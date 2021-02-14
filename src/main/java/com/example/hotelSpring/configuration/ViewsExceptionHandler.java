package com.example.hotelSpring.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ViewsExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ModelAndView wrongInput() {
        return new ModelAndView("error500");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(HttpServletRequest request, Exception e) {
        return new ModelAndView("error404");
    }
}
