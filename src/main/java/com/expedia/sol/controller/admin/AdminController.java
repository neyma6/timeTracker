package com.expedia.sol.controller.admin;

import java.util.List;

import javax.annotation.Resource;

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
import com.expedia.sol.domain.Activity;
import com.expedia.sol.domain.Person;
import com.expedia.sol.util.POJOToStringConverter;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource(name = "activityValidator")
	private Validator activityValidator;
	
	@Resource(name = "personValidator")
	private Validator personValidator;
	
	@Resource(name = "activityHibernateDBAccessor")
	private IDBAccessor<Activity, ActivityDbRequest> activityAccessor;
	
	@Resource(name = "personHibernateDbAccessor")
	private IDBAccessor<Person, PersonDbRequest> personAccessor;
	
	@RequestMapping(value = "/addActivity", method = RequestMethod.GET)
	public String getAddActivity(Model model) {
		
		model.addAttribute("activity", new Activity());

		return "addActivity";
	}
	
	@RequestMapping(value = "/addActivity", method = RequestMethod.POST)
	public String postAddActivity(@ModelAttribute("activity") Activity activity, Model model, BindingResult result) {
		
		activityValidator.validate(activity, result);
		
		if (result.hasErrors()) {
			model.addAttribute("validationError", true);
			return "addActivity";
		}
		
		List<String> activities = POJOToStringConverter.getTasks(activityAccessor.get(new ActivityDbRequest()));
		if (activities.contains(activity.getName())) {
			model.addAttribute("validationError", true);
			return "addActivity";
		}
		
		boolean success = activityAccessor.save(activity);
		model.addAttribute("success", success);
		
		return "addActivity";
	}
	
	@RequestMapping(value = "/deleteActivity", method = RequestMethod.GET)
	public String getDeleteActivity(Model model) {
		
		model.addAttribute("activities", POJOToStringConverter.getTasks(activityAccessor.get(new ActivityDbRequest())));
		return "deleteActivity";
	}
	
	
	@RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
	public String postDeleteActivity(@RequestParam("name") String name, Model model) {
		activityAccessor.delete(new ActivityDbRequest(name));
		
		model.addAttribute("activities", POJOToStringConverter.getTasks(activityAccessor.get(new ActivityDbRequest())));
		return "deleteActivity";
	}
	
	
	@RequestMapping(value = "/addPerson", method = RequestMethod.GET)
	public String getAddPerson(Model model) {
		
		model.addAttribute("person", new Person());

		return "addPerson";
	}
	
	@RequestMapping(value = "/addPerson", method = RequestMethod.POST)
	public String postAddPerson(@ModelAttribute("person") Person person, Model model, BindingResult result) {
		
		personValidator.validate(person, result);
		
		if (result.hasErrors()) {
			model.addAttribute("validationError", true);
			return "addPerson";
		}
		
		List<String> persons = POJOToStringConverter.getPersons((personAccessor.get(new PersonDbRequest())));
		if (persons.contains(person.getName())) {
			model.addAttribute("validationError", true);
			return "addPerson";
		}
		
		boolean success = personAccessor.save(person);
		model.addAttribute("success", success);
		
		return "addPerson";
	}
	
	@RequestMapping(value = "/deletePerson", method = RequestMethod.GET)
	public String getDeletePerson(Model model) {
		
		model.addAttribute("persons", POJOToStringConverter.getPersons((personAccessor.get(new PersonDbRequest()))));
		return "deletePerson";
	}
	
	
	@RequestMapping(value = "/deletePerson", method = RequestMethod.POST)
	public String postDeletePErson(@RequestParam("name") String name, Model model) {
		personAccessor.delete(new PersonDbRequest(name));
		
		model.addAttribute("persons", POJOToStringConverter.getPersons((personAccessor.get(new PersonDbRequest()))));
		return "deletePerson";
	}
	
}
