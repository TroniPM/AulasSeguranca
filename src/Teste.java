
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Teste implements Serializable {

    private String seila;
    private Date data;

    public Teste() {
        super();
    }

    public Teste(String seila) {
        this.seila = seila;
        this.data = new Date();

    }

    public String getSeila() {
        return seila;
    }

    public void setSeila(String seila) {
        this.seila = seila;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public static String convertToString(Teste c) {
        try {
            String str;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(c);
            byte[] objeto = baos.toByteArray();
            str = Base64.encode(objeto);
            oos.close();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Teste convertFromString(String str) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(str));
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Teste) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        Teste t = new Teste("aaaaa");
        String representacaoStr = Teste.convertToString(t);
        Teste t2 = Teste.convertFromString(representacaoStr);
        System.out.println(representacaoStr);
        System.out.println(t2.getSeila());
        System.out.println(t2.getData());
    }

}
