package com.expedia.sol.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.expedia.sol.domain.Person;
import com.mysql.jdbc.StringUtils;

public class PersonValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Person person = (Person)target;
		
		if (StringUtils.isNullOrEmpty(person.getName())) {
			errors.rejectValue("name", "name.empty");
		}
		
	}

}

