package servidor;

import protocolo.Protocolo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GerenciadorDiretorios {
    private final File arquivoServidor;
    private Protocolo protocolo;

    GerenciadorDiretorios(Protocolo protocolo) {
        this.protocolo = protocolo;
        this.arquivoServidor = new File("arquivoServidor");
        this.arquivoServidor.mkdir();
    }

    Protocolo init() {
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
                protocolo.setMensagem(salvarConteudoDiretorio(protocolo.getArquivo(), protocolo.getEndereco2(),protocolo.getNomeArquivo()));
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
        File sistemaDeArquivos = new File(arquivoServidor.getAbsolutePath() +"/"+ caminhoArquivo);
        if(sistemaDeArquivos.exists()){
            sistemaDeArquivos.delete();
            return "Arquivo "+sistemaDeArquivos.getAbsolutePath()+" removido!";
        } else
            return "Caminho inválido";
    }

    private String removerDiretorio(String nomeDiretorio) {
        File sistemaDeArquivos = new File(arquivoServidor.getAbsolutePath() +"/"+ nomeDiretorio);

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

    private String salvarConteudoDiretorio(byte[] Arquivo, String enderecoDestino, String nomeDoArquivo) {
        try {
            new FileOutputStream(
                    new File(arquivoServidor.getAbsolutePath() + "/" + enderecoDestino + "/" + nomeDoArquivo)
            ).write(Arquivo);
        } catch (IOException e) {
            return "Ouve um erro na persistencia do arquivo";
        }
        return "Arquivo enviado com sucesso!";
    }
}
