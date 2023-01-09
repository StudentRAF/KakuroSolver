package rs.raf.kakuro.gui.controller;

import rs.raf.kakuro.gui.controller.steps.StepBase;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;

import java.util.ArrayList;
import java.util.List;

public class StepManager {

    private static final List<StepBase> steps = new ArrayList<>();

    private static int currentStep = 0;

    public static void addStep(StepBase step) {
        steps.add(step);
        step.perform();
    }

    public static void nextStep() {
        if (currentStep == steps.size())
            return;

        SolutionWindow.window.setSelectedStepIndex(currentStep);

        steps.get(currentStep++).display();
    }

    public static void previousStep() {
        if (currentStep == 0)
            return;

        SolutionWindow.window.setSelectedStepIndex(currentStep);

        steps.get(--currentStep).hide();
    }

    public static StepBase peekNext() {
        if (currentStep == steps.size())
            return null;

        return steps.get(currentStep + 1);
    }

    public static StepBase peekPrevious() {
        if (currentStep == 0)
            return null;

        return steps.get(currentStep - 1);
    }

    public static boolean hasNext() {
        return currentStep != steps.size();
    }

}
