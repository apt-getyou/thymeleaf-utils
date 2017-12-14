package com.banhujiu.utils.thymeleaf.dialect;

import java.util.LinkedHashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.banhujiu.utils.thymeleaf.exception.ErrorDomainException;

/**
 * @author banhujiu
 * @date 2017/12/14 0014 10:51
 */
public class ImgDialect extends AbstractProcessorDialect {
	public static final String NAME = "img";
	public static final String PREFIX = "th";
	public String domain = "http://localhost";

	public ImgDialect() {
		super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
	}

	public ImgDialect(String domain) {
		super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
		if (domain == null) {
			throw new ErrorDomainException();
		}
		this.domain = domain;
	}

	@Override
	public Set<IProcessor> getProcessors(String imgPrefix) {
		LinkedHashSet<IProcessor> processors = new LinkedHashSet<>();
		processors.add(new ImgProcessor(imgPrefix, domain));
		return processors;
	}
}
