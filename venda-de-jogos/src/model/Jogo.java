package model;

/**
 *
 * @author gabriel_piske
 */
public class Jogo {
    String nome, descricao, dataLancamento, classificacaoIndicativa;
    Double preco;
    byte[] imagem;
    
    public Jogo(){
    }

    public Jogo(String nome, String descricao, String dataLancamento, String classificacaoIndicativa, Double preco, byte[] imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataLancamento = dataLancamento;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.preco = preco;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(String classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    
}
