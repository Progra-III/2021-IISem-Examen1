package controller;

import utilities.Utilities;
import view.MoviesWindow;

public class MoviesController {
    //-------------------------------
    private MoviesWindow cinemaWindow;
    private Utilities utilities;

    //-------------------------------

    public MoviesController() {

        this.cinemaWindow = new MoviesWindow();
        this.cinemaWindow.setVisible(true);
    }
}
