package com.example.fortuneservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fortuneservice.domain.Fortune;
import com.example.fortuneservice.repositories.FortuneRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@ConfigurationProperties(prefix="fortune")
public class FortuneController {

	private final Random RANDOM = new Random();
    
    private List<String> messages = new ArrayList<String>();

    @Autowired
    FortuneRepository repository;

    @RequestMapping("/fortunes")
    public Iterable<Fortune> fortunes() {
        return repository.findAll();
    }

    @RequestMapping("/random")
    public Fortune randomFortune() {
        List<Fortune> randomFortunes = repository.randomFortunes(new PageRequest(0, 1));
        return randomFortunes.get(0);
    }
    
    @RequestMapping("/remote-fortune")
    public String randomRemoteFortune() {
        return this.messages.get(RANDOM.nextInt(this.messages.size()));
    }
    
    public void setMessages(List<String> messages) {
    	this.messages = messages;
    }
    
    public List<String> getMessages() {
    	return this.messages;
    }
}
