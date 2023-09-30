package com.eazyschool.consumer.consumer.controller;

import com.eazyschool.consumer.consumer.model.Contact;
import com.eazyschool.consumer.consumer.proxy.ContactProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    ContactProxy contactProxy;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient webClient;

    @GetMapping("/getMessages")
    public List<Contact> getMessages( @RequestParam("status") String status ){
        return contactProxy.getMessagesByStatus( status );
    }
}
