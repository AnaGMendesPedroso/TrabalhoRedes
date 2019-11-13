package cliente;

import protocolo.Protocolo;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;

public class TCPClient {

    public Protocolo CriaPedido(int opcao, String endereco, String host){
        return CriaPedido(opcao, endereco, null, host);
    }

    public Protocolo CriaPedido(int opcao, String endereco, String destino, String host) {
        Protocolo protocolo = new Protocolo(opcao, endereco);

        try {
            if(opcao == 4){
                File file = new File(endereco);
                protocolo.setNomeArquivo(file.getName());
                protocolo.setArquivo(Files.readAllBytes(file.toPath()));
                protocolo.setEndereco2(destino);
            }
            protocolo = StartTCPClient(protocolo, host);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return protocolo;
    }

    private Protocolo StartTCPClient(Protocolo protocolo, String host) throws Exception{
        Socket socket = new Socket(host, 5555);

        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

        output.writeObject(protocolo);
        output.flush();

        Protocolo protocoloResposta = (Protocolo) input.readObject();

        input.close();
        output.close();
        socket.close();

        return protocoloResposta;
    }
}