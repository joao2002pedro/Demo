package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Cliente> getClienteById(@PathVariable Long id)
    {
        Optional<Cliente> clienteReturned = resposity.findById(id);
        return clienteReturned;

    }
}
