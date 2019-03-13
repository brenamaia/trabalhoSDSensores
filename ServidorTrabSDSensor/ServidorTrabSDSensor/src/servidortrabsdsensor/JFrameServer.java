package servidortrabsdsensor;

import java.awt.List;
import static java.awt.SystemColor.text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class JFrameServer extends javax.swing.JFrame {

    private static ServerSocket ssocket;
    private static Socket socket;
    private static BufferedReader br;
    private static InputStreamReader inputsr;
    private static String message = "";
    
    
    
    
    public JFrameServer() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SERVER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jLabel1)
                .addContainerGap(190, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(264, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) throws IOException {
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameServer().setVisible(true);    
            }
        });
        int cont = 0;
        try{
            
            
            while(true){
                ssocket = new ServerSocket(5000);
                if(cont == 0){
                    System.out.println("Server executando na porta 5000");
                    cont++;
                }
                socket = ssocket.accept();
                //System.out.println("Conectado!");

                inputsr = new InputStreamReader(socket.getInputStream());
                br = new BufferedReader(inputsr);
                message = br.readLine();
               
                message = message.replace("|","\n");
                
             
                
                System.out.println(message);

                inputsr.close();
                br.close();
                ssocket.close();
                socket.close();
                

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
