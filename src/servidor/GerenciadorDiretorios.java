package servidor;

import protocolo.Protocolo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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
            : "Não foi possível criar o diretório "+nomeNovoDiretorio;
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
            : "Não foi possível remover o diretório";
    }

    private ArrayList<String>  listarConteudoDiretorio(String caminho) {
        File diretorio = new File(arquivoServidor.getAbsolutePath() + "/"+caminho);
        File[] arquivos = diretorio.listFiles();
        ArrayList<String> listaConteudo = new ArrayList<>();
        if(arquivos != null && arquivos.length > 0){
            for (File arquivo: arquivos) {
                listaConteudo.add(" |--> "+ arquivo.getName());
                if (arquivo.isDirectory()){
                    listarConteudoDiretorio(arquivo.getAbsolutePath());
                }
            }
        }else{
            listaConteudo.add("Diretório vazio");
        }
        return listaConteudo;
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
