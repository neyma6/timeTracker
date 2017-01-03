package com.expedia.sol.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = "/addActivity", method = RequestMethod.GET)
	public String getAddActivity() {
		

		return "addActivity";
	}
}
