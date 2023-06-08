package com.projeto.alimentandovidas.service;

import com.projeto.alimentandovidas.models.OrganizacaoModel;
import org.springframework.stereotype.Service;

@Service
public interface OrganizacaoService {

    void AdicionarOrganizacao(OrganizacaoModel organizacaoModel);
    OrganizacaoModel GetOrganizacaoById(Long id);
    OrganizacaoModel GetOrganizacaoByEmail(String email);

}

