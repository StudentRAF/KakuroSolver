package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.model.data.Bounds;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.TableBoundsRenderer;

public class TableBoundsStep extends StepBase {

    private final Bounds bounds;

    public TableBoundsStep(Bounds bounds) {
        this.bounds = bounds;
    }

    @Override
    public void perform() {
        if (bounds == null)
            return;

        SolutionWindow.window.addStepRenderer(new TableBoundsRenderer(bounds));
    }

    @Override
    public void display() { }

    @Override
    public void hide() { }

}
