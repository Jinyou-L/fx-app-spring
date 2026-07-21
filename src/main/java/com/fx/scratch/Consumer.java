package com.fx.scratch;

import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private final GreetingService greetingService;

    public Consumer(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String greet(String name) {
        return greetingService.greet(name);
    }
}
