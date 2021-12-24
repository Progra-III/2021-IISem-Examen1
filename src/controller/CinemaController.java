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
        this.cinemaWindow.setVisible(true);
    }
}
