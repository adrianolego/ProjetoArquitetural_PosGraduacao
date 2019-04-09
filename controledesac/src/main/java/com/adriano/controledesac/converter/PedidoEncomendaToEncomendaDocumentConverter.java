package com.adriano.controledesac.converter;

import com.adriano.controledesac.document.*;
import com.adriano.controledesac.model.PedidoEncomenda;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PedidoEncomendaToEncomendaDocumentConverter implements Converter<PedidoEncomenda, Encomenda> {
    @Override
    public Encomenda convert(PedidoEncomenda e) {

        Frete frete = null;
        Veiculo veiculo = null;
        Logistica logistica = null;
        Expedicao expedicao = null;

        if (e.getFrete() != null) {
            frete = Frete.builder()
                    .existeCargaRetorno(e.getFrete().isExisteCargaRetorno())
                    .dataColeta(e.getFrete().getDataColeta())
                    .pesoKg(e.getFrete().getPesoKg())
                    .prioridadeEnvio(e.getFrete().getPrioridadeEnvio())
                    .tipoCarga(e.getFrete().getTipoCarga())
                    .build();
        }

        if (e.getVeiculo() != null) {
            veiculo = Veiculo.builder()
                    .codigo(e.getVeiculo().getCodigo())
                    .descricao(e.getVeiculo().getDescricao())
                    .pesoMaximoKg(e.getVeiculo().getPesoMaximoKg())
                    .placa(e.getVeiculo().getPlaca())
                    .quantidadeEixos(e.getVeiculo().getQuantidadeEixos())
                    .build();
        }

        if (e.getLogistica() != null) {
            logistica = Logistica.builder()
                    .dataHoraPevisaoSaida(e.getLogistica().getDataHoraPevisaoSaida())
                    .descricaocaminho(e.getLogistica().getDescricaocaminho())
                    .responsavelRota(e.getLogistica().getResponsavelRota())
                    .build();
        }

        if (e.getExpedicao() != null) {
            expedicao = Expedicao.builder()
                    .dataHoraSaida(e.getExpedicao().getDataHoraSaida())
                    .documentoTransporte(e.getExpedicao().getDocumentoTransporte())
                    .enviado(e.getExpedicao().isEnviado())
                    .responsavelEnvio(e.getExpedicao().getResponsavelEnvio())
                    .build();
        }

        return Encomenda.builder()
                .dataHoraRecebimento(e.getDataHoraRecebimento())
                .idEncomenda(e.getIdEncomenda())
                .destinatario(Destinatario.builder()
                        .bairro(e.getDestinatario().getBairro())
                        .celular(e.getDestinatario().getCelular())
                        .cep(e.getDestinatario().getCep())
                        .cidade(e.getDestinatario().getCidade())
                        .email(e.getDestinatario().getEmail())
                        .logradouro(e.getDestinatario().getLogradouro())
                        .nome(e.getDestinatario().getNome())
                        .numero(e.getDestinatario().getNumero())
                        .telefone(e.getDestinatario().getTelefone())
                        .build())
                .remetente(Remetente.builder()
                        .bairro(e.getRemetente().getBairro())
                        .celular(e.getRemetente().getCelular())
                        .cep(e.getRemetente().getCep())
                        .cidade(e.getRemetente().getCidade())
                        .email(e.getRemetente().getEmail())
                        .logradouro(e.getRemetente().getLogradouro())
                        .nome(e.getRemetente().getNome())
                        .numero(e.getRemetente().getNumero())
                        .telefone(e.getRemetente().getTelefone())
                        .build())
                .veiculo(Arrays.asList(veiculo))
                .expedicao(Arrays.asList(expedicao))
                .frete(Arrays.asList(frete))
                .logistica(Arrays.asList(logistica))
                .nomeOperador(Arrays.asList(e.getNomeOperador()))
                .build();
    }
}
