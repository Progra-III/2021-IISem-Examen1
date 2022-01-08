package utilities;

import javax.swing.*;

public class Utilities {

    public Utilities() {
    }

    public boolean isNumeric(String data){
        try {
            Integer.parseInt(String.valueOf(data));
            return true;
        } catch (NumberFormatException nfe){

            JOptionPane.showMessageDialog(null,"Dato no numerico. \n Favor intente de nuevo","Error",JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }

}
