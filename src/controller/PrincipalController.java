package controller;

import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalController {

    //-------------------------------
    private PrincipalWindow principalWindow;

    //-------------------------------

    public PrincipalController() {

        this.principalWindow = new PrincipalWindow();

        PrincipalController.ButtonListener action = new PrincipalController.ButtonListener();
        this.principalWindow.addListener(action);

        this.principalWindow.setVisible(true);
    }

    private class ButtonListener implements ActionListener {

        private ButtonListener() {
        }

        public void actionPerformed(ActionEvent e) {
            int valor = Integer.parseInt(e.getActionCommand());
            switch (valor) {
                case 1:
                    //ACTOR
                    ActorController actorController = new ActorController();
                    principalWindow.dispose();

                    break;
                case 2:
                    //DIRECTOR
                    DirectorController directorController = new DirectorController();
                    principalWindow.dispose();
                    break;

                case 3:
                    //PELÍCULA
                    CinemaController cinemaController = new CinemaController();
                    principalWindow.dispose();
                    break;
            }
        }
    }
}
