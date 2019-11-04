package redes.servidor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GerenciadorDiretorios {

    GerenciadorDiretorios(){}
    String criarDiretorio(String caminho) {
        File  sistemaDeArquivos = new File(caminho);
        boolean deu = sistemaDeArquivos.mkdir();
        if (deu) return "Diretorio criado no caminho "+caminho;
        else return "Deu ruim ao tentar criar o diretório "+caminho;
    }

    String removerDiretorio(String caminho) {
        File  sistemaDeArquivos = new File(caminho);
        boolean deu =  sistemaDeArquivos.delete();
        if (deu) return "Diretorio removido no caminho "+caminho;
        else return "Deu ruim ao tentar remover o diretório "+caminho;
    }

    void listarConteudoDiretorio(String caminho) {
        File sistemaArquivos = new File(caminho);
        String [] resultado = sistemaArquivos.list();
        for (String s: resultado ) {
            System.out.println(s);
        }
    }

    void salvarConteudoDiretorio(String caminho, String conteudo, int tamanho) {
        byte[] bytes = conteudo.getBytes();
        String[] nome = caminho.split("/");
        System.out.println("CAMINHO "+nome[nome.length-1]);
        File f = new File(nome[nome.length-1]);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            fos.write(bytes);
        } catch (FileNotFoundException e){

        } catch (IOException e){

        } finally {
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException ex) {

                }
        }
    }
}
