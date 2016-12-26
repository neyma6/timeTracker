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
import com.expedia.sol.util.DateFormatter;

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
		
		LocalDate today = LocalDate.now();
		LocalDate sunday;
		if (today.getDayOfWeek().getValue() == 7) {
			sunday = today;
		} else {
			sunday = today.with(next(SUNDAY));
		}
		
		int daysBack = DAYS * report.getWeek();
		LocalDate monday = sunday.minusDays(daysBack - 1);
		LocalDate nextSunday = monday.plusDays(DAYS - 1);
		
		ZoneId zoneId = ZoneId.systemDefault(); 
		long mondayEpoch = monday.atStartOfDay(zoneId).toEpochSecond();
		long sundayEpoch = nextSunday.atStartOfDay(zoneId).toEpochSecond();
		
		TimeInterval interval = new TimeInterval(mondayEpoch, sundayEpoch);
		
		List<Status> statuses = dbAccessor.getStatus(report.getName(), interval);
		model.addAttribute("statuses", statuses);
		model.addAttribute("workingHours", calculateWorkingHours(statuses));
		model.addAttribute("start", DateFormatter.formateTimeStampToDate(interval.getStart()));
		model.addAttribute("end", DateFormatter.formateTimeStampToDate(interval.getEnd()));
		
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
