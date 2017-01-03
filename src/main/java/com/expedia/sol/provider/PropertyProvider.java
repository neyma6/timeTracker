package com.expedia.sol.provider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import com.expedia.sol.dao.util.TimeIntervalUtil;

public class PropertyProvider implements InitializingBean {

	@Value("${names.list}")
	private String namesList;
	
	@Value("${time.values}")
	private String timeList;
	
	@Value("${report.week}")
	private String weeks;
	
	private List<String> names;
	private List<String> time;
	
	public List<String> getNames() {
		return names;
	}

	public List<String> getTime() {
		return time;
	}

	public List<String> getWeek() {
		return TimeIntervalUtil.getDisplayableTimeIntervalList(Integer.parseInt(weeks));
	}
	
	public int getNumberOfWeeks() {
		return Integer.parseInt(weeks);
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
