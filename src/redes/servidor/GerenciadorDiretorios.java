package redes.servidor;


import java.io.*;
import java.util.Objects;

public class GerenciadorDiretorios {
    private final File arquivoServidor;
    public GerenciadorDiretorios(){
       this.arquivoServidor = new File("arquivoServidor");
       arquivoServidor.mkdir();
    }
    public String criarDiretorio(String nomeNovoDiretorio) {
        File sistemaDeArquivos = new File(arquivoServidor.getAbsolutePath() + "/"+nomeNovoDiretorio);
        boolean deu = sistemaDeArquivos.mkdir();
        if (deu) return "Diretorio criado no caminho "+sistemaDeArquivos.getAbsolutePath();
        else return "Deu ruim ao tentar criar o diretório "+nomeNovoDiretorio;
    }
    public String removerArquivo(String caminhoArquivo) {
        File  sistemaDeArquivos = new File(arquivoServidor.getAbsolutePath() +"/"+ caminhoArquivo);
        if(sistemaDeArquivos.exists()){
            sistemaDeArquivos.delete();
            return "Arquivo "+sistemaDeArquivos.getAbsolutePath()+" removido!";
        }
        else return "Caminho inválido";
    }

    public String removerDiretorio(String nomeDiretorio) {
        File  sistemaDeArquivos = new File(arquivoServidor.getAbsolutePath() +"/"+ nomeDiretorio);

        //Remove um diretório não vazio
        if(sistemaDeArquivos.list().length > 0){
            for (File arquivoNoDiretorio : sistemaDeArquivos.listFiles()) {
                arquivoNoDiretorio.delete();
            }
        }
        boolean deu =  sistemaDeArquivos.delete();
        if (deu) return "Diretório "+sistemaDeArquivos.getAbsolutePath()+" removido!";
        else return "Deu ruim ao tentar remover o diretório";
    }

    public String listarConteudoDiretorio(String caminho) {
        File sistemaArquivos = new File(arquivoServidor.getAbsolutePath() + "/"+caminho);
        String[] conteudo = sistemaArquivos.list();
        String returnFinal = "";
        for (String nome : conteudo ) {
            returnFinal = "\n"+nome+"\n"+returnFinal;
        }
        return returnFinal;
    }

    public String salvarConteudoDiretorio(String nameFile, InputStream clientInput ) throws IOException {
        byte[] buffer = new byte[1048576];

        FileOutputStream fos = new FileOutputStream(arquivoServidor.getAbsolutePath()+"/"+nameFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(clientInput);

        while (true) {

            int bytesRead = objectInputStream.read(buffer);
            if (bytesRead == -1) break;
            fos.write(buffer, 0, bytesRead);
        }
        fos.flush();
        objectInputStream.close();


        System.out.println("Arquivo "+nameFile);
            return "Arquivo enviado com sucesso!";

        }
    }
