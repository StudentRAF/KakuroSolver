package rs.raf.kakuro.gui.controller.steps;

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
    public void display() {

    }

    @Override
    public void hide() {

    }

}
