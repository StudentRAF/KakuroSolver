package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.view.Editor;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;

import java.awt.event.ActionEvent;

public class SolveAction extends KakuroAction {

    public SolveAction() {
        super(SolveAction.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Editor.instance.setEditState();
        SolutionWindow.window.setVisible(true);
    }

}
