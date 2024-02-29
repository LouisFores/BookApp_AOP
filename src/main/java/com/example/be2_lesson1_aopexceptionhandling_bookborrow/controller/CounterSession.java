package com.example.be2_lesson1_aopexceptionhandling_bookborrow.controller;

import com.example.be2_lesson1_aopexceptionhandling_bookborrow.model.Counter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("counter")
public class CounterSession {
//    Cách 1: Dùng với biến nguyên thuỷ và đối tượng đều được
//    @GetMapping("/session")
//    public ModelAndView get(HttpSession httpSession) {
//        ModelAndView modelAndView = new ModelAndView("/demo/session");
//        Integer count = (Integer) httpSession.getAttribute("count");
//        if (count == null) {
//            count = 0;
//        }
//        count++;
//        httpSession.setAttribute("count", count);
//        modelAndView.addObject("countSession", count);
//        return modelAndView;
//    }

//    Cách 2: Sử dụng ModelAttribute cần dùng đối tượng, không cần setAttribute vì tham chiếu đến session
    @ModelAttribute("counter")
    public Counter getCount() {
        return new Counter();
    }

    @GetMapping("/session")
    public ModelAndView showSession(@ModelAttribute("counter") Counter counter) {
        ModelAndView modelAndView = new ModelAndView("/demo/session");
        counter.increment();
        modelAndView.addObject("countSession", counter.getCount());
        return modelAndView;
    }
}
