package redes.servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String args[]) {
        try {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true){

                System.out.println("Manda um trem ae...");
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    GerenciadorDiretorios gerenciadorDiretorios = new GerenciadorDiretorios();

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try { // an echo server
            String data = in.readUTF();
            System.out.println("Pacote:" + data);
            String[] acaoConteudo = data.split("#");
            System.out.println("Servidor recebeu  a ação: " + acaoConteudo[0].toUpperCase());
            System.out.println("Servidor recebeu  o conteúdo: " + acaoConteudo[1].toUpperCase());


            if (acaoConteudo[0].equalsIgnoreCase("1")) {
                out.writeUTF(gerenciadorDiretorios.criarDiretorio(acaoConteudo[1]));
                out.flush();
            } else if (acaoConteudo[0].equalsIgnoreCase("2")) {
                out.writeUTF(gerenciadorDiretorios.removerDiretorio(acaoConteudo[1]));
                out.flush();
            } else if (acaoConteudo[0].equalsIgnoreCase("3")) {
                String conteudo = gerenciadorDiretorios.listarConteudoDiretorio(acaoConteudo[1]);
                out.writeUTF(conteudo);
                out.flush();
            } else if (acaoConteudo[0].equalsIgnoreCase("4")) {
                String nomeArquivo = in.readUTF();
                String result = gerenciadorDiretorios.salvarConteudoDiretorio(nomeArquivo, clientSocket.getInputStream());
                out.writeUTF(result);
                out.flush();
            }
            else if( acaoConteudo[0].equalsIgnoreCase("5")){
                out.writeUTF(gerenciadorDiretorios.removerArquivo(acaoConteudo[1]));
                out.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
