package view;

import controller.ActorController;
import controller.MoviesController;
import controller.DirectorController;
import controller.PrincipalController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.spec.InvalidParameterSpecException;

public class PrincipalWindow extends JFrame {

    private JButton btnMovie;
    private JButton btnDirector;
    private JButton btnActor;
    private JPanel windowPanel;

    private static PrincipalController controller;

    public PrincipalWindow() {
        controller = PrincipalController.getInstance();

        setContentPane(windowPanel);
        setTitle("Menu Principal");
        setSize(900, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setButtons();

        ButtonListener listener = new ButtonListener();
        btnMovie.addActionListener(listener);
        btnDirector.addActionListener(listener);
        btnActor.addActionListener(listener);
        setVisible(true);
    }

    public void setButtons() {
        btnActor.setActionCommand("1");
        btnDirector.setActionCommand("2");
        btnMovie.setActionCommand("3");
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int valor = Integer.parseInt(e.getActionCommand());
            switch (valor) {
                case 1:
                    //ACTOR
                    ActorWindow actorWindow = new ActorWindow();
                    actorWindow.setVisible(true);
                    dispose();

                    break;
                case 2:
                    //DIRECTOR
                    DirectorWindow directorWindow = new DirectorWindow();
                    directorWindow.setVisible(true);
                    dispose();

                    break;

                case 3:
                    //PELÍCULA
                    MoviesWindow moviesWindow = new MoviesWindow();
                    moviesWindow.setVisible(true);
                    dispose();

                    break;
            }

        }


    }
}

