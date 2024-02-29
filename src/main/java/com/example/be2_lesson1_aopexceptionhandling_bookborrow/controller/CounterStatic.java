package com.example.be2_lesson1_aopexceptionhandling_bookborrow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CounterStatic {
    public static int count = 0;
    @RequestMapping("/static")
    public ModelAndView countStatic() {
        ModelAndView modelAndView = new ModelAndView("/demo/static");
        count++;
        modelAndView.addObject("countStatic", count);
        return modelAndView;
    }
}
