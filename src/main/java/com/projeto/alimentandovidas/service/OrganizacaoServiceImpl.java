package com.projeto.alimentandovidas.service;

import com.projeto.alimentandovidas.entities.factories.AcaoSocialMapperFactory;
import com.projeto.alimentandovidas.entities.factories.OrganizacaoMapperFactory;
import com.projeto.alimentandovidas.models.OrganizacaoModel;
import com.projeto.alimentandovidas.repository.AcaoSocialRepository;
import com.projeto.alimentandovidas.repository.OrganizacaoRepository;
import com.projeto.alimentandovidas.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrganizacaoServiceImpl implements OrganizacaoService {

    private OrganizacaoMapperFactory organizacaoMapperFactory;

    private AcaoSocialRepository acaoSocialRepository;
    private OrganizacaoRepository organizacaoRepository;
    private UsuarioRepository usuarioRepository;

    public void AdicionarOrganizacao(OrganizacaoModel organizacaoModel) {
        var organizacao = organizacaoMapperFactory.createEntityFromModel(organizacaoModel);
        organizacao.setDataCadastro(LocalDateTime.now());
        organizacao.setStatus(organizacaoModel.status);
        organizacao.setCidade(organizacaoModel.cidade);
        organizacao.setEstado(organizacaoModel.estado);
        organizacao.setChavePix(organizacaoModel.chavePix);
        organizacao.setAcaoSocialList(organizacaoModel.acaoSocialList);
        organizacao.setDescricao(organizacaoModel.descricao);
        organizacao.setCnpj(organizacaoModel.cnpj);
        organizacao.setNomeFantasia(organizacaoModel.nomeFantasia);
        organizacao.setSite(organizacaoModel.site);
        organizacao.setTelefone(organizacaoModel.telefone);
        organizacao.setPizza(pizzaRepository.getReferenceById(model.idpizza));
        pedido.setCliente(clienteRepository.getReferenceById(UUID.fromString(model.idCliente)));
        pedidoRepository.save(pedido);
    }

    @Override
    public OrganizacaoModel GetOrganizacaoById(Long id) {
        return null;
    }

    @Override
    public OrganizacaoModel GetOrganizacaoByEmail(String email) {
        return null;
    }

}
