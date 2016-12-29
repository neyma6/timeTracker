package com.expedia.sol.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.domain.Status;
import com.expedia.sol.provider.PropertyProvider;

@Controller
@RequestMapping("/modify")
public class ModifyController {

	@Autowired
	private PropertyProvider propertyProvider;
	
	@Resource(name = "hibernateDBAccessor")
	private IDBAccessor dbAccessor;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(@RequestParam("id") String id, Model model) {
		Status status = dbAccessor.getStatusById(Integer.parseInt(id));
		
		if (status == null) {
			model.addAttribute("nodata", true);
			return "modify";
		}
		
		model.addAttribute("status", status);
		model.addAttribute("names", propertyProvider.getNames());
		model.addAttribute("time", propertyProvider.getTime());
		model.addAttribute("task", propertyProvider.getTask());
		
		return "modify";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(@ModelAttribute("status") Status status, Model model) {
		
		boolean success = dbAccessor.update(status);
		model.addAttribute("success", success);
		
		return "modify";
	}
}