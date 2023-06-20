import org.example.Desafio02.src.SistemaDeVendas.Cliente;
import org.example.Desafio02.src.SistemaDeVendas.Produto;
import org.example.Desafio02.src.SistemaDeVendas.Vendedor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.mockito.junit.jupiter.MockitoExtension;



public class ClienteTest {
    @Test
    public void verificaSeMostrarClienteEstaFuncionando(){
        Cliente cli = new Cliente();
        cli.mostrarCliente();
//        assertEquals(cli,cli);
    }

    @Test
    public void verificaSeMostrarVendedorEstaFuncionando(){
        Vendedor vend = new Vendedor();
        vend.adicionaVendedores();
        vend.mostrarVendedores();
//        assertEquals(vend,vend);
    }
    @Test
    public void verificaSeEstaSorteandoVendedor(){
        Vendedor vend = new Vendedor();
        vend.sortearVendedores();
    }
    @Test
    public void verificarSeEPossivelVisualizarProdutos(){
        Produto prod = new Produto();
        prod.mostrarProdutos();
    }
    @Test
    public void verificarSeEPossivelFinalizarCompra(){
        Cliente cliente = new Cliente();
        cliente.finalizar();
    }
}
