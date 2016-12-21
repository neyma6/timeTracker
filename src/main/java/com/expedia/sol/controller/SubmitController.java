package com.expedia.sol.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expedia.sol.domain.Status;

@Controller
@RequestMapping("/submit")
public class SubmitController {

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		
		List<String> names = Arrays.asList("Gabor Csatlos", "Gabor Karajko");
		List<Double> time =  Arrays.asList(0.5, 1d, 1.5, 2d, 2.5, 3d, 3.5, 4d, 4.5, 5d, 5.5, 6d, 6.5, 7d, 7.5, 8d);
		model.addAttribute("status", new Status());
		model.addAttribute("names", names);
		model.addAttribute("time", time);
		
		return "submitForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(@ModelAttribute("status") Status status, Model model) {
		
		System.out.println(status);
		
		return "submitForm";
	}
}
