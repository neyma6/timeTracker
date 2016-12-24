package com.expedia.sol.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.domain.Status;

@Controller
@RequestMapping("/submit")
public class SubmitController implements InitializingBean {

	@Value("${names.list}")
	private String namesList;
	
	@Value("${time.values}")
	private String timeList;
	
	@Resource(name = "hibernateDBAccessor")
	private IDBAccessor dbAccessor;
	
	private static List<String> names;
	private static List<String> time;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		
		model.addAttribute("status", new Status());
		model.addAttribute("names", names);
		model.addAttribute("time", time);
		
		return "submitForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(@ModelAttribute("status") Status status, Model model) {
		
		System.out.println(status);
		status.setNanotime(LocalTime.now().toNanoOfDay());
		dbAccessor.save(status);
		
		return "submitForm";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String[] split = namesList.split(",");
		names = Arrays.asList(split);
		Collections.sort(names);
		
		String[] splitTime = timeList.split(",");
		time = Arrays.asList(splitTime);
	}
}
