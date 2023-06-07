package com.projeto.alimentandovidas.entities.factories;
import org.modelmapper.ModelMapper;
import com.projeto.alimentandovidas.entities.AcaoSocial;
import com.projeto.alimentandovidas.models.AcaoSocialModel;
import com.projeto.alimentandovidas.repository.OrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AcaoSocialMapperFactory {

    private ModelMapper modelMapper;

    @Autowired
    private OrganizacaoRepository organizacaoRepository;
    public AcaoSocialMapperFactory() {
        this.modelMapper = new ModelMapper();
    }

    public AcaoSocial createEntityFromModel(AcaoSocialModel acaoSocialModel) {
        var entity =  modelMapper.map(acaoSocialModel, AcaoSocial.class);
        entity.setOrganizacao(organizacaoRepository.getOrganizacaoById(acaoSocialModel.id));
        return entity;
    }

}
