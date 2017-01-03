package com.expedia.sol.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.expedia.sol.domain.Report;
import com.expedia.sol.provider.PropertyProvider;
import com.mysql.jdbc.StringUtils;

public class ReportValidator implements Validator {

	@Autowired
	private PropertyProvider propertyProvider;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Report.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Report report = (Report) target;
		
		if (!isWeekInRange(report)) {
			errors.rejectValue("week", "week.not.in.range");
		}
		
		if (isReportInvalid(report)) {
			errors.rejectValue("name", "name.invalid");
		}
	}

	private boolean isReportInvalid(Report report) {
		return report.getName() != null && StringUtils.isEmptyOrWhitespaceOnly(report.getName());
	}

	private boolean isWeekInRange(Report report) {
		return 0 < report.getWeek() && report.getWeek() <= propertyProvider.getNumberOfWeeks();
	}

}
