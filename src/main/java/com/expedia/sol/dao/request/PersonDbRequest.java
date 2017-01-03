package com.expedia.sol.dao.request;

import com.expedia.sol.dao.DBRequest;
import com.expedia.sol.domain.Person;

public class PersonDbRequest implements DBRequest<Person> {

	private final String name;
	
	public PersonDbRequest(String name) {
		this.name = name;
	}

	public PersonDbRequest() {
		this.name = "";
	}

	public String getName() {
		return name;
	}

	@Override
	public Person createEntity() {
		Person person = new Person();
		person.setName(this.name);
		return person;
	}

}
