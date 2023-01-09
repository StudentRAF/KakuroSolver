package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.controller.StepManager;
import rs.raf.kakuro.gui.model.cell.EmptyCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.AssignmentEmptyCellRenderer;

public class AssignmentEmptyCellStep extends StepBase {

    private final EmptyCell cell;

    public AssignmentEmptyCellStep(EmptyCell cell) {
        this.cell = cell;
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStepRenderer(new AssignmentEmptyCellRenderer(cell));
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
