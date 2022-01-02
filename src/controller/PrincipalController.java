package controller;

import view.*;

import java.awt.*;
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

    public static PrincipalController getInstance(){
        if(instance == null){
            instance = new PrincipalController();
        }
        return instance;
    }


    private static PrincipalController instance;
}
