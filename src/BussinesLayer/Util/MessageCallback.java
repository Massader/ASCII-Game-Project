package BussinesLayer.Util;

import PresentationLayer.GamePresentation;

public class MessageCallback {

    GamePresentation gp;

    public MessageCallback(GamePresentation gp) {
        this.gp = gp;
    }

    public void send(String msg) {
        gp.printMessage(msg);
    }

}
