package redes.cliente;

import java.net.*;
import java.io.*;

class UDPClient{
    private DatagramSocket aSocket;
    private InetAddress aHost;
    private int serverPort;

    UDPClient(){
        try {
            aHost = InetAddress.getByName("127.0.0.1");
            serverPort = 6789;
        }catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        }
    }
    private void comunicaServidor(byte[] m){
        try {
            aSocket = new DatagramSocket();
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);
            byte[] buffer = new byte[5000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));
        }catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        } finally {
            if(aSocket != null) aSocket.close();
        }
    }

    void gerUDPClient(String action){
        int opcao = Integer.parseInt(action.charAt(0)+"");
        if(opcao == 1 || opcao == 2 || opcao == 5){
            comunicaServidor(action.getBytes());
        } else if (opcao == 4) {
            String []caminho = action.split("#");
            String aux = action.charAt(0)+"#";

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                outputStream.write(aux.getBytes());
                outputStream.write(getBytes(new File(caminho[1])));
                caminho[1] = "#"+caminho[1];
                outputStream.write(caminho[1].getBytes());
            } catch (IOException e) {
                System.out.println("");
            }
            comunicaServidor(outputStream.toByteArray());
        }

    }

    private static byte[] getBytes(File file) {
        int len = (int)file.length();
        byte[] sendBuf = new byte[len];
        FileInputStream inFile  = null;
        try {
            inFile = new FileInputStream(file);
            inFile.read(sendBuf, 0, len);

        } catch (FileNotFoundException fnfex) {
            System.out.println(fnfex);
        } catch (IOException ioex) {
            System.out.println(ioex);
        }
        return sendBuf;
    }
}