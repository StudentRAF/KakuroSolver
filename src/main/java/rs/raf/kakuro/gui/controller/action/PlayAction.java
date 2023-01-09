package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.controller.StepManager;

import java.awt.event.ActionEvent;

public class PlayAction extends KakuroAction {

    public PlayAction() {
        super(PlayAction.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        int i = 130;

        while (i-- > 0)
            StepManager.nextStep();

    }

}
