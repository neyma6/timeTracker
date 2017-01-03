package com.expedia.sol.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.dao.request.ActivityDbRequest;
import com.expedia.sol.dao.request.ListStatusRequest;
import com.expedia.sol.domain.Activity;
import com.expedia.sol.domain.Status;
import com.expedia.sol.provider.PropertyProvider;

@Controller
@RequestMapping("/submit")
public class SubmitController {

	@Autowired
	private PropertyProvider propertyProvider;
	
	@Resource(name = "hibernateDBAccessor")
	private IDBAccessor<Status, ListStatusRequest> dbAccessor;
	
	@Resource(name = "activityHibernateDBAccessor")
	private IDBAccessor<Activity, ActivityDbRequest> activityAccessor;
	
	@Resource(name = "statusValidator")
	private Validator validator;
	
	@ModelAttribute
	public void fill(Model model) {
		model.addAttribute("names", propertyProvider.getNames());
		model.addAttribute("time", propertyProvider.getTime());
		model.addAttribute("task", getTasks(activityAccessor.get(new ActivityDbRequest())));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		
		model.addAttribute("status", new Status());
		return "submitForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(@ModelAttribute("status") Status status, Model model, BindingResult result) {
		
		ZoneId zoneId = ZoneId.systemDefault();
		long currentEpoch = LocalDate.now().atStartOfDay(zoneId).toEpochSecond();
		status.setTimestamp(currentEpoch);
		
		validator.validate(status, result);
		
		if (result.hasErrors()) {
			model.addAttribute("validationError", true);
			model.addAttribute("status", status);
			return "submitForm";
		}
		
		boolean success = dbAccessor.save(status);
		
		model.addAttribute("success", success);
		return "submitForm";
	}
	
	private List<String> getTasks(List<Activity> activities) {
		List<String> tasks = new ArrayList<>();
		for (Activity act : activities) {
			tasks.add(act.getName());
		}
		return tasks;
	}
}
