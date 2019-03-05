package com.arquitetura.modulocoleta.model;

public class Encomenda {
    private Long id;
    private Double valorFrete;
    private String origemFrete;
    private String peso;
    private String dimensao;
    private String codRastreio;
    private String veiculo;
    private String nomeRemetente;
    private String enderecoRemetente;
    private String contatoRemetente;
    private String nomeDestinatario;
    private String enderecoDestinatario;
    private String contatoDestinatario;
    private String dataHoraRecebimento;
    private String classificacaoTransporte;
    private String classificacaoEnvio;
    private Boolean cargaRetorno;
    private Integer numeroEixos;
    private String nomeResponsavel;
    private String observacao;

    public Encomenda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public String getOrigemFrete() {
        return origemFrete;
    }

    public void setOrigemFrete(String origemFrete) {
        this.origemFrete = origemFrete;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDimensao() {
        return dimensao;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }

    public String getCodRastreio() {
        return codRastreio;
    }

    public void setCodRastreio(String codRastreio) {
        this.codRastreio = codRastreio;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getNomeRemetente() {
        return nomeRemetente;
    }

    public void setNomeRemetente(String nomeRemetente) {
        this.nomeRemetente = nomeRemetente;
    }

    public String getEnderecoRemetente() {
        return enderecoRemetente;
    }

    public void setEnderecoRemetente(String enderecoRemetente) {
        this.enderecoRemetente = enderecoRemetente;
    }

    public String getContatoRemetente() {
        return contatoRemetente;
    }

    public void setContatoRemetente(String contatoRemetente) {
        this.contatoRemetente = contatoRemetente;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public String getEnderecoDestinatario() {
        return enderecoDestinatario;
    }

    public void setEnderecoDestinatario(String enderecoDestinatario) {
        this.enderecoDestinatario = enderecoDestinatario;
    }

    public String getContatoDestinatario() {
        return contatoDestinatario;
    }

    public void setContatoDestinatario(String contatoDestinatario) {
        this.contatoDestinatario = contatoDestinatario;
    }

    public String getDataHoraRecebimento() {
        return dataHoraRecebimento;
    }

    public void setDataHoraRecebimento(String dataHoraRecebimento) {
        this.dataHoraRecebimento = dataHoraRecebimento;
    }

    public String getClassificacaoTransporte() {
        return classificacaoTransporte;
    }

    public void setClassificacaoTransporte(String classificacaoTransporte) {
        this.classificacaoTransporte = classificacaoTransporte;
    }

    public String getClassificacaoEnvio() {
        return classificacaoEnvio;
    }

    public void setClassificacaoEnvio(String classificacaoEnvio) {
        this.classificacaoEnvio = classificacaoEnvio;
    }

    public Boolean getCargaRetorno() {
        return cargaRetorno;
    }

    public void setCargaRetorno(Boolean cargaRetorno) {
        this.cargaRetorno = cargaRetorno;
    }

    public Integer getNumeroEixos() {
        return numeroEixos;
    }

    public void setNumeroEixos(Integer numeroEixos) {
        this.numeroEixos = numeroEixos;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void salvarPedido(Encomenda encomenda) {
    }

    @Override
    public String toString() {
        return "Encomenda{" +
                "id=" + id +
                ", valorFrete=" + valorFrete +
                ", origemFrete='" + origemFrete + '\'' +
                ", peso='" + peso + '\'' +
                ", dimensao='" + dimensao + '\'' +
                ", codRastreio='" + codRastreio + '\'' +
                ", veiculo='" + veiculo + '\'' +
                ", nomeRemetente='" + nomeRemetente + '\'' +
                ", enderecoRemetente='" + enderecoRemetente + '\'' +
                ", contatoRemetente='" + contatoRemetente + '\'' +
                ", nomeDestinatario='" + nomeDestinatario + '\'' +
                ", enderecoDestinatario='" + enderecoDestinatario + '\'' +
                ", contatoDestinatario='" + contatoDestinatario + '\'' +
                ", dataHoraRecebimento='" + dataHoraRecebimento + '\'' +
                ", classificacaoTransporte='" + classificacaoTransporte + '\'' +
                ", classificacaoEnvio='" + classificacaoEnvio + '\'' +
                ", cargaRetorno=" + cargaRetorno +
                ", numeroEixos=" + numeroEixos +
                ", nomeResponsavel='" + nomeResponsavel + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
