package br.senac.rn.model;

import java.util.List;

public class Venda {

    private int id;
    private Cliente cliente;
    private List<Produto> produtos;
    private float valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", cliente=" + cliente + ", produtos=" + produtos + ", valor=" + valor + '}';
    }
    
}
