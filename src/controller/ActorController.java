package controller;

import utilities.Utilities;
import view.ActorWindow;
import view.PrincipalWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActorController {
    //-------------------------------
    private ActorWindow actorWindow;
    private Utilities utilities;

    //-------------------------------

    public ActorController() {

        this.actorWindow = new ActorWindow();

        ActorController.ButtonListener action = new ActorController.ButtonListener();
        this.actorWindow.addListener(action);

        this.actorWindow.setVisible(true);
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
                    actorWindow.dispose();
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
