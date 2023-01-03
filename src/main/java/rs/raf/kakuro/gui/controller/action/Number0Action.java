package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.solver.Solver;

import java.awt.event.ActionEvent;

public class Number0Action extends KakuroAction {

    public Number0Action() {
        super(Number0Action.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Solver.initialize();
    }

}
