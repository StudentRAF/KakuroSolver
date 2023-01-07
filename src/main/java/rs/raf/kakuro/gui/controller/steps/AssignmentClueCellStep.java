package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.model.cell.ClueCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.AssignmentClueCellRenderer;

public class AssignmentClueCellStep extends StepBase {

    private final ClueCell clueCell;

    public AssignmentClueCellStep(ClueCell clueCell) {
        this.clueCell = clueCell;
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStep(new AssignmentClueCellRenderer(clueCell));
    }

    @Override
    public void display() { }

    @Override
    public void hide() { }

}
