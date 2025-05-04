package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;;

public class VentanaPrincipal extends JFrame{
    JPanel panel = new JPanel();
    
    public VentanaPrincipal(){
        cuerpo();
    }
    public void cuerpo(){
        setSize(300, 500);
        panel.setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Calculadora");
        panel.setBackground(Color.GRAY);
        this.add(panel);
    }
    public void elementos(){}
    public static void main(String[] args) {
        Login a = new Login();
        a.setVisible(true);
    }
}