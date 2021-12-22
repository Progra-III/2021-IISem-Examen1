package controller;

import utilities.Utilities;
import view.DirectorWindow;
import view.PrincipalWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectorController {
    //-------------------------------
    private DirectorWindow directorWindow;
    private Utilities utilities;

    //-------------------------------

    public DirectorController() {

        this.directorWindow = new DirectorWindow();

        DirectorController.ButtonListener action = new DirectorController.ButtonListener();
        this.directorWindow.addListener(action);

        this.directorWindow.setVisible(true);
    }

    private class ButtonListener implements ActionListener {

        private ButtonListener() {
        }

        public void actionPerformed(ActionEvent e) {
            int valor = Integer.parseInt(e.getActionCommand());
            switch (valor) {
                case 1:
                    //ADD

                    break;
                case 2:
                    //EXIT
                    PrincipalController principalController = new PrincipalController();
                    directorWindow.dispose();
                    break;

                case 3:
                    //LIST

                    break;

                case 4:
                    //SEARCH

                    break;

                case 5:
                    //UPDATE

                    break;
            }
        }
    }
}
