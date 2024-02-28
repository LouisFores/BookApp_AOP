package com.example.be2_lesson1_aopexceptionhandling_bookborrow.controller;

import com.example.be2_lesson1_aopexceptionhandling_bookborrow.model.Book;
import com.example.be2_lesson1_aopexceptionhandling_bookborrow.model.Cart;
import com.example.be2_lesson1_aopexceptionhandling_bookborrow.model.Product;
import com.example.be2_lesson1_aopexceptionhandling_bookborrow.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Optional;


@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    private IProductService productService;


    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }


    @GetMapping("/shop")
    public ModelAndView showShop() {
        ModelAndView modelAndView = new ModelAndView("/product/shop");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/shop/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/shop/create")
    public String create(@ModelAttribute("product") Product product,
                         RedirectAttributes redirectAttributes) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Create new product successfully");
        return "redirect:/shop";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute Cart cart,
                            @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error";
        }
        if (action.equals("show")) {
            cart.addProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }
        cart.addProduct(productOptional.get());
        return "redirect:/shop";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id,
                            @ModelAttribute Cart cart,
                            @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error";
        }
        if (action.equals("show")) {
            cart.removeProduct(productOptional.get());
        }
        return "redirect:/shopping-cart";
    }
}
