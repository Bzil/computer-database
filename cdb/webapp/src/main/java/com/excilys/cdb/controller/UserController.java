package com.excilys.cdb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.cdb.model.User;

// TODO see org.springframework.security.core.userdetails
@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("login") User user, final Model model) {
		return ControllerList.LOGIN_VIEW;
	}
}
