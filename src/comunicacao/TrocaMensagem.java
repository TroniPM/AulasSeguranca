package comunicacao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TrocaMensagem implements Runnable {

    private Socket socket;
    private DataOutputStream dadosSaida;
    private DataInputStream dadosEntrada;
    private String data;

    public TrocaMensagem(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            data = null;
            try {
                Tela.jLabelStatus.setText("Conectado com: " + socket.getInetAddress().toString());
                System.out.println("Conectado...: " + Thread.currentThread().getName());
                dadosEntrada = new DataInputStream(socket.getInputStream());
                String mensagemDesencriptada = Tela.desencripta(dadosEntrada.readUTF());
                Tela.jTextAreaEntrada.append(mensagemDesencriptada);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void envia(String mensagem) throws IOException {
        if (socket != null) {
            try {
                dadosSaida = new DataOutputStream(socket.getOutputStream());
                String mensagemEncriptada = Tela.encripta(mensagem + "\n");
                dadosSaida.writeUTF(mensagemEncriptada);
                dadosSaida.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void encerrar() throws IOException {
        if (socket != null) {
            dadosSaida.close();
            socket.close();
        }
    }

    public String receber() {
        return data;
    }

}
