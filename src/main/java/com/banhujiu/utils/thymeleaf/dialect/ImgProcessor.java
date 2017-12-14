package com.banhujiu.utils.thymeleaf.dialect;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.banhujiu.utils.thymeleaf.exception.ErrorDomainException;

import static org.thymeleaf.util.StringUtils.trim;
import static org.thymeleaf.util.Validate.notEmpty;

/**
 * @author banhujiu
 * @date 2017/12/14 0014 11:16
 */
public class ImgProcessor extends AbstractAttributeTagProcessor {
	private static final String ATTRIBUTE_NAME = "img";
	private static final int PRECEDENCE = 300;
	private String domain;

	protected ImgProcessor(String dialectPrefix, String domain) {
		super(
				TemplateMode.HTML, // 处理thymeleaf 的模型
				dialectPrefix, // 标签前缀名
				null, // No tag name: match any tag name
				false, // No prefix to be applied to tag name
				ATTRIBUTE_NAME, // 标签前缀的 属性 例如：< th:img="">
				true, // Apply dialect prefix to attribute name
				PRECEDENCE, // Precedence (inside dialect's precedence)
				true); // Remove the matched attribute afterwards
		if (domain == null) {
			throw new ErrorDomainException();
		}
		this.domain = domain;
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String s, IElementTagStructureHandler structureHandler) {
		String rawValue = trim(tag.getAttributeValue(attributeName));
		notEmpty(rawValue, "value of '" + attributeName + "' must not be empty");

		if (!rawValue.startsWith("/")) {
			rawValue = "/" + rawValue;
		}
		structureHandler.replaceAttribute(attributeName, "src", this.domain + rawValue);
	}
}
