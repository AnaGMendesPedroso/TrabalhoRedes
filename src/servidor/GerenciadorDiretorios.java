package servidor;

import protocolo.Protocolo;

import java.io.*;

public class GerenciadorDiretorios {
    private final File arquivoServidor;
    private Protocolo protocolo;

    GerenciadorDiretorios(Protocolo protocolo) {
        this.protocolo = protocolo;
        this.arquivoServidor = new File("arquivoServidor");
        this.arquivoServidor.mkdir();
    }

    public Protocolo init() throws IOException {
        switch (protocolo.getOperacao()){
            case 1:
                protocolo.setMensagem(criarDiretorio(protocolo.getEndereco1()));
                break;
            case 2:
                protocolo.setMensagem(removerDiretorio(protocolo.getEndereco1()));
                break;
            case 3:
                protocolo.setLista(listarConteudoDiretorio(protocolo.getEndereco1()));
                break;
            case 4:
                protocolo.setMensagem(salvarConteudoDiretorio(protocolo.getArquivo(), protocolo.getEndereco2()));
                break;
            case 5:
                protocolo.setMensagem(removerArquivo(protocolo.getEndereco1()));
                break;
            default:
                protocolo.setMensagem("Operacao invalida");
        }
        return protocolo;
    }

    private String criarDiretorio(String nomeNovoDiretorio) {
        File file = new File(arquivoServidor.getAbsolutePath() + "/"+nomeNovoDiretorio);
        return ((file).mkdir())
                ? "Diretorio criado no caminho "+file.getAbsolutePath()
                : "Deu ruim ao tentar criar o diretório "+nomeNovoDiretorio;
    }

    private String removerArquivo(String caminhoArquivo) {
        File  sistemaDeArquivos = new File(arquivoServidor.getAbsolutePath() +"/"+ caminhoArquivo);
        if(sistemaDeArquivos.exists()){
            sistemaDeArquivos.delete();
            return "Arquivo "+sistemaDeArquivos.getAbsolutePath()+" removido!";
        } else
            return "Caminho inválido";
    }

    private String removerDiretorio(String nomeDiretorio) {
        File  sistemaDeArquivos = new File(arquivoServidor.getAbsolutePath() +"/"+ nomeDiretorio);

        //Remove um diretório não vazio
        if(sistemaDeArquivos.list().length > 0){
            for (File arquivoNoDiretorio : sistemaDeArquivos.listFiles()) {
                arquivoNoDiretorio.delete();
            }
        }
        return (sistemaDeArquivos.delete())
            ? "Diretório "+sistemaDeArquivos.getAbsolutePath()+" removido!"
            : "Deu ruim ao tentar remover o diretório";
    }

    private String[] listarConteudoDiretorio(String caminho) {
        return (new File(arquivoServidor.getAbsolutePath() + "/"+caminho)).list();
    }

    private String salvarConteudoDiretorio(File arquivo, String destino) throws IOException {
        FileInputStream arquivoInput = new FileInputStream(arquivo);
        FileOutputStream outputStream = new FileOutputStream(arquivoServidor.getAbsolutePath()+"/"+destino+"/"+arquivo.getName());
        int size = 0;
        while ((size = arquivoInput.read()) != -1) {
            outputStream.write(size);
        }
        arquivoInput.close();
        outputStream.close();
        return "Arquivo enviado com sucesso!";
    }
}
