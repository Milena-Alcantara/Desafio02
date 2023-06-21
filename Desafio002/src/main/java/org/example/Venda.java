package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda extends Cliente {
    private static Vendedor vendedor;
    private static Cliente comprador;
    private static LocalDate dataRegistro;
    private static List<Produto> listaCompras;
    private static Double valorTotal;
    static List<Venda> vendasGeral = new ArrayList<>();
    public static void setComprador(Cliente comprador) {
        Venda.comprador = comprador;
    }

    public static void setVendedor(Vendedor vendedor) {
        Venda.vendedor = vendedor;
    }
    public static void setDataRegistro(LocalDate dataRegistro){
       Venda.dataRegistro = dataRegistro;
    }

    public static void setListaCompras(List<Produto> listaCompras) {
        Venda.listaCompras = listaCompras;
    }

    public static void setValorTotal(Double valorTotal) {
        Venda.valorTotal = valorTotal;
    }

    public Venda() {
    }
    public Venda(Vendedor vendedor, Cliente comprador, LocalDate dataRegistro,List<Produto> listaCompras, Double valorTotal ){
       Venda.vendedor = vendedor;
       Venda.comprador = comprador;
       Venda.dataRegistro = dataRegistro;
       Venda.valorTotal = valorTotal;
       Venda.listaCompras = listaCompras;
    }

    public static Vendedor getVendedor() {
        return vendedor;
    }

    public static Cliente getComprador() {
        return comprador;
    }

    public static LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public static List<Produto> getListaCompras() {
        return listaCompras;
    }

    public static Double getValorTotal() {
        return valorTotal;
    }
}
