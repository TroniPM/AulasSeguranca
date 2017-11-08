package objetos;

public class Objeto {

    private String mensagem;
    private String destino;
    private int tamanho;

    public Objeto(String mensagem, String destino, int tamanho) {
        super();
        this.mensagem = mensagem;
        this.destino = destino;
        this.tamanho = tamanho;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

}
