package redes;

import java.io.File;

public class GerenciadorDiretorios {

    public static void criarDiretorio(String caminho) {
        File  sistemaDeArquivos = new File(caminho);
        boolean deu = sistemaDeArquivos.mkdir();
        if (deu) System.out.println("Diretorio criado! ");
        else System.out.println("Deu ruim..");
    }

    public static void removerDiretorio(String caminho) {
        File  sistemaDeArquivos = new File(caminho);
        boolean deu =  sistemaDeArquivos.delete();
        if (deu) System.out.println("Diretorio removido");
        else System.out.println("Deu ruim  na remoção...");
    }

    public static void listarConteudoDiretorio(String caminho) {
        File sistemaArquivos = new File(caminho);
        String [] resultado = sistemaArquivos.list();
        for (String s: resultado ) {
            System.out.println(s);
        }
    }

    public static void removerArquivo(String caminho) {
    }
}
