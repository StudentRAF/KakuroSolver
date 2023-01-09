package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.controller.StepManager;
import rs.raf.kakuro.gui.model.attribute.Notes;
import rs.raf.kakuro.gui.model.cell.ValueCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.ExcessNotesRenderer;

public class ExcessNotesStep extends StepBase {

    private final ValueCell cell;
    private final Notes newNotes;
    private final Notes oldNotes;

    public ExcessNotesStep(ValueCell cell, Notes oldNotes) {
        this.cell     = cell;
        this.oldNotes = oldNotes;
        this.newNotes = cell.getNotes().copy();
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStepRenderer(new ExcessNotesRenderer(cell, oldNotes));
    }

    @Override
    public void focus() {
        editor.setEditorCellFocused(getEditorRow(cell.getRow()), getEditorColumn(cell.getColumn()));

    }

    @Override
    public void display() {
        focus();

        ((ValueCell) editorTable.getCell(cell.getRow(), cell.getColumn(), kakuroTable.getBounds())).getNotes().set(newNotes);

        editor.getEditorCell(getEditorRow(cell.getRow()), getEditorColumn(cell.getColumn())).repaint();
    }

    @Override
    public void hide() {
        ((ValueCell) editorTable.getCell(cell.getRow(), cell.getColumn(), kakuroTable.getBounds())).getNotes().set(oldNotes);

        editor.getEditorCell(getEditorRow(cell.getRow()), getEditorColumn(cell.getColumn())).repaint();

        if (StepManager.peekPrevious() == null)
            return;

        StepManager.peekPrevious().focus();
    }

}
