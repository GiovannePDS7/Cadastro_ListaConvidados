package com.example.Cadastrar_ListaConvidados.dto;

import lombok.Data;

@Data
public class ConvidadoRespostaDTO {
    private Long idConvidado;
    private String nomeConvidado;
    private Long idEvento;  // Apenas o ID do evento, conforme solicitado
}
