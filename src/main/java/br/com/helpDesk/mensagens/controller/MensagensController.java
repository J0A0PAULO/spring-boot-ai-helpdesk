package br.com.helpDesk.mensagens.controller;

import br.com.helpDesk.config.JWTUserData;
import br.com.helpDesk.mensagens.dto.MensagensResponse;
import br.com.helpDesk.mensagens.service.MensagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mensagens")
@Tag(name = "Mensagens", description = "Endpoints para listagem e consulta de mensagens do usuário logado")
public class MensagensController {

    private final MensagemService mensagemService;

    public MensagensController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @Operation(
            summary = "Listar todas as mensagens",
            description = "Retorna uma lista com todas as mensagens vinculadas ao cliente autenticado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagens listadas com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autenticado / Token ausente ou inválido"),
            @ApiResponse(responseCode = "403", description = "Acesso negado (requer perfil CLIENTE)")
    })
    @GetMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<List<MensagensResponse>> listarMensagens(@AuthenticationPrincipal JWTUserData jwtUserData) {
        List<MensagensResponse> mensagens = mensagemService.listarMensagens(jwtUserData.getUserId());
        return ResponseEntity.ok(mensagens);
    }

    
    @Operation(summary = "Listar mensagem específica por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Mensagem não encontrada ou não pertence ao usuário")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<MensagensResponse> listarMensagemPorId(@AuthenticationPrincipal JWTUserData jwtUserData,
                                                                 @PathVariable Long id) {
        MensagensResponse mensagem = mensagemService.listarMensagenPorId(jwtUserData.getUserId(), id);

        return ResponseEntity.ok(mensagem);
    }
}