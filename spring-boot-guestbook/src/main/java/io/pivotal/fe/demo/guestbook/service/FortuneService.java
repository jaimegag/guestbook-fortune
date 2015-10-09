package io.pivotal.fe.demo.guestbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class FortuneService {

    @Autowired
    RestTemplate restTemplate;
    
    @HystrixCommand(fallbackMethod = "fallbackFortune")
    public String remoteFortune() {
        return restTemplate.getForObject("http://fortunes/remote-fortune", String.class);
    }

    private String fallbackFortune() {
        return "Your future is unclear.";
    }
}
