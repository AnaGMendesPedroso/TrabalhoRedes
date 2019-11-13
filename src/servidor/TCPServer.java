package servidor;

import protocolo.Protocolo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private GerenciadorDiretorios gerenciadorDiretorios;
    private ServerSocket serverSocket;

    private void criarServerSocket(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);
    }

    private Socket esperaConexao() throws IOException {
        Socket socket = serverSocket.accept();
        return socket;
    }

    private void fechaSocket(Socket socket) throws IOException {
        socket.close();
    }

    private void trataConexao(Socket socket) throws IOException {
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            System.out.println("Pedido recebido...");
            Protocolo protocolo = (Protocolo) input.readObject();
            gerenciadorDiretorios = new GerenciadorDiretorios(protocolo);

            output.writeObject(gerenciadorDiretorios.init());
            output.flush();

            input.close();
            output.close();
        } catch (IOException e) {
            System.out.println("TRATA CONEXAO " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            fechaSocket(socket);
        }
    }

    public static void main(String[] args) {
        try {
            TCPServer server = new TCPServer();
            System.out.println("Aguardando conexao...");
            server.criarServerSocket(5555);

            while (true) {
                Socket socket = server.esperaConexao();
                System.out.println("Cliente conectado...");
                server.trataConexao(socket);
                System.out.println("Cliente finalizado.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
