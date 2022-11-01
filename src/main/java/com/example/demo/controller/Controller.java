package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente/v1")
public class Controller
{
    @Autowired
    Repository resposity;
    @PostMapping
    public Cliente create(@RequestBody @Valid Cliente cliente)
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
    @DeleteMapping("/{id}") //Deleta tal cliente atraves do id
    public String deleteClienteById(@PathVariable Long id)
    {
        try
        {

         Optional<Cliente> cliente = Optional.of(resposity.getById(id));
            if (cliente.isPresent())
            {
                resposity.deleteById(id);
                return "Cliente de " + id + "deletado com sucesso! ";
            }else
            {
                throw new Exception("Cliente inexistente");   //throw significa jogar
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            return "O cliente de " + id + " não existe para ser deletado! " +
            "Por favor, entre em contado com o atendimento 696 969 696";
        }

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
