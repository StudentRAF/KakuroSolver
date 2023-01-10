package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.controller.steps.TimerManager;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;

import java.awt.event.ActionEvent;

public class PlayAction extends KakuroAction {

    public PlayAction() {
        super(PlayAction.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        TimerManager.start();

        SolutionWindow.window.changeTimerAction();
    }

}
