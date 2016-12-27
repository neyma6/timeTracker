package com.expedia.sol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listTeam")
public class ListTeamController extends ListController {

	@Override
	protected String getRedirectString() {
		return "listTeam";
	}

}
