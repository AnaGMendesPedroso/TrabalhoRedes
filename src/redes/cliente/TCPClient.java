package redes.cliente;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.FileSystem;

public class TCPClient {

    public static String iniciaComunicacao(String message, String hostname) {
        Socket socket = null;
        try {
            int serverPort = 7896;
            socket = new Socket(hostname, serverPort);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            if (message.startsWith("4")) enviarArquivoServidor(message, out);
            else gerenciarDiretoriosArquivosServidor(message, out);

            String data = in.readUTF();
            data = "Recebido do Servidor: " + data;
            return data;
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Algo deu errado..";
    }

    private static void gerenciarDiretoriosArquivosServidor(String message, DataOutputStream out) throws IOException {
        out.writeUTF(message);
        out.flush();
    }

    private static void enviarArquivoServidor(String message, DataOutputStream out) throws IOException {
        System.out.println("Vai enviar " + message);
        String[] arquivo = message.split("#");
        out.writeUTF(message);
        out.flush();

        byte[] buffer = new byte[1048576];
        File file = new File(arquivo[1]);
        out.writeUTF(file.getName());
        out.flush();

        FileInputStream fos = new FileInputStream(arquivo[1]);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);

        while (true) {

            int bytesRead = fos.read(buffer);
            if (bytesRead == -1) break;
            objectOutputStream.write(buffer, 0, bytesRead - 1);
            objectOutputStream.flush();
        }
    }

}