package redes.servidor;

import java.io.File;

public class GerenciadorDiretorios {

    public GerenciadorDiretorios(){}
    public  String criarDiretorio(String caminho) {
        File  sistemaDeArquivos = new File(caminho);
        boolean deu = sistemaDeArquivos.mkdir();
        if (deu) return "Diretorio criado no caminho "+caminho;
        else return "Deu ruim ao tentar criar o diretório "+caminho;
    }

    public  void removerDiretorio(String caminho) {
        File  sistemaDeArquivos = new File(caminho);
        boolean deu =  sistemaDeArquivos.delete();
        if (deu) System.out.println("Diretorio removido");
        else System.out.println("Deu ruim  na remoção...");
    }

    public  void listarConteudoDiretorio(String caminho) {
        File sistemaArquivos = new File(caminho);
        String [] resultado = sistemaArquivos.list();
        for (String s: resultado ) {
            System.out.println(s);
        }
    }

    public static void removerArquivo(String caminho) {
    }
}
