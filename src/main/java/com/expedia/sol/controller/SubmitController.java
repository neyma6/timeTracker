package com.expedia.sol.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.domain.Status;
import com.expedia.sol.provider.PropertyProvider;

@Controller
@RequestMapping("/submit")
public class SubmitController {

	@Autowired
	private PropertyProvider propertyProvider;
	
	@Resource(name = "hibernateDBAccessor")
	private IDBAccessor dbAccessor;
	
	
	@ModelAttribute
	public void fillModel(Model model) {
		model.addAttribute("names", propertyProvider.getNames());
		model.addAttribute("time", propertyProvider.getTime());
		model.addAttribute("task", propertyProvider.getTask());
		if (!model.containsAttribute("status")) {
			model.addAttribute("status", new Status());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		return "submitForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(@ModelAttribute("status") Status status, Model model) {
		
		ZoneId zoneId = ZoneId.systemDefault();
		long currentEpoch = LocalDate.now().atStartOfDay(zoneId).toEpochSecond();
		status.setTimestamp(currentEpoch);
		boolean success = dbAccessor.save(status);
		
		model.addAttribute("success", success);
		return "submitForm";
	}
}
