package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PrincipalWindow extends JFrame{

    private JButton btnMovie;
    private JButton btnDirector;
    private JButton btnActor;
    private JPanel windowPanel;

    public PrincipalWindow(){

        setContentPane(windowPanel);
        setTitle("Menu Principal");
        setSize(900,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setButtons();
    }

    public void addListener(ActionListener action){
        btnActor.addActionListener(action);
        btnDirector.addActionListener(action);
        btnMovie.addActionListener(action);
    }

    public void setButtons() {
        btnActor.setActionCommand("1");
        btnDirector.setActionCommand("2");
        btnMovie.setActionCommand("3");
    }

    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(windowPanel, errorMessage, " WARNING ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public void displayMessage(String message){
        JOptionPane.showMessageDialog(windowPanel, message);
    }
}
