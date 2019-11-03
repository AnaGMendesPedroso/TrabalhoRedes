package redes.servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
   private  static  GerenciadorDiretorios gerenciadorDiretorios = new GerenciadorDiretorios();
    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[5000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                String acaoConteudo = new String(request.getData());
                System.out.println("RECEBIDO PELO SERVIDOR:"+acaoConteudo);
                String[] acaoConteudo2 =  acaoConteudo.split("#");
                System.out.println("Servidor recebeu  a ação: " + acaoConteudo2[0].toUpperCase());
                System.out.println("Servidor recebeu  o conteúdo : " + acaoConteudo2[1].toUpperCase());
                String result = "";
                if (acaoConteudo2[0].equalsIgnoreCase("1")) {
                  result = gerenciadorDiretorios.criarDiretorio( acaoConteudo2[1]);
                } else if(acaoConteudo2[0].equalsIgnoreCase("2") || acaoConteudo2[0].equalsIgnoreCase("5") ){
                    result = gerenciadorDiretorios.removerDiretorio(acaoConteudo2[1]);
                }else if(acaoConteudo2[0].equalsIgnoreCase("3")){
                    gerenciadorDiretorios.listarConteudoDiretorio(acaoConteudo2[1]);
                }else if(acaoConteudo2[0].equalsIgnoreCase("4")){

                }
                DatagramPacket reply = new DatagramPacket(result.getBytes(), result.length(), request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }
    }
}