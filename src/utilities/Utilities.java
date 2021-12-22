package utilities;

public class Utilities {

    public Utilities() {
    }

    public boolean isNumeric(char data){
        try {
            Integer.parseInt(String.valueOf(data));
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

}
