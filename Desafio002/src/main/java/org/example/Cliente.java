package org.example;
import org.springframework.security.crypto.bcrypt.BCrypt;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.time.LocalDate;
import java.util.*;

public class Cliente {
    static Scanner input = new Scanner(System.in);
    private String nome, email, cpf, senha, senhaCripto;
    static List<Produto> listaCompras = new ArrayList<>();
    static double valorTotal;
    static List<Cliente> clientesCadastrados = new ArrayList<>();
    static Map<String,Compra> historicoCompras = new LinkedHashMap<>();
    static LocalDate dataCompra;

    public Cliente() {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }


    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getSenhaCripto() {
        return senhaCripto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setSenhaCripto(String senhaCripto) {
        this.senhaCripto = senhaCripto;
    }

    public static void mostrarMenuCliente(){
        try {
            System.out.println("---------------------------------------------------------------");
            System.out.println("Informe: ");
            System.out.println("1- Efetuar Cadastro \t 2- Realizar uma Compra \t 3- Verificar Histórico de Compras \t 4- Voltar ao Menu anterior");
            switch (input.nextInt()){
                case 1:
                    input.nextLine();
                    cadastrar();
                    break;


                case 2:
                    input.nextLine();
                    logarCliente();
                    break;

                case 3:
                    input.nextLine();
                    exibirHistoricoCompras();
                    break;

                case  4:
                    input.nextLine();
                    Menu.mostrarMenuSistema();


                default:
                    throw new NumberFormatException();
            }

        }catch (NumberFormatException erro){
         System.err.println("Entrada Inválida, tente novamente");
          mostrarMenuCliente();
        }catch (InputMismatchException erro2){
            input.nextLine();
            System.err.println("Entrada Inválida, tente novamente");
            mostrarMenuCliente();
        }

        mostrarMenuCliente();
    }

    public static Cliente cadastrar(){
        Cliente cliente = new Cliente();
try {

    System.out.println("Informe seu nome: ");
    cliente.setNome(input.nextLine());
    if (!(cliente.getNome().matches("[a-zA-Z]+"))){
        throw new UnsupportedOperationException();
    }

    System.out.println("Informe seu CPF: ");
    cliente.setCpf(input.nextLine());
    if (!(cliente.getCpf().matches("\\d+"))){
        throw new UnsupportedOperationException();
    }

    System.out.println("Informe o seu e-mail: ");
    cliente.setEmail(input.next());
    if (!(cliente.getEmail().contains("@")) || !(cliente.getEmail().contains("."))){
        System.err.println("Email inválido, por favor digite novamente");
        cliente.setEmail(input.next());
    }

    System.out.println("Informe uma senha: ");
    cliente.setSenha(input.next());
    if (!(cliente.getSenha().matches("\\d+")) && !(cliente.getSenha().matches("[a-zA-Z]+")) && cliente.getSenha().length()<8){
        System.out.println("Senha inválida, tente novamente");
        cliente.setSenha(input.next());
    }

    cliente.setSenhaCripto(BCrypt.hashpw(cliente.getSenha(), BCrypt.gensalt()));
    System.out.println(BCrypt.checkpw(cliente.getSenha(), cliente.getSenhaCripto()));


    boolean clienteCadastrado = false;
    for (Cliente clienteExistente : clientesCadastrados) { // para cada  item da lista, esta atibuindo para um atributo do mesmo tipo da classe
        if (clienteExistente.getCpf().equals(cliente.getCpf()) || clienteExistente.getEmail().equals(cliente.getEmail())){ //verificando se o cpf do atributo é igual ao do novo objeto istanciado
            clienteCadastrado = true;
            break;
        }
    }
    if (clienteCadastrado){
        System.out.println("Cliente já cadastrado");
    }else {
        clientesCadastrados.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }
}catch (UnsupportedOperationException erro){
    System.err.println("Entrada de dados inválida, tente novamente");
    cadastrar();
}
        mostrarMenuCliente();
       return (Cliente) clientesCadastrados;
    }

    static Compra compra1 = new Compra();
    public static void logarCliente(){
      try {
          System.out.println("Informe seu e-mail");
          String email = input.next();
          if (!(email.contains("@"))){
              throw new UnsupportedOperationException();
          }
          System.out.println("Informe sua senha");
          String senha = input.next();
          boolean clienteLogado = false;
          for (Cliente clienteExistente: clientesCadastrados) {
              if (clienteExistente.getEmail().equals(email)){
                  if(BCrypt.checkpw(senha, clienteExistente.getSenhaCripto())){
                      System.out.println(BCrypt.checkpw(senha, clienteExistente.getSenhaCripto()));
                      clienteLogado = true;
                      compra1.setComprador(clienteExistente);
                      Venda.setComprador(clienteExistente);
                      break;
                  }else{
                      System.out.println("Senha ou e-mail incorretos");
                      clienteLogado = true;
                      logarCliente();
                      break;
                  }
              }
          }
          if (!(clienteLogado)){
              System.out.println("Ops... vi aqui que seu e-mail não consta em nosso sistema");
              System.out.println("Deseja efetuar seu cadastro? \n 1- Sim \t 2- Não ");
              switch (input.nextInt()){
                  case 1:
                      input.nextLine();
                      cadastrar();
                      break;

                  case 2:
                      mostrarMenuCliente();
                      break;

                  default:
                      throw new NumberFormatException();
              }
          }else {
              comprar();
          }
      }catch (UnsupportedOperationException erro){
          System.err.println("E-mail inválido, por favor tente novamente.");
          logarCliente();
      }
    }
    public static void comprar() throws InputMismatchException{
      dataCompra = LocalDate.now();
        System.out.println("-------------------- BOLOS EM ESTOQUE  ------------------------");
        Produto.mostrarProdutos();
        verificar();
    }
    public static void verificar(){
        System.out.println("Informe o item desejado pelo número");
        int produtoEscolhido = input.nextInt();
        for (int i = 0; i <Produto.listaProdutos.size() ; i++) {
            if ( i == (produtoEscolhido -1)){
                listaCompras.add(Produto.listaProdutos.get(i));
                break;
            }
        }

        System.out.println("Deseja continuar comprando? \n 1- Sim \t 2- Não");
        switch (input.nextInt()){
            case 1:
                verificar();
                break;

            case 2:
                finalizar();
                break;

            default:
             System.exit(0);

        }

    }
    public static double finalizar(){
         valorTotal= 0;
        System.out.println("Visualize seu carrinho: ");
        for (int i = 0; i <listaCompras.size() ; i++) {
            System.out.println(listaCompras.get(i).getNomeProduto()+" "+listaCompras.get(i).getCodProduto()+" "+"R$ "+listaCompras.get(i).getPreco());
            valorTotal += listaCompras.get(i).getPreco();
        }
        System.out.println("O Valor total da compra é: R$ "+valorTotal+ " e você comprou "+listaCompras.size()+ " produtos em nossa loja.");
        System.out.println("Obrigado por comprar conosco, volte sempre! :)");

        cadastrarCompra();
        cadastrarVenda();
        mostrarMenuCliente();
        return valorTotal;
    }

    public static void cadastrarCompra(){
        compra1.setDataCompra(dataCompra);
        compra1.setValorTotal(valorTotal);
        compra1.setListaCompras(listaCompras);
        Compra compra = new Compra(compra1.getComprador(),compra1.getDataCompra(),compra1.getListaCompras(),compra1.getValorTotal());
        historicoCompras.put(compra.getComprador().getCpf(),compra);

    }
    public static Venda cadastrarVenda(){
        Venda.setVendedor(Vendedor.getSorteado());
        Venda.setDataRegistro(dataCompra);
        Venda.setListaCompras(listaCompras);
        Venda.setValorTotal(valorTotal);
        Venda venda = new Venda(Venda.getVendedor(),Venda.getComprador(),Venda.getDataRegistro(),Venda.getListaCompras(),Venda.getValorTotal());
       Venda.vendasGeral.add(venda);
        return venda;
    }
    public static void exibirHistoricoCompras(){

        try {
            boolean contemCpfAssociado = false;
            System.out.println("Informe seu CPF: ");
            String cpf = input.next();
            if (!(cpf.matches("\\d+"))){
                throw new UnsupportedOperationException();
            }
            for (Map.Entry<String, Compra> entry : historicoCompras.entrySet()) {
                if (entry.getKey().equals(cpf)){
                    contemCpfAssociado = true;
                    break;
                }
            }if (!(contemCpfAssociado)){
                System.out.println("CPF não está relacionado á nenhuma compra!");
            }else {
                System.out.println("-------------- DETALHES DA COMPRA ---------------------");
                for (Map.Entry<String, Compra> entry : historicoCompras.entrySet()) {
                    System.out.println("CPF DO COMPRADOR " + entry.getKey());
                    System.out.println("DATA DA COMPRA: "+entry.getValue().getDataCompra());
                    System.out.println("Produtos comprados: ");
                    for (int i = 0; i <entry.getValue().getListaCompras().size(); i++) {
                        System.out.println("Nome: "+entry.getValue().getListaCompras().get(i).getNomeProduto()+ "\t Preço: "+entry.getValue().getListaCompras().get(i).getPreco());
                    }
                    System.out.println("Quantidade de Itens adquiridos: " +entry.getValue().getListaCompras().size());
                    System.out.println("Valor Total da compra: "+ entry.getValue().getValorTotal());
                }}
        }catch (UnsupportedOperationException erro){
            System.err.println("CPF inválido, por favor, tente novamente.");
            exibirHistoricoCompras();
        }

        mostrarMenuCliente();
    }
    public static void mostrarCliente(){
        System.out.println("-------------- CLIENTES CADASTRADOS -----------------");
        for (int i = 0; i <clientesCadastrados.size() ; i++) {
            System.out.println("Nome: "+clientesCadastrados.get(i).getNome() + " E-mail: "+clientesCadastrados.get(i).getEmail()+ " CPF: "+clientesCadastrados.get(i).getCpf());
            System.out.println("------------------------------------------------------------");
        }

        Vendedor.mostrarMenuVendedor();
    }
}
