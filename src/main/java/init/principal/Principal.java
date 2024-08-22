package init.principal;

import init.view.Main;
import init.controller.Recibo;

public class Principal {

    public static void main(String[] args) {
        new Recibo().createLocal();
        new Main().setVisible(true);
    }
}
