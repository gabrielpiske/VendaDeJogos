package model;

import java.util.List;

/**
 *
 * @author gabriel_piske
 */
public class Carrinho {
    int idCarrinho;
    List<Jogo> listaJogos;
    Double valorTotal;
    
    public Carrinho(){}

    public Carrinho(int idCarrinho, List<Jogo> listaJogos, Double valorTotal) {
        this.idCarrinho = idCarrinho;
        this.listaJogos = listaJogos;
        this.valorTotal = valorTotal;
    }

    public List<Jogo> getListaJogos() {
        return listaJogos;
    }

    public void setListaJogos(List<Jogo> listaJogos) {
        this.listaJogos = listaJogos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(int idCarrinho) {
        this.idCarrinho = idCarrinho;
    }
    
    @Override
    public String toString() {
        return "Carrinho{" +
                "idCarrinho=" + idCarrinho +
                ", listaJogos=" + listaJogos +
                ", valorTotal=" + valorTotal +
                '}';
    }

}
