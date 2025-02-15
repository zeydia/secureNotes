package com.zeta.SecureNotes.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class HelloController {

    @GetMapping("hello")
    public String HelloPage() {
        return "Hello";
    }
}
