package com.expedia.sol.controller.admin;

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
import com.expedia.sol.domain.Activity;
import com.expedia.sol.util.ActivityToStringConverter;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource(name = "activityValidator")
	private Validator validator;
	
	@Resource(name = "activityHibernateDBAccessor")
	private IDBAccessor<Activity, ActivityDbRequest> activityAccessor;
	
	@RequestMapping(value = "/addActivity", method = RequestMethod.GET)
	public String getAddActivity(Model model) {
		
		model.addAttribute("activity", new Activity());

		return "addActivity";
	}
	
	@RequestMapping(value = "/addActivity", method = RequestMethod.POST)
	public String postAddActivity(@ModelAttribute("activity") Activity activity, Model model, BindingResult result) {
		
		validator.validate(activity, result);
		
		if (result.hasErrors()) {
			model.addAttribute("validationError", true);
			return "deleteActivity";
		}
		
		boolean success = activityAccessor.save(activity);
		model.addAttribute("success", success);
		
		return "addActivity";
	}
	
	@RequestMapping(value = "/deleteActivity", method = RequestMethod.GET)
	public String getDeleteActivity(Model model) {
		
		model.addAttribute("activities", ActivityToStringConverter.getTasks(activityAccessor.get(new ActivityDbRequest())));
		return "deleteActivity";
	}
	
	
	@RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
	public String postDeleteActivity(@RequestParam("name") String name, Model model) {
		System.out.println(name);
		activityAccessor.delete(new ActivityDbRequest(name));
		
		model.addAttribute("activities", ActivityToStringConverter.getTasks(activityAccessor.get(new ActivityDbRequest())));
		return "deleteActivity";
	}
	
	
}
