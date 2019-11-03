package redes.cliente;

import java.net.*;
import java.io.*;

public class UDPClient{
    private DatagramSocket aSocket;
    private InetAddress aHost;
    private int serverPort;

    UDPClient(String host){
        try {
            aSocket = new DatagramSocket();
            aHost = InetAddress.getByName(host);
            serverPort = 6789;
        }catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        }
    }

    public void criarDiretorio(String caminho){
        byte [] m = caminho.getBytes();
        try {
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);
            retorno();
        }catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        } finally {
            if(aSocket != null) aSocket.close();
        }
    }

    private void retorno() throws IOException {
        byte[] buffer = new byte[5000];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        aSocket.receive(reply);
        System.out.println("Reply: " + new String(reply.getData()));
    }

    public static byte[] getBytes(File file) {
        int len = (int)file.length();
        byte[] sendBuf = new byte[len];
        FileInputStream inFile  = null;
        try {
            inFile = new FileInputStream(file);
            inFile.read(sendBuf, 0, len);

        } catch (FileNotFoundException fnfex) {

        } catch (IOException ioex) {

        }
        return sendBuf;
    }

    public void transferirArquivo(String dado){
        String[] qualquer = dado.split("[.]");
        System.out.println(qualquer[1]);
    }
}