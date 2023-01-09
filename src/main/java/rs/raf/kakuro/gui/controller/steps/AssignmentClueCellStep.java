package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.controller.StepManager;
import rs.raf.kakuro.gui.model.cell.ClueCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.AssignmentClueCellRenderer;

public class AssignmentClueCellStep extends StepBase {

    private final ClueCell cell;

    public AssignmentClueCellStep(ClueCell cell) {
        this.cell = cell;
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStepRenderer(new AssignmentClueCellRenderer(cell));
    }

    @Override
    public void focus() {
        editor.setEditorCellFocused(getEditorRow(cell.getRow()), getEditorColumn(cell.getColumn()));
    }

    @Override
    public void display() {
        focus();
    }

    @Override
    public void hide() {
        if (StepManager.peekPrevious() == null)
            return;

        StepManager.peekPrevious().focus();
    }

}
