package cliente;

import protocolo.Protocolo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InicioCliente {
    public static TCPClient client;
    public static void main(String[] args) {
        client = new TCPClient();
        System.out.println("||----------------------------------------------------------||");
        System.out.println("||\tFaculdade de Computação\t ---------------------------||");
        System.out.println("||\tTrabalho Prático Redes de Computadores  ------------||");
        System.out.println("||\tAcadêmicos: Ana GM Pedroso, Nathan P Bispo ---------||");
        System.out.println("||----------------------------------------------------------||\n");
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
                        System.out.println((client.CriaPedido(1, caminho, hostname)).getMensagem());
                        break;
                    case 2:
                        System.out.println("Nome do diretório que deseja apagar: ");
                        caminho = t.next();
                        System.out.println((client.CriaPedido(2, caminho, hostname)).getMensagem());
                        break;

                    case 3:
                        System.out.println("Nome do diretório que deseja listar o conteúdo: ") ;
                        caminho = t.next();
                        Protocolo protocolo = client.CriaPedido(3, caminho, hostname);
                        System.out.println(protocolo.getMensagem());
                        for (String i : protocolo.getLista())
                            System.out.println(i);
                        break;

                    case 4:
                        System.out.println("Caminho do arquivo em sua máquina que deseja enviar: \nExemplo:/home/user/Downloads/arquivoExemplo.pdf");
                        caminho = t.next();
                        System.out.println("Qual o caminho de destino no servidor?");
                        String destino = t.next();
                        System.out.println((client.CriaPedido(4, caminho, destino, hostname)).getMensagem());
                        break;

                    case 5:
                        System.out.println("Caminho do arquivo no servidor que você quer apagar: ");
                        caminho = t.next();
                        System.out.println((client.CriaPedido(5, caminho, hostname)).getMensagem());
                        break;

                    case 6:
                        System.out.println("Trabalho finalizado com sucesso!");
                        break;
                }
            }catch(InputMismatchException e) {
                System.err.println("Entrada inválida. Tente novamente");
                System.out.print("Aperte \"Enter\" para continuar.");
                t.nextLine();
                t.nextLine().matches("\n");
            }
        } while(opcoes != 6);
    }
}