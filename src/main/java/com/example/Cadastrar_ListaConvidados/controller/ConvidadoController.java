package com.example.Cadastrar_ListaConvidados.controller;

import com.example.Cadastrar_ListaConvidados.dto.ConvidadoRespostaDTO;
import com.example.Cadastrar_ListaConvidados.entity.Convidado;
import com.example.Cadastrar_ListaConvidados.entity.Evento;
import com.example.Cadastrar_ListaConvidados.repository.ConvidadoRepository;
import com.example.Cadastrar_ListaConvidados.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/convidados")
public class ConvidadoController {

    @Autowired
    private ConvidadoRepository convidadoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarConvidado(@RequestBody Convidado convidado) {
        if (convidado == null || convidado.getEvento() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
        }

        Evento evento = eventoRepository.findById(convidado.getEvento().getIdEvento())
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado!"));

        convidado.setEvento(evento); // Vincula o evento ao convidado
        Convidado convidadoSalvo = convidadoRepository.save(convidado);

        // Criar o DTO de resposta
        ConvidadoRespostaDTO respostaDTO = new ConvidadoRespostaDTO();
        respostaDTO.setIdConvidado(convidadoSalvo.getIdConvidado());
        respostaDTO.setNomeConvidado(convidadoSalvo.getNomeConvidado());
        respostaDTO.setIdEvento(convidadoSalvo.getEvento().getIdEvento()); // Incluindo apenas o ID do evento

        return ResponseEntity.status(HttpStatus.CREATED).body(respostaDTO);
    }

    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<?> listarConvidadosPorEvento(@PathVariable Long idEvento) {
        try {
            // Busca os convidados no repositório, filtrando pelo ID do evento
            List<Convidado> convidados = convidadoRepository.findByEventoIdEvento(idEvento);

            // Converte os convidados para ConvidadoRespostaDTO
            List<ConvidadoRespostaDTO> convidadoRespostaDTOs = convidados.stream()
                    .map(c -> {
                        ConvidadoRespostaDTO dto = new ConvidadoRespostaDTO();
                        dto.setIdConvidado(c.getIdConvidado());
                        dto.setNomeConvidado(c.getNomeConvidado());
                        dto.setIdEvento(c.getEvento().getIdEvento());  // Pegamos o ID do evento
                        return dto;
                    })
                    .collect(Collectors.toList());

            // Retorna a resposta com o status 200 e a lista de ConvidadoRespostaDTO
            return ResponseEntity.ok(convidadoRespostaDTOs);
        } catch (Exception e) {
            // Em caso de erro, retorna o status 500 com a mensagem de erro
            return ResponseEntity.status(500).body("Erro ao buscar convidados: " + e.getMessage());
        }
    }

}
