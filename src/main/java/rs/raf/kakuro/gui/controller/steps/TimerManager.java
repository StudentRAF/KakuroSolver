package rs.raf.kakuro.gui.controller.steps;


import rs.raf.kakuro.gui.controller.ActionManager;
import rs.raf.kakuro.gui.controller.action.NextAction;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;

import javax.swing.Timer;

public class TimerManager {

    private static Timer timer = new Timer(SolutionWindow.window.getTimerPeriod(), ActionManager.getAction(NextAction.class));

    public static void start() {
        stop();

        timer.setDelay(SolutionWindow.window.getTimerPeriod());
        timer.start();
    }

    public static void stop() {
        timer.stop();
    }

}
