package com.example.be2_lesson1_aopexceptionhandling_bookborrow.controller;

import com.example.be2_lesson1_aopexceptionhandling_bookborrow.model.Book;
import com.example.be2_lesson1_aopexceptionhandling_bookborrow.repository.BookRepository;
import com.example.be2_lesson1_aopexceptionhandling_bookborrow.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("")
    public ModelAndView listBook(@CookieValue(value = "counter", defaultValue = "0") Long counter, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("/views/list");
        Iterable<Book> books = bookService.findAll();
        counter++;
        Cookie cookie = new Cookie("counter", counter.toString());
        cookie.setMaxAge(10);
        response.addCookie(cookie);
        modelAndView.addObject("books", books);
        modelAndView.addObject("cookie", cookie.getValue());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/views/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("book") Book book,
                         RedirectAttributes redirectAttributes) {
        bookService.save(book);
        redirectAttributes.addFlashAttribute("message", "Create new book successfully");
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/views/update");
            modelAndView.addObject("book", book.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("book") Book book,
                         RedirectAttributes redirect) {
        bookService.save(book);
        redirect.addFlashAttribute("message", "Update book successfully");
        return "redirect:/books";
    }

    @GetMapping("/view-book/{id}")
    public ModelAndView viewForm(@PathVariable Long id ) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            httpSession.setAttribute("book", book);
            ModelAndView modelAndView = new ModelAndView("/views/view-book");
            modelAndView.addObject("book", book.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error");
        }
    }

    @GetMapping("/borrow/{id}")
    public String borrow(@PathVariable Long id) throws Exception {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            if (book.get().getNumber() > 0) {
                book.get().setNumberBookRemain(book.get().getNumberBookRemain() + 1);
                book.get().setNumber(book.get().getNumber() - 1);
                bookService.save(book.get());
            } else {
                throw new Exception();
            }
        } else {

            return "/error";
        }
        return "redirect:/books";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam("code") int code) {
        Optional<Book> book = bookService.findByCode(code);
        if (book.isPresent()) {
            if (book.get().getNumberBookRemain() > 0) {
                book.get().setNumber(book.get().getNumber() + 1);
                book.get().setNumberBookRemain(book.get().getNumberBookRemain() - 1);
                bookService.save(book.get());
            }
        } else {
            throw new NullPointerException();
        }
        return "redirect:/books";
    }

    @GetMapping("/view-session")
    public ModelAndView viewSession() {
        ModelAndView modelAndView = new ModelAndView("/views/view-book");
        Optional<Book> book = (Optional<Book>) httpSession.getAttribute("book");
        System.out.println(book.get().getId());
        modelAndView.addObject("book", book.get());
        return modelAndView;
    }

}