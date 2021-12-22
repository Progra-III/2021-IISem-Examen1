package executable;

import controller.PrincipalController;

public class Executable {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PrincipalController principalController= new PrincipalController();
            }
        });
    }
}
