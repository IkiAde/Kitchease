package com.example.KitchEase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/back-office")
public class BackOfficeController {

    //Route Controlller for back-office pages

    @GetMapping("/home")
    public ModelAndView login() {
        return new ModelAndView("back-office/home");
        // landing page view home.html
    }

    @GetMapping("/usermanagement")
    public ModelAndView usermanagement() {
        return new ModelAndView("back-office/usermanagement");
        // landing page view usermanagement.html
    }

    @GetMapping("/stockmanagement")
    public ModelAndView stockmanagement() {
        return new ModelAndView("back-office/stockmanagement"); 
        // landing page view stockmanagement.html
    }

}
