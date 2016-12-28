package com.expedia.sol.provider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class PropertyProvider implements InitializingBean {

	@Value("${names.list}")
	private String namesList;
	
	@Value("${time.values}")
	private String timeList;
	
	@Value("${report.week}")
	private String weeks;
	
	@Value("${status.task}")
	private String taskList;
	
	private List<String> names;
	private List<String> time;
	private List<String> week;
	private List<String> task;
	
	public List<String> getNames() {
		return names;
	}

	public List<String> getTime() {
		return time;
	}

	public List<String> getWeek() {
		return week;
	}
	
	public List<String> getTask() {
		return task;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String[] split = namesList.split(",");
		names = Arrays.asList(split);
		Collections.sort(names);
		
		String[] splitTime = timeList.split(",");
		time = Arrays.asList(splitTime);
		
		String[] splitWeek = weeks.split(",");
		week = Arrays.asList(splitWeek);
		
		String[] splitTask = taskList.split(",");
		task = Arrays.asList(splitTask);
		Collections.sort(task);
	}

}
