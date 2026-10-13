package br.com.helpDesk.chamados.controller;

import br.com.helpDesk.chamados.dto.ChamadoReponse;
import br.com.helpDesk.chamados.dto.ChamadoRequest;
import br.com.helpDesk.chamados.dto.StatusRquest;
import br.com.helpDesk.chamados.service.AtendenteChamadoService;
import br.com.helpDesk.chamados.service.ClientChamadoService;
import br.com.helpDesk.config.JWTUserData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chamado")
public class ChamadoController {


    private final ClientChamadoService clientChamadoService;
    private final AtendenteChamadoService atendenteChamadoService;

    public ChamadoController(ClientChamadoService clientChamadoService, AtendenteChamadoService atendenteChamadoService) {
        this.clientChamadoService = clientChamadoService;
        this.atendenteChamadoService = atendenteChamadoService;
    }

    @GetMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<List<ChamadoReponse>> listarChamados(@AuthenticationPrincipal JWTUserData jwtUserData) {
        List<ChamadoReponse> chamadoReponses = clientChamadoService.exibirChamados(jwtUserData.getUserId());
        return ResponseEntity.ok(chamadoReponses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ChamadoReponse> listarChamadoPorId(@AuthenticationPrincipal JWTUserData jwtUserData, @PathVariable Long id) {
        ChamadoReponse chamadoReponse = clientChamadoService.exibirChamadoPorId(jwtUserData.getUserId(), id);
        return ResponseEntity.ok(chamadoReponse);
    }

    @PostMapping("/abrirChamado")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ChamadoReponse> criarChamado(@AuthenticationPrincipal JWTUserData jwtUserData, @RequestBody ChamadoRequest request) {
        ChamadoReponse chamadoReponse = clientChamadoService.criarChamado(jwtUserData.getUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(chamadoReponse);
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<Void> deletarChamado(@AuthenticationPrincipal JWTUserData jwtUserData,@PathVariable Long id) {
        clientChamadoService.deletarChamado(jwtUserData.getUserId(), id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/alterar/{id}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ChamadoReponse> alterarChamado(@AuthenticationPrincipal JWTUserData jwtUserData, @PathVariable  Long id, @RequestBody ChamadoRequest request){
        ChamadoReponse chamadoReponse = clientChamadoService.alterarChamado(jwtUserData.getUserId(), id, request);
        return ResponseEntity.ok(chamadoReponse);
    }

    @PreAuthorize("hasRole('ATENDENTE')")
    @PutMapping("/atendente/status/{id}")
    public ResponseEntity<String> alterarStatusDoPedido(@PathVariable Long id,@RequestBody StatusRquest status) {
        String string = atendenteChamadoService.alterarStatusDoPedido(id, status);
        return ResponseEntity.ok(string);
    }

    @PreAuthorize("hasRole('ATENDENTE')")
    @GetMapping("/atendente/listar")
    public ResponseEntity<List<ChamadoReponse>> exibirPedidos() {
        List<ChamadoReponse> chamadoReponses = atendenteChamadoService.exibirTodosChamados();
        return ResponseEntity.ok(chamadoReponses);
    }

    @PreAuthorize("hasRole('ATENDENTE')")
    @GetMapping("/atendente/listar/{id}")
    public ResponseEntity<ChamadoReponse> exibirPedidoPorId(@PathVariable Long id) {
        ChamadoReponse chamadoReponse = atendenteChamadoService.exibirPorId(id);
        return ResponseEntity.ok(chamadoReponse);
    }

    @PreAuthorize("hasRole('ATENDENTE')")
    @DeleteMapping("/atendente/deletar/{id}")
    public ResponseEntity<Void> deletarChamado(@PathVariable Long id) {
        atendenteChamadoService.deletarChamado(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
