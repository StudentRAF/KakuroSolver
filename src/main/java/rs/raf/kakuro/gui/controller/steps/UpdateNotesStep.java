package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.model.attribute.Notes;
import rs.raf.kakuro.gui.model.cell.ValueCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.UpdateNotesRenderer;

public class UpdateNotesStep extends StepBase {

    private final ValueCell cell;
    private final Notes oldNotes;
    private final Notes combinationNotes;

    public UpdateNotesStep(ValueCell cell, Notes combinationNotes, Notes oldNotes) {
        this.cell = cell;
        this.oldNotes = oldNotes;
        this.combinationNotes = combinationNotes;
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStepRenderer(new UpdateNotesRenderer(cell, combinationNotes, oldNotes));
    }

    @Override
    public void display() {

    }

    @Override
    public void hide() {

    }

}
