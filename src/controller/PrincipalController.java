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

        this.principalWindow.setVisible(true);
    }

}
