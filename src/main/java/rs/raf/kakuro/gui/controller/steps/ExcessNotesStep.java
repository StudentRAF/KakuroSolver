package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.model.attribute.Notes;
import rs.raf.kakuro.gui.model.cell.ValueCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.ExcessNotesRenderer;

public class ExcessNotesStep extends StepBase {

    private final ValueCell cell;
    private final Notes oldNotes;

    public ExcessNotesStep(ValueCell cell, Notes oldNotes) {
        this.cell     = cell;
        this.oldNotes = oldNotes;
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStepRenderer(new ExcessNotesRenderer(cell, oldNotes));
    }

    @Override
    public void display() {

    }

    @Override
    public void hide() {

    }

}
