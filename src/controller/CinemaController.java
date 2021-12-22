package controller;

import utilities.Utilities;
import view.CinemaWindow;
import view.PrincipalWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CinemaController {
    //-------------------------------
    private CinemaWindow cinemaWindow;
    private Utilities utilities;

    //-------------------------------

    public CinemaController() {

        this.cinemaWindow = new CinemaWindow();

        CinemaController.ButtonListener action = new CinemaController.ButtonListener();
        this.cinemaWindow.addListener(action);

        this.cinemaWindow.setVisible(true);
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
                    cinemaWindow.dispose();
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
