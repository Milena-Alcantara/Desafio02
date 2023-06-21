package org.example;

import java.util.ArrayList;
import java.util.List;

public class Produto {
    private String nomeProduto, codProduto;
    private Double preco;

    static List<Produto> listaProdutos = new ArrayList<>();
    public Produto(){
    }

    public Produto(String nomeProduto, String codProduto, Double preco){
        this.nomeProduto = nomeProduto;
        this.codProduto = codProduto;
        this.preco = preco;

    }
    public static void adicionarProdutos(){
        listaProdutos.add(new Produto("Bolo Formigueiro", "26367",25.90));
        listaProdutos.add(new Produto("Bolo de Laranja", "26399",15.90));
        listaProdutos.add(new Produto("Bolo de Coco", "26500",15.90));
        listaProdutos.add(new Produto("Bolo Sensação", "26500",35.90));
        listaProdutos.add(new Produto("Bolo de Chocolate", "26502",39.90));

    }
    public static void mostrarProdutos(){
        listaProdutos.clear();
        adicionarProdutos();
        for (int i = 0; i <listaProdutos.size() ; i++) {
            System.out.println(i+1 + " - "+listaProdutos.get(i).getNomeProduto()+ " Cód: "+listaProdutos.get(i).codProduto+ " "+ "R$"+listaProdutos.get(i).preco);
        }
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public Double getPreco() {
        return preco;
    }
}
