package redes.servidor;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[5000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                System.out.println("PASSOU AQUI: "+new String(request.getData()));
                aSocket.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(),
                        request.getPort());
                
                salvarArquivo(request.getData(), request.getLength());
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

    public static void salvarArquivo(byte[] bytes, int tamanho) throws IOException {
        byte[] bytesCorte = new byte[tamanho];
        for(int i=0; i<tamanho; i++)
            bytesCorte[i] = bytes[i];

        File f = new File ("/home/nathan/Documentos/DevPC/Redes/Servidor/nomeDoArquivo.java");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream (f);
            fos.write (bytesCorte);
        } finally {
            if (fos != null) 
                try { 
                    fos.close(); 
                } catch (IOException ex) {

                }
        }
    }
}