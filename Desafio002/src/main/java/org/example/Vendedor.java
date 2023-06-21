package org.example;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

public class Vendedor {
   static Scanner input = new Scanner(System.in);
   private String nome, cpf, email,senha, senhaCripto;
   static   List<Vendedor> vendedoresCadastrados = new ArrayList<>();
   private static Vendedor sorteado;
   static Map<String,Venda> historicoVendas = new LinkedHashMap<>();

   public Vendedor(){}
    public Vendedor(String nome, String cpf, String email, String senha){
     this.nome = nome;
     this.cpf= cpf;
     this.email = email;
     this.senha = senha;

    }


    public static Vendedor getSorteado() {
        return sorteado;
    }

    public void setSorteado(Vendedor sorteado) {
        this.sorteado = sorteado;
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

    public static void adicionaVendedores(){
        vendedoresCadastrados.add(new Vendedor("Milena","55762902838","milena.alcantara.2106@gmail.com","123456ab"));
        vendedoresCadastrados.add(new Vendedor("Zefir","123456789","zefir.nere.alcantara@gmail.com", "123456abc"));
        vendedoresCadastrados.add(new Vendedor("Margarida","738485928264","meg.mesquita@gmail.com","123456cd"));
    }
    public static Vendedor sortearVendedores(){
     adicionaVendedores();
        Random sorteiaVedendor = new Random();
       sorteado= vendedoresCadastrados.get(sorteiaVedendor.nextInt(vendedoresCadastrados.size()));
        System.out.println("Olá eu sou "+sorteado.getNome()+" e estarei aqui para lhe auxiliar!");

        return sorteado;
    }

    public static void mostrarMenuVendedor(){
        try {
            System.out.println("---------------------------------------------------");
            System.out.println("Informe a ação desejada ");
            System.out.println("1- Cadastrar-se como Vendedor \t 2- Consultar histórico de vendas \t 3- Verificar lista de Clientes \t 4- Verificar Vendedores \t 5- Voltar ao Menu anterior");

            switch (input.nextInt()){
                case 1:
                    input.nextLine();
                    cadastrarVedendor();
                    break;

                case 2:
                    input.nextLine();
                    logarVendedor();
                    break;

                case 3:
                    input.nextLine();
                    Cliente.mostrarCliente();
                    break;

                case 4:
                    mostrarVendedores();
                    break;

                case 5:
                    Menu.mostrarMenuSistema();

                default:
                    throw new NumberFormatException();
            }

        }catch (NumberFormatException erro){
            System.err.println("Entrada Inválida, tente novamente");
            mostrarMenuVendedor();
        }catch (InputMismatchException erro2){
            System.err.println("Entrada Inválida, tente novamente");
           mostrarMenuVendedor();
        }
        mostrarMenuVendedor();
    }
    public static void cadastrarVedendor(){
        Vendedor vendedor = new Vendedor();
        try {
            System.out.println("Informe seu nome: ");
            vendedor.setNome(input.next());
            if (!(vendedor.getNome().matches("[a-zA-Z]+"))){
                throw new UnsupportedOperationException();
            }
            System.out.println("Informe seu CPF: ");
            vendedor.setCpf(input.next());
            if (!(vendedor.getCpf().matches("\\d+"))){
                throw new UnsupportedOperationException();
            }
            if (vendedor.getCpf().length()<11){
                throw new UnsupportedOperationException();
            }
            System.out.println("Informe seu e-mail: ");
            vendedor.setEmail(input.next());
            if (!(vendedor.getEmail().contains("@")) || !(vendedor.getEmail().contains("."))){
                System.out.println("Email inválido, por favor digite novamente");
                vendedor.setEmail(input.next());
            }
            System.out.println("Informe uma senha: ");
            vendedor.setSenha(input.next());
            if (!(vendedor.getSenha().matches("\\d+")) && !(vendedor.getSenha().matches("[a-zA-Z]+")) && vendedor.getSenha().length()<8){
                System.out.println("Senha inválida, tente novamente");
                vendedor.setSenha(input.next());
            }
            vendedor.setSenhaCripto(BCrypt.hashpw(vendedor.getSenha(), BCrypt.gensalt()));
            System.out.println(BCrypt.checkpw(vendedor.getSenha(), vendedor.getSenhaCripto()));

            boolean vendendorCadastrado = false;
            for (Vendedor vendedorExistente: vendedoresCadastrados) {
                if (vendedorExistente.getCpf().equals(vendedor.getCpf()) || vendedorExistente.getEmail().equals(vendedor.getEmail())){
                    vendendorCadastrado = true;
                    break;
                }
            }
            if (vendendorCadastrado){
                System.out.println("Vedendor já cadastrado.");
            }else{
                vendedoresCadastrados.add(vendedor);
                System.out.println("Cadastro realizado com sucesso!");
            }

        }catch (UnsupportedOperationException erro){
            System.err.println("Entrada de dados inválida, tente novamente");
            cadastrarVedendor();
        }
        mostrarMenuVendedor();
    }
    public static void logarVendedor(){
       try {
           System.out.println("Informe seu e-mail");
           String email = input.next();
           if (!(email.contains("@"))){
               throw new UnsupportedOperationException();
           }
           System.out.println("Informe sua senha");
           String senha = input.next();
           boolean vendedorLogado = false;
           for (Vendedor vendedorExistente: vendedoresCadastrados) {
               if (vendedorExistente.getEmail().equals(email)){
               if (BCrypt.checkpw(senha,vendedorExistente.getSenhaCripto())){
                   vendedorLogado = true;
                   break;
               }else {
                   System.out.println("Senha ou e-mail incorretos");
                   vendedorLogado = true;
                   logarVendedor();
                   break;
               }}
           }
           if (!(vendedorLogado)){
               System.out.println("Ops... vi aqui que seu e-mail não consta em nosso sistema");
               System.out.println("Deseja efetuar seu cadastro? \n 1- Sim \t 2- Não ");
               switch (input.nextInt()){
                   case 1:
                       input.nextLine();
                       cadastrarVedendor();
                       break;

                   case 2:
                       mostrarMenuVendedor();
                       break;

                   default:
                    throw new NumberFormatException();
               }
           }else {
               System.out.println("Vedendor Logado.");
               pesquisarHistoricoVendas(Cliente.cadastrarVenda());
           }
       }catch (UnsupportedOperationException erro){
           System.err.println("E-mail inválido, por favor tente novamente.");
           logarVendedor();
       }

    }
    public static void pesquisarHistoricoVendas(Venda venda){
       try {
           historicoVendas.put(Venda.getVendedor().getEmail(),venda);
           System.out.println("Informe a opção desejada: \n 1- Consultar por e-mail \n 2- Lista Geral de Vendas");
           switch (input.nextInt()){

               case 1:
                   boolean contemEmailAssociado = false;
                   System.out.println("Informe seu email novamente: ");
                   String email = input.next();
                   if (!(email.contains("@"))){
                       throw new UnsupportedOperationException();
                   }
                   for (Map.Entry<String, Venda> entry : historicoVendas.entrySet()) {
                       if (entry.getKey().equals(email)){
                           contemEmailAssociado = true;
                           break;
                       }
                   }if (!(contemEmailAssociado)){
                   System.out.println("E-MAIL não está relacionado á nenhuma compra!");
               }else {
                   System.out.println("-------------- DETALHES DA VENDA ---------------------");
                   for (Map.Entry<String, Venda> entry : historicoVendas.entrySet()) {
                       System.out.println("NOME DO CLIENTE: " + entry.getValue().getComprador().getNome());
                       System.out.println("DATA DE REGISTRO: "+entry.getValue().getDataRegistro()+" \n VENDEDOR RESPONSÁVEL: "+entry.getValue().getVendedor().getNome());
                       System.out.println("Produtos comprados: ");
                       for (int i = 0; i <entry.getValue().getListaCompras().size(); i++) {
                           System.out.println("Produto: "+entry.getValue().getListaCompras().get(i).getNomeProduto()+ "\t Preço: "+entry.getValue().getListaCompras().get(i).getPreco()+ "\t CÓD: "+entry.getValue().getListaCompras().get(i).getCodProduto());
                       }
                       System.out.println("Quantidade de Itens adquiridos: " +entry.getValue().getListaCompras().size());
                       System.out.println("Valor Total da compra: "+ entry.getValue().getValorTotal());
                   }}
                   break;

               case 2:
                   for (Map.Entry<String, Venda> entry : historicoVendas.entrySet()) {
                       System.out.println("NOME DO CLIENTE: " + entry.getValue().getComprador().getNome());
                       System.out.println("DATA DE REGISTRO: " + entry.getValue().getDataRegistro());
                       System.out.println("Valor Total da compra: " + entry.getValue().getValorTotal());
                   }
                   break;

               default:
              throw new NumberFormatException();
           }
       }catch (UnsupportedOperationException erro){
           System.err.println("E-mail inválido, informe-o novamente.");
           pesquisarHistoricoVendas(Cliente.cadastrarVenda());
       }
        mostrarMenuVendedor();
    }

    public static void  mostrarVendedores(){
        System.out.println("----------- LISTA DE VENDEDORES ---------------------");
        for (int i = 0; i <vendedoresCadastrados.size() ; i++) {
            System.out.println("Vendedor: "+vendedoresCadastrados.get(i).getNome()+ " | E-mail: "+vendedoresCadastrados.get(i).getEmail()+ " | CPF: "+vendedoresCadastrados.get(i).getCpf());
            System.out.println("---------------------------------------------------------");
        }
    }
}
