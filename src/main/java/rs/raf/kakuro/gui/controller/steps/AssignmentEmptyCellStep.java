package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.model.cell.EmptyCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.AssignmentEmptyCellRenderer;

public class AssignmentEmptyCellStep extends StepBase {

    private final EmptyCell emptyCell;

    public AssignmentEmptyCellStep(EmptyCell emptyCell) {
        this.emptyCell = emptyCell;
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStepRenderer(new AssignmentEmptyCellRenderer(emptyCell));
    }

    @Override
    public void display() { }

    @Override
    public void hide() { }

}
