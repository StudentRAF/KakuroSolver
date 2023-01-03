package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.view.solution.Solution;

import java.awt.event.ActionEvent;

public class SolveAction extends KakuroAction {

    public SolveAction() {
        super(SolveAction.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Solution.window.setVisible(true);
    }

}
