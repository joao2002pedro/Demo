package com.example.demo;

import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cliente/v1")

public class Controller {

    @Autowired
    Repository resposity;

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente)
    {
        Cliente clienteSaved = resposity.save(cliente);
        return clienteSaved;
    }
}
