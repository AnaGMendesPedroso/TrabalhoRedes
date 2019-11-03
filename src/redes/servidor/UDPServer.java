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
                String[] acaoConteudo = new String(request.getData()).split("#");
                System.out.println("Servidou recebeu o pacotão com a ação: " + acaoConteudo[0]);
                System.out.println("Servidou recebeu o pacotinho com o conteúdo : " + acaoConteudo[1]);
                aSocket.receive(request);

                if (acaoConteudo[0].equalsIgnoreCase("1")) {
                    String result = gerenciadorDiretorios.criarDiretorio( acaoConteudo[1]);
                    DatagramPacket reply = new DatagramPacket(result.getBytes(), result.length(), request.getAddress(), request.getPort());
                    aSocket.send(reply);
                }
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