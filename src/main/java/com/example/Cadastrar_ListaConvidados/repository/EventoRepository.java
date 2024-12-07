package com.example.Cadastrar_ListaConvidados.repository;



import com.example.Cadastrar_ListaConvidados.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository <Evento, Long> {
    List<Evento> findByOrganizadorIdOrganizador(Long idOrganizador);
}

