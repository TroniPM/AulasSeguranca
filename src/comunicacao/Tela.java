package comunicacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.BorderFactory;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import criptografia.AES;
import objetos.Objeto;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.swing.SwingUtilities;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class Tela extends javax.swing.JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    {
        // Set Look & Feel
        try {
            javax.swing.UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JButton jButtonIniciar;
    private JButton jButtonEnviar;
    public static JTextArea jTextAreaEntrada;
    private JButton jButtonEncerrar;
    public static JLabel jLabelStatus;
    private JPanel jPanel1;
    private JTextField jTextFieldPorta;
    private JTextField jTextFieldIp;
    private JRadioButton jRadioButtonCliente;
    private JRadioButton jRadioButtonServidor;
    public static Servidor servidor;
    private JTextField jTextFieldSaida;
    public static Cliente cliente;
    private ButtonGroup buttonGroup;
    private KeyGenerator chaveGerada;
    private SecretKey chaveSecreta;
    private static AES abc;

    /**
     * Auto-generated main method to display this JFrame
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tela inst = new Tela();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public Tela() {
        super();
        initGUI();
    }

    private void initGUI() {
        try {
            AnchorLayout thisLayout = new AnchorLayout();
            getContentPane().setLayout(thisLayout);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            getContentPane().setBackground(new java.awt.Color(212, 208, 200));
            {
                try {
                    chaveGerada = KeyGenerator.getInstance("AES");
                    chaveGerada.init(256);
                    chaveSecreta = chaveGerada.generateKey();

                    String chaveSecretaManual = "ABCDEFGHIJKLMNOPQRSTUVWXYZASDFGH";
                    byte[] chaveManual = chaveSecretaManual.getBytes("UTF-8");

                    System.out.println("Chave: " + Base64.encode(chaveManual));

                    abc = new AES();
                    abc.setPadding(new PKCS7Padding());
//					abc.setKey(chaveSecreta.getEncoded());
                    abc.setKey(chaveManual);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            {
                jTextFieldSaida = new JTextField();
                getContentPane().add(jTextFieldSaida,
                        new AnchorConstraint(782, 979, 846, 23, AnchorConstraint.ANCHOR_REL,
                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                jTextFieldSaida.setPreferredSize(new java.awt.Dimension(352, 29));
                jTextFieldSaida.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
                jTextFieldSaida.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        if (jRadioButtonServidor.isSelected()) {
                            try {
                                servidor.trocaMensagem.envia(jTextFieldSaida.getText());
                                jTextFieldSaida.setText("");
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                cliente.trocaMensagem.envia(jTextFieldSaida.getText());
                                jTextFieldSaida.setText("");
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
            {
                jPanel1 = new JPanel();
                BorderLayout jPanel1Layout = new BorderLayout();
                jPanel1.setLayout(jPanel1Layout);
                getContentPane().add(jPanel1, new AnchorConstraint(926, 979, 994, 23, AnchorConstraint.ANCHOR_REL,
                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                jPanel1.setPreferredSize(new java.awt.Dimension(352, 31));
                jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                {
                    jLabelStatus = new JLabel();
                    jPanel1.add(jLabelStatus, BorderLayout.CENTER);
                    jLabelStatus.setText("Status");
                    jLabelStatus.setPreferredSize(new java.awt.Dimension(340, 13));
                }
            }
            {
                buttonGroup = new ButtonGroup();
            }
            {
                jTextFieldPorta = new JTextField();
                getContentPane().add(jTextFieldPorta,
                        new AnchorConstraint(149, 968, 204, 808, AnchorConstraint.ANCHOR_REL,
                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                jTextFieldPorta.setPreferredSize(new java.awt.Dimension(59, 25));
                jTextFieldPorta.setText("5000");
            }
            {
                jTextFieldIp = new JTextField();
                getContentPane().add(jTextFieldIp, new AnchorConstraint(149, 792, 204, 395, AnchorConstraint.ANCHOR_REL,
                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                jTextFieldIp.setPreferredSize(new java.awt.Dimension(146, 25));
                jTextFieldIp.setEnabled(false);
                jTextFieldIp.setEditable(false);
                jTextFieldIp.setText("");
                jTextFieldIp.setBackground(new Color(220, 220, 220));
            }
            {
                jRadioButtonCliente = new JRadioButton();
                getContentPane().add(jRadioButtonCliente,
                        new AnchorConstraint(144, 392, 208, 218, AnchorConstraint.ANCHOR_REL,
                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                jRadioButtonCliente.setText("Cliente");
                jRadioButtonCliente.setPreferredSize(new java.awt.Dimension(64, 29));
                jRadioButtonCliente.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        jTextFieldIp.setEnabled(true);
                        jTextFieldIp.setEditable(true);
                        jTextFieldIp.setBackground(new Color(255, 255, 255));
                        jTextFieldIp.setText("localhost");
                    }
                });
                buttonGroup.add(jRadioButtonCliente);
            }
            {
                jRadioButtonServidor = new JRadioButton();
                getContentPane().add(jRadioButtonServidor,
                        new AnchorConstraint(144, 218, 208, 28, AnchorConstraint.ANCHOR_REL,
                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                jRadioButtonServidor.setText("Servidor");
                jRadioButtonServidor.setPreferredSize(new java.awt.Dimension(70, 29));
                jRadioButtonServidor.setSelected(true);
                jRadioButtonServidor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        jTextFieldIp.setEnabled(false);
                        jTextFieldIp.setEditable(false);
                        jTextFieldIp.setText("");
                        jTextFieldIp.setBackground(new Color(220, 220, 220));
                    }
                });
                buttonGroup.add(jRadioButtonServidor);
            }

            {
                jButtonEncerrar = new JButton();
                getContentPane().add(jButtonEncerrar,
                        new AnchorConstraint(857, 979, 915, 639, AnchorConstraint.ANCHOR_REL,
                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                jButtonEncerrar.setText("Encerrar Conex�o");
                jButtonEncerrar.setPreferredSize(new java.awt.Dimension(125, 26));
                jButtonEncerrar.setEnabled(false);
                jButtonEncerrar.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        if (jRadioButtonServidor.isSelected()) {
                            try {
                                servidor.trocaMensagem.encerrar();
                                jButtonIniciar.setEnabled(true);
                                jButtonEncerrar.setEnabled(false);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                cliente.trocaMensagem.encerrar();
                                jButtonIniciar.setEnabled(true);
                                jButtonEncerrar.setEnabled(false);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }
                    }
                });

            }
            {
                jTextAreaEntrada = new JTextArea();
                getContentPane().add(jTextAreaEntrada,
                        new AnchorConstraint(215, 979, 767, 23, AnchorConstraint.ANCHOR_REL,
                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                jTextAreaEntrada.setPreferredSize(new java.awt.Dimension(352, 250));
                jTextAreaEntrada.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
            }
            {
                jButtonEnviar = new JButton();
                getContentPane().add(jButtonEnviar, new AnchorConstraint(25, 979, 133, 509, AnchorConstraint.ANCHOR_REL,
                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                jButtonEnviar.setText("Enviar");
                jButtonEnviar.setPreferredSize(new java.awt.Dimension(173, 49));
                jButtonEnviar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        if (jRadioButtonServidor.isSelected()) {
                            try {
                                servidor.trocaMensagem.envia(jTextFieldSaida.getText());
                                jTextFieldSaida.setText("");
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                cliente.trocaMensagem.envia(jTextFieldSaida.getText());
                                jTextFieldSaida.setText("");
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
            {
                jButtonIniciar = new JButton();
                getContentPane().add(jButtonIniciar, new AnchorConstraint(25, 495, 133, 21, AnchorConstraint.ANCHOR_REL,
                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                jButtonIniciar.setText("Iniciar Conexão");
                jButtonIniciar.setPreferredSize(new java.awt.Dimension(169, 49));
                jButtonIniciar.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        if (jRadioButtonServidor.isSelected()) {
                            servidor = new Servidor(Integer.valueOf(jTextFieldPorta.getText()));
                            setTitle("Servidor");
                            jButtonIniciar.setEnabled(false);
                            jButtonEncerrar.setEnabled(true);
                        } else {
                            cliente = new Cliente();
                            cliente.conecta(jTextFieldIp.getText(), Integer.valueOf(jTextFieldPorta.getText()));
                            setTitle("Cliente");
                            jButtonIniciar.setEnabled(false);
                            jButtonEncerrar.setEnabled(true);
                        }
                    }
                });
            }
            pack();
            this.setSize(384, 491);
        } catch (Exception e) {
            // add your error handling code here
            e.printStackTrace();
        }
    }

    public static String encripta(String textoPleno) {
        String textoEncriptado = null;
        try {
            byte[] ba = textoPleno.getBytes("UTF-8");
            byte[] encr = abc.encrypt(ba);

            textoEncriptado = Base64.encode(encr);

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DataLengthException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidCipherTextException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return textoEncriptado;
    }

    public static String desencripta(String textoEncriptado) {
        String textoPleno = null;
        try {
            byte[] ba = Base64.decode(textoEncriptado);

            byte[] retr = abc.decrypt(ba);
            if (retr.length == ba.length) {
                ba = retr;
            } else {
                System.arraycopy(retr, 0, ba, 0, 2);//ba.length);
            }
            textoPleno = new String(ba, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DataLengthException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidCipherTextException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return textoPleno;
    }

    public static String convertToString(Objeto objetoQualquer) {
        try {
            String str;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(objetoQualquer);
            byte[] objeto = byteArrayOutputStream.toByteArray();
            str = Base64.encode(objeto);
            objectOutputStream.close();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Objeto convertFromString(String str) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (Objeto) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
