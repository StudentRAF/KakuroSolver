package rs.raf.kakuro.gui.controller;

import rs.raf.kakuro.gui.controller.steps.StepBase;

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

        steps.get(currentStep++).display();
    }

    public static void previousStep() {
        if (currentStep == 0)
            return;

        steps.get(currentStep--).hide();
    }

    public static boolean hasNext() {
        return currentStep != steps.size();
    }

}
