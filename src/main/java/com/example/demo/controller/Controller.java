package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente/v1")

public class Controller
{

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

    @DeleteMapping("/id)") //Deleta tal cliente atraves do id
    public void deleteClienteById(@PathVariable Long id)
    {
        resposity.deleteById(id);
    }

    @GetMapping //Puxa todos os cliente
    public List<Cliente> clienteList()
    {
        return resposity.findAll();
    }

    //Atualiza ou coloca uma informação no lugar de outra
     @PutMapping("/atualize/{id}")
    public String updateClienteById(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id)
    {
        Optional<Cliente> velhoCliente = resposity.findById(id);
        if (velhoCliente.isPresent())
        {
            Cliente cliente = velhoCliente.get();
            cliente.setEndereco(clienteDTO.getEndereco());
            resposity.save(cliente);
            return "Cliente de ID " + cliente.getId() + " atualizado com sucesso ";
        } else
        {
            return "Cliente de ID " + id + " Não encontrado";
        }

    }
}
