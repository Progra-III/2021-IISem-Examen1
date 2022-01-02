package executable;

import controller.PrincipalController;
import view.PrincipalWindow;

public class Executable {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PrincipalWindow principal = new PrincipalWindow();
            }
        });
    }
}
