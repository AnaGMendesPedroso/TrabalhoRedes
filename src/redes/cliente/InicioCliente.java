package redes.cliente;

import redes.servidor.GerenciadorDiretorios;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InicioCliente {
    private static UDPClient udpCliente = new UDPClient();

    public static void main(String[] args) {
        System.out.println("||-------------------------------------------------------------------- ||");
        System.out.println("||                                Faculdade de Computação                                       ||");
        System.out.println("||                  Trabalho Prático Redes de Computadores                ||");
        System.out.println("||            Acadêmicos: Ana GM Pedroso, Nathan P Bispo             ||");
        System.out.println("||---------------------------------------------------------------------||\n");
        menu();
    }

    private static void menu() {
        Scanner t = new Scanner(System.in);
        int opcoes = 0;
        do {
            String caminho = null;
            System.out.println("Escolha uma das opções a seguir: \n[1] Criar diretório\n" +
                    "[2] Remover diretório\n" +
                    "[3] Listar conteúdo de diretório\n" +
                    "[4] Enviar arquivo\n" +
                    "[5] Remover arquivo\n"+
                    "[6] Finalizar trabalho");
            opcoes = t.nextInt();
            try {
                switch (opcoes) {
                    case 1:
                        System.out.println("OK! Vamos criar um diretório!");
                        System.out.println("Caminho: ");
                        caminho = t.next();
                        caminho = "1#"+caminho+"#";
                        break;

                    case 2:
                        System.out.println("Bora remover uns diretórios ae então!");
                        System.out.println("Manda o caminho do diretório que você quer apagar ae meu consagrado! ");
                        System.out.println("Caminho: ");
                        caminho = "2#"+caminho+"#";
                        //chama o udp client aqui passando essa caminho
                        break;

                        case 3:
                            System.out.println("Bora dar uns bisu nuns arquivos ae então");
                            System.out.println("Manda o caminho do diretório que você quer listar ae meu consagrado! ");
                            System.out.println("Caminho: ");
                            caminho = t.next();
                            caminho = "3#"+caminho+"#";
                            //chama o udp client aqui passando essa caminho
                        break;

                    case 4:
                        System.out.println("Beleza! Bora enviar um arquivão :)");
                        caminho = t.next();
                        caminho = "4#"+caminho+"#";
                        //chama o udp client aqui passando essa caminho
                        break;

                    case 5:
                        System.out.println("Removendo arquivo em 3..2..1..");
                        System.out.println("Manda o caminho do arquivo que você quer apagar ae meu consagrado! ");
                        System.out.println("Caminho: ");
                        caminho = "5#"+caminho+"#";
                        //chama o udp client aqui passando essa caminho
                        break;

                    case 6:
                        System.out.println("Trabalho finalizado com sucesso! Volte sempre :)");
                        break;
                }
                if (caminho != null) udpCliente.gerUDPClient(caminho);

            }catch(InputMismatchException e) {
                        System.err.println("Ixi cara deu ruim nessa sua entrada ae :( \nTenta de novo que eu deixo :)");
                System.out.print("Aperte \"Enter\" para continuar.");
                t.nextLine();
                t.nextLine().matches("\n");
            }
        } while(opcoes != 6);
    }
}