package com.expedia.sol.controller;

import java.time.LocalDate;
import java.time.ZoneId;
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
import com.expedia.sol.domain.Report;
import com.expedia.sol.domain.Status;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.previous;

@Controller
@RequestMapping("/list")
public class ListController implements InitializingBean{

	private static final int DAYS = 7;
	
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
		return "list";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(@ModelAttribute("report") Report report, Model model) {
		
		LocalDate sunday = LocalDate.now().with(next(SUNDAY));
		
		int daysBack = DAYS * report.getWeek();
		LocalDate monday = sunday.minusDays(daysBack);
		LocalDate nextSunday = monday.plusDays(DAYS);
		
		ZoneId zoneId = ZoneId.systemDefault(); 
		long mondayEpoch = monday.atStartOfDay(zoneId).toEpochSecond();
		long sundayEpoch = nextSunday.atStartOfDay(zoneId).toEpochSecond();
		
		TimeInterval interval = new TimeInterval(mondayEpoch, sundayEpoch);
		
		List<Status> statuses = dbAccessor.getStatus(report.getName(), interval);
		model.addAttribute("statuses", statuses);
		
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
}
