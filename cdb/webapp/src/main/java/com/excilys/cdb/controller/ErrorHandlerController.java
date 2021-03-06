/**
 * 
 * @author Basile
 */
package com.excilys.cdb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class ErrorHandlerController.
 */
@ControllerAdvice
public class ErrorHandlerController {

    /**
     * Handle500.
     *
     * @param e the e
     * @return the model and view
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handle500(Exception e) {
        final ModelAndView model = new ModelAndView(
                ControllerList.ERROR_500_VIEW);
        model.addObject("message", e.getMessage());
        model.addObject("exception", e.getStackTrace());
        return model;
    }
}
