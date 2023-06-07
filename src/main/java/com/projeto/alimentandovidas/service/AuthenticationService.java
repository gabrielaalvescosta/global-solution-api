package com.projeto.alimentandovidas.service;

import com.projeto.alimentandovidas.entities.Usuario;
import com.projeto.alimentandovidas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.GetUsuarioByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }



}