package com.expedia.sol.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.expedia.sol.domain.Status;
import com.expedia.sol.provider.PropertyProvider;
import com.mysql.jdbc.StringUtils;

public class StatusValidator implements Validator {

	@Autowired
	private PropertyProvider propertyProvider;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Status.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Status status = (Status) target;

		if (StringUtils.isNullOrEmpty(status.getName())) {
			errors.rejectValue("name", "name.not.proper");
		}
		if (StringUtils.isNullOrEmpty(status.getDescription())) {
			errors.rejectValue("description", "desc.not.proper");
		}
		if (!propertyProvider.getTime().contains(status.getTime())) {
			errors.rejectValue("time", "time.not.proper");
		}
		if (status.getId() < 0) {
			errors.rejectValue("id", "id.not.proper");
		}
		if (status.getTimestamp() < 0) {
			errors.rejectValue("timestamp", "timestamp.not.proper");
		}
		
	}

}
