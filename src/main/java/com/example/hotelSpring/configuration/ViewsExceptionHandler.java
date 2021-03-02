package com.example.hotelSpring.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ViewsExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ModelAndView wrongInput() {
        return new ModelAndView("error500");
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ModelAndView handleError404(HttpServletRequest request, Exception e) {
        return new ModelAndView("error404");
    }
}
