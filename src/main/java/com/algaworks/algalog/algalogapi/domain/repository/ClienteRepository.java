package com.algaworks.algalog.algalogapi.domain.repository;

import com.algaworks.algalog.algalogapi.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    List<Cliente> findByName(String nome);
    List<Cliente> findByNameContaining(String nome);
    Optional<Cliente> findByEmail(String email);
}
