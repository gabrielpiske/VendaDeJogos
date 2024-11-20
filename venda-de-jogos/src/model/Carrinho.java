package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel_piske
 */
public class Carrinho {

    int idCarrinho;
    List<Jogo> listaJogos;
    Double valorTotal;

    public Carrinho() {
        this.listaJogos = new ArrayList<>();
        this.valorTotal = 0.0;
    }

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

    public void addJogo(Jogo jogo) {
        if (this.listaJogos == null) {
            this.listaJogos = new ArrayList<>();
        }
        this.listaJogos.add(jogo);
    }

}
