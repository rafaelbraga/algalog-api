package com.algaworks.algalog.algalogapi.api.controller;

import com.algaworks.algalog.algalogapi.domain.model.Cliente;

import com.algaworks.algalog.algalogapi.domain.repository.ClienteRepository;
import com.algaworks.algalog.algalogapi.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private CatalogoClienteService catalogoClienteService;

    @GetMapping()
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }
    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        return clienteRepository.findById(clienteId).map(cliente -> ResponseEntity.ok(cliente)).orElse(ResponseEntity.notFound().build());
//        Optional<Cliente> cliente=  clienteRepository.findById(clienteId);
//        if (cliente.isPresent()){
//            return ResponseEntity.ok(cliente.get());
//        }
//        else {
//            return ResponseEntity.notFound().build();
//        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar (@Valid @RequestBody Cliente cliente){
//        return clienteRepository.save(cliente);
        return catalogoClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public  ResponseEntity<Cliente> atualizar (@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente){
        if (!clienteRepository.existsById(clienteId)){
            return  ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
//        cliente = clienteRepository.save(cliente);
        cliente = catalogoClienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);

    }
    @DeleteMapping("/{clienteId}")
    public  ResponseEntity<Void> remover(@PathVariable Long clienteId){
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
//        clienteRepository.deleteById(clienteId);
        catalogoClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();

    }
}
