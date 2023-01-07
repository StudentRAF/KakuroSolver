package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.controller.StepManager;

import java.awt.event.ActionEvent;

public class NextAction extends KakuroAction {

    public NextAction() {
        super(NextAction.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        StepManager.nextStep();
    }

}
