package br.com.adirvic.task.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = {RequestMethod.GET})
    public String sayHello() {
        return "Hello world";
    }

    @GetMapping("/{user}")
    public String welcomeUser(@PathVariable String user) {
        return "Welcome " + user ;
    }
}
