package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.model.cell.ClueCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.CalculateBottomCombinationsRenderer;

public class CalculateBottomCombinationsStep extends StepBase {

    private final ClueCell cell;

    public CalculateBottomCombinationsStep(ClueCell cell) {
        this.cell = cell;
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStepRenderer(new CalculateBottomCombinationsRenderer(cell));
    }

    @Override
    public void display() {

    }

    @Override
    public void hide() {

    }

}
