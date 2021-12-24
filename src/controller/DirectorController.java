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



        this.directorWindow.setVisible(true);
    }


}
