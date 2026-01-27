package com.greenko.assetmanagement.api;

import com.greenko.assetmanagement.dto.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class GreetController {

    @GetMapping("/message")
    public Message greet(){
        return new Message("Hello from Greenko",
                LocalDate.now(), LocalTime.now());
    }

}
