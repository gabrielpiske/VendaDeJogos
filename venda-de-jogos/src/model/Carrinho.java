/*
    Classe model do carrinho com seus atributos próprios
 */

package model;

import java.util.List;

/**
 *
 * @author gabriel_piske
 */
public class Carrinho {

    int usuario_id;
    List<Integer> listaJogos;
    List<Double> valorTotal;

    public Carrinho(int usuario_id, List<Integer> listaJogos, List<Double> valorTotal) {
        this.usuario_id = usuario_id;
        this.listaJogos = listaJogos;
        this.valorTotal = valorTotal;
    }

    

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public List<Integer> getListaJogos() {
        return listaJogos;
    }

    public void setListaJogos(List<Integer> listaJogos) {
        this.listaJogos = listaJogos;
    }

    public List<Double> getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(List<Double> valorTotal) {
        this.valorTotal = valorTotal;
    }

}
