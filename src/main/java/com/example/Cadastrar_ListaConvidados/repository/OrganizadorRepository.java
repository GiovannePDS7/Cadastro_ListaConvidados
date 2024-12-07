package com.example.Cadastrar_ListaConvidados.repository;


import com.example.Cadastrar_ListaConvidados.entity.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {

}
