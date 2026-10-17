package br.com.helpDesk.usuarios.controller;

import br.com.helpDesk.usuarios.dto.CadastroLogin;
import br.com.helpDesk.usuarios.dto.LoginCadastro;
import br.com.helpDesk.usuarios.dto.UsuarioToken;
import br.com.helpDesk.usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Autenticação e Usuários", description = "Endpoints para cadastro de clientes/atendentes e autenticação no sistema")
public class AuthController {


    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Cadastrar novo cliente", description = "Endpoint público para auto-cadastro de novos clientes no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de cadastro inválidos ou e-mail já cadastrado")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarClient(@RequestBody CadastroLogin cadastroLogin) {
        String cadastro = usuarioService.cadastrarClient(cadastroLogin);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastro);
    }

    @Operation(
            summary = "Cadastrar novo atendente",
            description = "Cadastra um novo usuário com perfil de Atendente. Requer token de autenticação com a role 'ATENDENTE'.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atendente cadastrado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autenticado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado (requer perfil ATENDENTE)"),
            @ApiResponse(responseCode = "400", description = "Dados de cadastro inválidos")
    })
    @PreAuthorize("hasRole('ATENDENTE')")
    @PostMapping("/atendente/cadastrar")
    public ResponseEntity<String> cadastrarAtendente(@RequestBody CadastroLogin cadastroLogin) {
        String cadastro = usuarioService.cadastrarAtendente(cadastroLogin);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastro);
    }

    @Operation(summary = "Realizar login", description = "Autentica o usuário e retorna o token JWT para acesso aos endpoints protegidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso (retorna o token JWT)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas (e-mail ou senha incorretos)")
    })
    @PostMapping("/login")
    public ResponseEntity<UsuarioToken> login(@RequestBody LoginCadastro loginCadastro) {
        UsuarioToken login = usuarioService.Login(loginCadastro);
        return ResponseEntity.ok(login);
    }


}
