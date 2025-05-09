package com.example.moattravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class sample {
	    @GetMapping("/index")
	    public String showIndex() {
	        return "index";
	    }
	}
