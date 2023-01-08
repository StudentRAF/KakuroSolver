package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.model.attribute.Combinations;
import rs.raf.kakuro.gui.model.cell.ClueCell;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;
import rs.raf.kakuro.gui.view.solution.render.UpdateCombinationsRenderer;

public class UpdateCombinationsStep extends StepBase {

    private final ClueCell     cell;
    private final boolean      isRight;
    private final Combinations oldCombinations;

    public UpdateCombinationsStep(ClueCell cell, Combinations oldCombinations, boolean isRight) {
        this.cell            = cell;
        this.isRight         = isRight;
        this.oldCombinations = oldCombinations;
    }

    @Override
    public void perform() {
        SolutionWindow.window.addStepRenderer(new UpdateCombinationsRenderer(cell, oldCombinations, isRight));
    }

    @Override
    public void display() {

    }

    @Override
    public void hide() {

    }

}
