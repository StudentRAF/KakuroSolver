package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.controller.StepManager;
import rs.raf.kakuro.gui.model.cell.ValueCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.UpdateValueRenderer;

public class UpdateValueStep extends StepBase {

    private final ValueCell cell;

    public UpdateValueStep(ValueCell cell) {
        this.cell = cell;
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStepRenderer(new UpdateValueRenderer(cell));
    }

    @Override
    public void focus() {
        editor.setEditorCellFocused(getEditorRow(cell.getRow()), getEditorColumn(cell.getColumn()));
    }

    @Override
    public void display() {
        focus();

        ((ValueCell) editorTable.getCell(cell.getRow(), cell.getColumn(), kakuroTable.getBounds())).setValue(cell.getValue());

        editor.getEditorCell(getEditorRow(cell.getRow()), getEditorColumn(cell.getColumn())).repaint();
    }

    @Override
    public void hide() {
        ((ValueCell) editorTable.getCell(cell.getRow(), cell.getColumn(), kakuroTable.getBounds())).setValue(0);

        editor.getEditorCell(getEditorRow(cell.getRow()), getEditorColumn(cell.getColumn())).repaint();

        if (StepManager.peekPrevious() == null)
            return;

        StepManager.peekPrevious().focus();
    }

}
