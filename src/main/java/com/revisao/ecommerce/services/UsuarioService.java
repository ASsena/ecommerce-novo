package com.revisao.ecommerce.services;

import com.revisao.ecommerce.dto.UsuarioDTO;
import com.revisao.ecommerce.entities.Usuario;
import com.revisao.ecommerce.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuariorRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuariorRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    //Método que vai salvar o usuário no banco de dados
    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(bCryptPasswordEncoder.encode(usuarioDTO.getSenha()));
        usuario.setTelefone(usuarioDTO.getTelefone());


        usuario = usuarioRepository.save(usuario);

        return new UsuarioDTO(usuario);
    }

    //Método que retorna um boolean a depender se o e-mail está cadastrado e se a senha está correta
    public boolean loginUsuario(UsuarioDTO usuarioDTO) {
        var usuario = usuarioRepository.findByEmail(usuarioDTO.getEmail());
        System.out.println(usuarioDTO.getEmail());
        System.out.println(usuarioDTO.getSenha());
        if (usuario == null) {
            return false;
        }
        return bCryptPasswordEncoder.matches(usuarioDTO.getSenha(), usuario.getSenha());

    }
}
