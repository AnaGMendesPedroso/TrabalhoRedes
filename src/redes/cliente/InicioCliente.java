package redes.cliente;

import redes.servidor.GerenciadorDiretorios;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InicioCliente {
    private static UDPClient udpCliente = new UDPClient("127.0.0.1");

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
        String caminho = "";
        System.out.println("Escolha uma das opções a seguir: \n[1] Criar diretório\n" +
                "[2] Remover diretório\n" +
                "[3] Listar conteúdo de diretório\n" +
                "[4] Enviar arquivo\n" +
                "[5] Remover arquivo\n"+
                "[6] Finalizar trabalho");
        try {
            switch (t.nextInt()) {
                case 1:
                    System.out.println("OK! Vamos criar um diretório!");
                    System.out.println("Caminho: ");
                    caminho = t.next();
                    //GerenciadorDiretorios.criarDiretorio(caminho);
                    caminho = "1#"+caminho+"#";
                    udpCliente.criarDiretorio(caminho);
                    menu();
                    break;

                case 2:
                    System.out.println("Bora remover uns diretórios ae então!");
                    System.out.println("Manda o caminho do diretório que você quer apagar ae meu consagrado! ");
                    System.out.println("Caminho: ");
                    caminho = "2#"+caminho+"#";
                    //chama o udp client aqui passando essa caminho
                    menu();
                    break;

                    case 3:
                        System.out.println("Bora dar uns bisu nuns arquivos ae então");
                        System.out.println("Manda o caminho do diretório que você quer listar ae meu consagrado! ");
                        System.out.println("Caminho: ");
                        caminho = "3#"+caminho+"#";
                        //chama o udp client aqui passando essa caminho
                    menu();
                    break;

                case 4:
                    System.out.println("Beleza! Bora enviar um arquivão :)");
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
        }catch(InputMismatchException e) {
                    System.err.println("Ixi cara deu ruim nessa sua entrada ae :( \nTenta de novo que eu deixo :)");
            System.out.print("Aperte \"Enter\" para continuar.");
            t.nextLine();
            t.nextLine().matches("\n");
            }
        }
    }