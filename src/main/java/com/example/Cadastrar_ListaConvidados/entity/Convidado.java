package com.example.Cadastrar_ListaConvidados.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ListaConv")
public class Convidado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Convidado")
    private Long idConvidado;

    @Column(name = "Nome_Convidado", nullable = false, length = 100)
    private String nomeConvidado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Evento", referencedColumnName = "Id_Evento", nullable = false)
    private Evento evento;
}
