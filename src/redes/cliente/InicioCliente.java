package redes.cliente;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InicioCliente {
    public static void main(String[] args) {
        System.out.println("||\t--------------------------------------------------------\t-||");
        System.out.println("||\tFaculdade de Computação\t-||||");
        System.out.println("||\tTrabalho Prático Redes de Computadores\t-||");
        System.out.println("||\tAcadêmicos: Ana GM Pedroso, Nathan P Bispo\t-||");
        System.out.println("||---------------------------------------------------------------------||\n");
        menu();
    }

    private static void menu() {
        Scanner t = new Scanner(System.in);
        int opcoes = 0;
        String caminho = "";
        System.out.println("Seu IP:");
        String hostname = t.next();

        do {
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

                        System.out.println("Nome do novo diretório: ");
                        caminho = t.next();
                        caminho = "1#"+caminho+"#";
                        System.out.println(TCPClient.iniciaComunicacao(caminho,hostname));
                        break;
                        //172.28.0.1
                        // /home/anapedroso/Documents/Redes/EmpresaDepois.txt
                    case 2:
                        System.out.println("Nome do diretório que deseja apagar: ");
                        caminho = t.next();
                        caminho = "2#"+caminho+"#";
                        System.out.println(TCPClient.iniciaComunicacao(caminho,hostname));
                        break;

                    case 3:
                        System.out.println("Nome do diretório que deseja listar o conteúdo: ") ;
                        caminho = t.next();
                        caminho = "3#"+caminho+"#";
                        System.out.println(TCPClient.iniciaComunicacao(caminho,hostname));
                        break;

                    case 4:
                        System.out.println("Caminho do arquivo em sua máquina que deseja enviar:");
                        caminho = t.next();
                        caminho = "4#"+caminho+"#";
                        System.out.println(TCPClient.iniciaComunicacao(caminho,hostname));
                        break;

                    case 5:
                        System.out.println("Caminho do arquivo que você quer apagar: ");
                        caminho = t.next();
                        caminho = "5#"+caminho+"#";
                        System.out.println(TCPClient.iniciaComunicacao(caminho,hostname));
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
        } while(opcoes != 6);
    }
}