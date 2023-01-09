package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.controller.StepManager;
import rs.raf.kakuro.gui.model.attribute.Notes;
import rs.raf.kakuro.gui.model.cell.ValueCell;
import rs.raf.kakuro.gui.view.editor.EditorValueCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.AssignmentNotesRenderer;

public class AssignmentNotesStep extends StepBase {

    private final ValueCell cell;
    private final Notes     newNotes;
    private final Notes     oldNotes;
    private final Notes     combinationNotes;
    private boolean         isCellEdited;

    public AssignmentNotesStep(ValueCell cell, Notes combinationNotes, Notes oldNotes) {
        this.cell             = cell;
        this.newNotes         = cell.getNotes().copy();
        this.oldNotes         = oldNotes;
        this.combinationNotes = combinationNotes;
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStepRenderer(new AssignmentNotesRenderer(cell, combinationNotes, oldNotes));
    }

    @Override
    public void focus() {
        editor.setEditorCellFocused(getEditorRow(cell.getRow()), getEditorColumn(cell.getColumn()));
    }

    @Override
    public void display() {
        focus();

        ((ValueCell) editorTable.getCell(cell.getRow(), cell.getColumn(), kakuroTable.getBounds())).getNotes().set(newNotes);

        EditorValueCell editorValueCell = ((EditorValueCell) editor.getEditorCell(getEditorRow(cell.getRow()), getEditorColumn(cell.getColumn())));

        isCellEdited = editorValueCell.isEdited();

        editorValueCell.setEdited(true);
        editorValueCell.repaint();
    }

    @Override
    public void hide() {
        ((ValueCell) editorTable.getCell(cell.getRow(), cell.getColumn(), kakuroTable.getBounds())).getNotes().set(oldNotes);

        EditorValueCell editorValueCell = ((EditorValueCell) editor.getEditorCell(getEditorRow(cell.getRow()), getEditorColumn(cell.getColumn())));

        editorValueCell.setEdited(isCellEdited);
        editorValueCell.repaint();

        if (StepManager.peekPrevious() == null)
            return;

        StepManager.peekPrevious().focus();
    }

}
