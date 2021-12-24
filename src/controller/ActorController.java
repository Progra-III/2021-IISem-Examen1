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
        this.actorWindow.setVisible(true);
    }


}
