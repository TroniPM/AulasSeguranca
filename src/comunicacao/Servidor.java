package comunicacao;

import java.net.*;
import java.io.*;

public class Servidor implements Serializable {

    /**
     *
     */
    public TrocaMensagem trocaMensagem;
    private static final long serialVersionUID = 1L;
    public ServerSocket serverSocket;
    public Socket socket;

    public Servidor(Integer porta) {
        try {
            serverSocket = new ServerSocket(porta.intValue());
            System.out.println("Aguardando comunicação em: " + serverSocket.getLocalSocketAddress().toString());
            socket = serverSocket.accept();
            System.out.println("Conectado com: " + socket.getInetAddress().toString());
            trocaMensagem = new TrocaMensagem(socket);
            Thread t1 = new Thread(trocaMensagem);
            t1.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviar(String mensagem) {
        try {
            trocaMensagem.envia(mensagem);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String receber() {
        return trocaMensagem.receber();
    }
}
