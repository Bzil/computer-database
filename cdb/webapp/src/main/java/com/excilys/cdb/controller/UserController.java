package com.excilys.cdb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// TODO see org.springframework.security.core.userdetails
@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		final ModelAndView model = new ModelAndView();
		model.setViewName("connect");
		return model;
	}

	/**
	 * Admin page.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/dashboard**", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		final ModelAndView model = new ModelAndView();
		model.setViewName("dashboard");
		return model;

	}

	/**
	 * Login.
	 *
	 * @param error
	 *            the error
	 * @param logout
	 *            the logout
	 * @return the model and view
	 */
	@RequestMapping(value = "/connect", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		final ModelAndView model = new ModelAndView();
		if (error != null) {
			LOGGER.debug("Login error : {}", error);
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			LOGGER.debug("Logout : {}", logout);
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("connect");

		return model;
	}
}
