package com.example.Cadastrar_ListaConvidados.repository;

import com.example.Cadastrar_ListaConvidados.entity.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {
    List<Convidado> findByEventoIdEvento(Long idEvento);
}
