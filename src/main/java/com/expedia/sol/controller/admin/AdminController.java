package com.expedia.sol.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.dao.request.ActivityDbRequest;
import com.expedia.sol.domain.Activity;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource(name = "activityValidator")
	private Validator validator;
	
	@Resource(name = "activityHibernateDBAccessor")
	private IDBAccessor<Activity, ActivityDbRequest> accessor;
	
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
			return "addActivity";
		}
		
		boolean success = accessor.save(activity);
		model.addAttribute("success", success);
		
		return "addActivity";
	}
}
