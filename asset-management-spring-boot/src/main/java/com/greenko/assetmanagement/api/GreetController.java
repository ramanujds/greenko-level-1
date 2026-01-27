package com.greenko.assetmanagement.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @GetMapping("/message")
    public String greet(){
        return "Hello from Greenko";
    }

}
