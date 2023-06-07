package SistemaDeVendas;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Menu {

    public static void mostrarMenuSistema(){
        Scanner input = new Scanner(System.in);
      try{
          System.out.println("Bem vindo a D'OUROS BOLOS!");
          System.out.println("Informe: 1- Cliente \t 2- Vendedor \t 3- Sair");

          switch (input.nextInt()){
              case 1:
                  Vendedor.sortearVendedores();
                  Cliente.mostrarMenuCliente();
                  break;

              case 2:
                  Vendedor.adicionaVendedores();
                  Vendedor.mostrarMenuVendedor();
                  break;

              case 3:
                  System.exit(0);
              default:
                  throw new NumberFormatException();
          }
      }catch(NumberFormatException erro1){
          System.err.println("Entrada Inválida, tente novamente");
          mostrarMenuSistema();
      }catch (InputMismatchException erro2){
          System.err.println("Entrada Inválida, tente novamente");
          mostrarMenuSistema();
      }


    }
}
