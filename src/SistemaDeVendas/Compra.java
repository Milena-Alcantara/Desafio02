package SistemaDeVendas;

import java.time.LocalDate;
import java.util.List;

public class Compra extends Cliente {
    private Double valorTotal;
    private static Cliente comprador;
    private static List<Produto> listaCompras;
    public Compra(){}

    public void setComprador(Cliente comprador) {
        this.comprador = comprador;
    }

    public  Cliente getComprador() {
        return comprador;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public  List<Produto> getListaCompras() {
        return listaCompras;
    }

    public  void setListaCompras(List<Produto> listaCompras) {
        Compra.listaCompras = listaCompras;
    }

    public Compra(Cliente comprador, LocalDate dataCompra, List<Produto> listaCompras, Double valorTotal){
        this.comprador = comprador;
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
        Compra.listaCompras = listaCompras;
    }
}
