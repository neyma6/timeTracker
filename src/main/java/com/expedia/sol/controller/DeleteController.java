package com.expedia.sol.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.dao.request.ListStatusRequest;
import com.expedia.sol.domain.Status;

@Controller
@RequestMapping("/delete")
public class DeleteController {

	@Resource(name = "hibernateDBAccessor")
	private IDBAccessor<Status, ListStatusRequest> dbAccessor;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(@RequestParam("id") String id, Model model) {
		Status status = dbAccessor.getById(new ListStatusRequest(Integer.parseInt(id)));
		
		if (status == null) {
			model.addAttribute("nodata", true);
			return "delete";
		}
		
		model.addAttribute("status", status);
		
		return "delete";
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public String post(@RequestParam("id") String id, Model model) {
		
		boolean success = dbAccessor.delete(new ListStatusRequest(Integer.parseInt(id)));
		model.addAttribute("success", success);
		
		return "delete";
	}
}
