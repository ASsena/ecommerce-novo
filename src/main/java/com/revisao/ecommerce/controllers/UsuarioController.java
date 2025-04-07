package com.revisao.ecommerce.controllers;

import com.revisao.ecommerce.dto.UsuarioDTO;
import com.revisao.ecommerce.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Método que vai receber as infos do usuário na requisição Post para '/usuario/cadastrar'
    // Esse método vai chamar o service para salvar o user e vai retornar os dados salvos
    @PostMapping(value = "cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.salvarUsuario(usuarioDTO), HttpStatus.CREATED);
    }


    @PostMapping("login")
    public ResponseEntity<?> logar(@RequestBody UsuarioDTO usuarioDTO) {
        boolean teste = usuarioService.loginUsuario(usuarioDTO);

        if(teste){
            return new ResponseEntity<>("Sucesso!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Senha incorreta",HttpStatus.UNAUTHORIZED);
    }

}
