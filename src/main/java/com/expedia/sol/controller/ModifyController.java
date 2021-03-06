package com.expedia.sol.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.dao.request.ActivityDbRequest;
import com.expedia.sol.dao.request.PersonDbRequest;
import com.expedia.sol.dao.request.StatusRequest;
import com.expedia.sol.domain.Activity;
import com.expedia.sol.domain.Person;
import com.expedia.sol.domain.Status;
import com.expedia.sol.provider.PropertyProvider;
import com.expedia.sol.util.POJOToStringConverter;

@Controller
@RequestMapping("/modify")
public class ModifyController {

	@Autowired
	private PropertyProvider propertyProvider;
	
	@Resource(name = "hibernateDBAccessor")
	private IDBAccessor<Status, StatusRequest> dbAccessor;
	
	@Resource(name = "activityHibernateDBAccessor")
	private IDBAccessor<Activity, ActivityDbRequest> activityAccessor;
	
	@Resource(name = "personHibernateDbAccessor")
	private IDBAccessor<Person, PersonDbRequest> personAccessor;
	
	@Resource(name = "statusValidator")
	private Validator validator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(@RequestParam("id") String id, Model model) {
		Status status = dbAccessor.getById(new StatusRequest(Integer.parseInt(id)));
		
		if (status == null) {
			model.addAttribute("nodata", true);
			return "modify";
		}
		
		model.addAttribute("status", status);
		model.addAttribute("names", POJOToStringConverter.getPersons(personAccessor.get(new PersonDbRequest())));
		model.addAttribute("time", propertyProvider.getTime());
		model.addAttribute("task", POJOToStringConverter.getTasks(activityAccessor.get(new ActivityDbRequest())));
		
		return "modify";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(@ModelAttribute("status") Status status, Model model, BindingResult result) {
		
		validator.validate(status, result);
		
		if (result.hasErrors()) {
			model.addAttribute("validationError", true);
			return "modify";
		}
		boolean success = dbAccessor.update(status);
		model.addAttribute("success", success);
		
		return "modify";
	}
}
