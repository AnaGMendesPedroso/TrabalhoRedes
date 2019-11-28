package protocolo;

import java.util.ArrayList;

public class Protocolo implements java.io.Serializable {
    private int operacao;
    private byte[] arquivo;
    private String endereco1;
    private String endereco2;
    private ArrayList<String>  lista;
    private String mensagem;
    private String nomeArquivo;

    public Protocolo(int operacao, String endereco1){
        this.operacao = operacao;
        this.endereco1 = endereco1;
    }

    public int getOperacao() {
        return operacao;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getEndereco1() {
        return endereco1;
    }

    public String getEndereco2() {
        return endereco2;
    }

    public void setEndereco2(String endereco2) {
        this.endereco2 = endereco2;
    }

    public ArrayList<String>  getLista() {
        return lista;
    }

    public void setLista(ArrayList<String> lista) {
        this.lista = lista;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
}
