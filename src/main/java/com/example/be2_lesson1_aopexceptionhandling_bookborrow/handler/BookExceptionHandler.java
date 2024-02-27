package com.example.be2_lesson1_aopexceptionhandling_bookborrow.handler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Aspect
@ControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e) {
        System.out.println(e.getMessage());
        ModelAndView modelAndView = new ModelAndView("/error");
        return modelAndView;
    }

    @AfterReturning(value = "execution(public * com.example.be2_lesson1_aopexceptionhandling_bookborrow.controller.BookController.borrow(..))")
    public void afterMethod(JoinPoint joinPoint){
        System.out.println("number of book change");
    }
    @AfterThrowing(value = "execution(public * com.example.be2_lesson1_aopexceptionhandling_bookborrow.controller.BookController.borrow(..))",throwing = "e")
    public void afterMethodError(JoinPoint joinPoint ,Exception e){
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.printf("[CMS] in %5.%5%5: %5%n",className, methodName, args + e.getMessage());
    }
}
