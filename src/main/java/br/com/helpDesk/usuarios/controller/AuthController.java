package br.com.helpDesk.usuarios.controller;

import br.com.helpDesk.usuarios.dto.CadastroLogin;
import br.com.helpDesk.usuarios.dto.LoginCadastro;
import br.com.helpDesk.usuarios.dto.UsuarioToken;
import br.com.helpDesk.usuarios.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class AuthController {


    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarClient(@RequestBody CadastroLogin cadastroLogin) {
        String cadastro = usuarioService.cadastrarClient(cadastroLogin);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastro);
    }

    @PreAuthorize("hasRole('ATENDENTE')")
    @PostMapping("/atendente/cadastrar")
    public ResponseEntity<String> cadastrarAtendente(@RequestBody CadastroLogin cadastroLogin) {
        String cadastro = usuarioService.cadastrarAtendente(cadastroLogin);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastro);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioToken> login(@RequestBody LoginCadastro loginCadastro) {
        UsuarioToken login = usuarioService.Login(loginCadastro);
        return ResponseEntity.status(HttpStatus.CREATED).body(login);
    }


}
