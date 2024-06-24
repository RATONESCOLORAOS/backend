package com.ratones_colorados.ahorro_compras.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

    @RequestMapping("greetings")
    public String greetings(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello {" + name + "}";
    }
}
