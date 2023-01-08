package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.model.cell.ValueCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.AssignmentValueCellRenderer;

public class AssignmentValueCellStep extends StepBase {

    private final ValueCell valueCell;

    public AssignmentValueCellStep(ValueCell valueCell) {
        this.valueCell = valueCell;
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStepRenderer(new AssignmentValueCellRenderer(valueCell));
    }

    @Override
    public void display() { }

    @Override
    public void hide() { }

}
