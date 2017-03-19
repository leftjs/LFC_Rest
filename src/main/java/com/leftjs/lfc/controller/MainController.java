package com.leftjs.lfc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jason on 2017/3/11.
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String frontPage() {
        return "index";
    }
}
