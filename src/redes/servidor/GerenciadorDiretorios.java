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

    public  String removerDiretorio(String caminho) {
        File  sistemaDeArquivos = new File(caminho);
        boolean deu =  sistemaDeArquivos.delete();
        if (deu) return "Diretorio removido no caminho "+caminho;
        else return "Deu ruim ao tentar remover o diretório "+caminho;
    }

    public  void listarConteudoDiretorio(String caminho) {
        File sistemaArquivos = new File(caminho);
        String [] resultado = sistemaArquivos.list();
        for (String s: resultado ) {
            System.out.println(s);
        }
    }
}
