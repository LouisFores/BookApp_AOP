package com.example.be2_lesson1_aopexceptionhandling_bookborrow.controller;

import com.example.be2_lesson1_aopexceptionhandling_bookborrow.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CounterCookie {
    //Cookie chỉ lưu trên 1 trình duyệt, giá trị cookie trên mỗi trình duyệt là khác nhau tắt trình duyệt vẫn lưu và mỗi trình duyệt có 1 cookie để lưu giá trị
    //Session chỉ lưu giá trị tại 1 phiên làm việc trên trình duyệt, tắt trình duyệt thì giá trị bị xoá
    //Static lưu giá trị trên máy chủ, tắt trình duyệt thì giá trị vẫn lưu

    @GetMapping("/cookie")
    public ModelAndView listBook(@CookieValue(value = "counter", defaultValue = "0") Long counter, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("/demo/cookie");
        counter++;
        Cookie cookie = new Cookie("counter", counter.toString());
        cookie.setMaxAge(15);
        response.addCookie(cookie);
        modelAndView.addObject("counterCookie", cookie.getValue());
        return modelAndView;
    }
}
