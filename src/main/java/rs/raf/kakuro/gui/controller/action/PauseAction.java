package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.controller.steps.TimerManager;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;

import java.awt.event.ActionEvent;

public class PauseAction extends KakuroAction {

    public PauseAction() {
        super(PauseAction.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        TimerManager.stop();

        SolutionWindow.window.changeTimerAction();
    }

}
