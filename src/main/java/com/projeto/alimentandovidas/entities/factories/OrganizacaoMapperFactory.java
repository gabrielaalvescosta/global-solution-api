package com.projeto.alimentandovidas.entities.factories;
import com.projeto.alimentandovidas.entities.Organizacao;
import com.projeto.alimentandovidas.models.OrganizacaoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class OrganizacaoMapperFactory {

    private ModelMapper modelMapper;

    @Autowired
    public OrganizacaoMapperFactory() {
        this.modelMapper = new ModelMapper();
    }

    public Organizacao createEntityFromModel(OrganizacaoModel organizacaoModel) {
        return modelMapper.map(organizacaoModel, Organizacao.class);
    }
}
