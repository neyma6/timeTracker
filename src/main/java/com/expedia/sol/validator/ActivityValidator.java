package com.expedia.sol.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.expedia.sol.domain.Activity;
import com.mysql.jdbc.StringUtils;

public class ActivityValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Activity.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Activity activity = (Activity)target;
		
		if (StringUtils.isNullOrEmpty(activity.getName())) {
			errors.rejectValue("name", "name.empty");
		}
		
	}

}
