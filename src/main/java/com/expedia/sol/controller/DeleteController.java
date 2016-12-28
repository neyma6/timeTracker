package com.expedia.sol.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.domain.Status;

@Controller
@RequestMapping("/delete")
public class DeleteController {

	@Resource(name = "hibernateDBAccessor")
	private IDBAccessor dbAccessor;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(@RequestParam("id") String id, Model model) {
		Status status = dbAccessor.getStatusById(Integer.parseInt(id));
		
		if (status == null) {
			// TODO redirect
		}
		
		model.addAttribute("status", status);
		
		return "delete";
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public String post(@RequestParam("id") String id, Model model) {
		
		boolean success = dbAccessor.delete(Integer.parseInt(id));
		model.addAttribute("success", success);
		
		return "delete";
	}
}
