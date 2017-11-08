package comunicacao;

import java.io.*;
import java.net.*;

public class Cliente {

    public TrocaMensagem trocaMensagem;
    public Socket socket;

    public void conecta(String host, Integer port) {
        try {
            socket = new Socket(host, port.intValue());
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

    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
