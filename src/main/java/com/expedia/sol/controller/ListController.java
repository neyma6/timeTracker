package com.expedia.sol.controller;

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
import com.expedia.sol.dao.domain.TimeInterval;
import com.expedia.sol.dao.util.TimeIntervalUtil;
import com.expedia.sol.domain.Report;
import com.expedia.sol.domain.Status;
import com.expedia.sol.util.DateFormatter;

@Controller
@RequestMapping("/list")
public class ListController implements InitializingBean{

	
	@Value("${names.list}")
	private String namesList;
	
	@Value("${report.week}")
	private String weeks;
	
	@Resource(name = "hibernateDBAccessor")
	private IDBAccessor dbAccessor;
	
	private static List<String> names;
	private static List<String> week;
	
	@ModelAttribute
	public void fillModel(Model model) {
		model.addAttribute("names", names);
		model.addAttribute("weeks", week);
		if (!model.containsAttribute("report")) {
			model.addAttribute("report", new Report());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		return getRedirectString();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(@ModelAttribute("report") Report report, Model model) {
		
		TimeInterval interval = TimeIntervalUtil.getTimeInterval(report.getWeek());		
		List<Status> statuses = dbAccessor.getStatus(report.getName(), interval);
		model.addAttribute("statuses", statuses);
		model.addAttribute("workingHours", calculateWorkingHours(statuses));
		model.addAttribute("start", DateFormatter.formateTimeStampToDate(interval.getStart()));
		model.addAttribute("end", DateFormatter.formateTimeStampToDate(interval.getEnd()));
		
		return getRedirectString();
	}
	
	protected String getRedirectString() {
		return "list";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String[] split = namesList.split(",");
		names = Arrays.asList(split);
		Collections.sort(names);
		
		String[] splitTime = weeks.split(",");
		week = Arrays.asList(splitTime);
		
	}
	
	private double calculateWorkingHours(List<Status> list) {
		double workingHours = 0d;
		
		for (Status status : list) {
			workingHours += Double.parseDouble(status.getTime());
		}
		
		return workingHours;
	}
}
