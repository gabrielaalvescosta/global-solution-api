package com.projeto.alimentandovidas.entities.factories;
import com.projeto.alimentandovidas.entities.Usuario;
import com.projeto.alimentandovidas.models.UsuarioModel;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioMapperFactory {

    private ModelMapper modelMapper;

    @Autowired
    public UsuarioMapperFactory() {
        this.modelMapper = new ModelMapper();
    }

    public Usuario createEntityFromModel(UsuarioModel usuarioModel) {
        return modelMapper.map(usuarioModel, Usuario.class);
    }

}
