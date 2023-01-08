package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.model.cell.ValueCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.UpdateValueCellValueRenderer;

public class UpdateValueCellValueStep extends StepBase {

    private final ValueCell cell;

    public UpdateValueCellValueStep(ValueCell cell) {
        this.cell = cell;
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStepRenderer(new UpdateValueCellValueRenderer(cell));
    }

    @Override
    public void display() {

    }

    @Override
    public void hide() {

    }

}
