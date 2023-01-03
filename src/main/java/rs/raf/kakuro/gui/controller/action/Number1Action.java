package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.solver.Solver;

import java.awt.event.ActionEvent;

public class Number1Action extends KakuroAction {

    public Number1Action() {
        super(Number1Action.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Solver.removeExcessCellNotes();
    }

}