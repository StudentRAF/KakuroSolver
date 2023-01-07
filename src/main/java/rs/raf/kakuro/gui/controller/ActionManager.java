package rs.raf.kakuro.gui.controller;

import rs.raf.kakuro.gui.controller.action.EditCellAction;
import rs.raf.kakuro.gui.controller.action.EditStateAction;
import rs.raf.kakuro.gui.controller.action.KakuroAction;
import rs.raf.kakuro.gui.controller.action.NextAction;
import rs.raf.kakuro.gui.controller.action.Number0Action;
import rs.raf.kakuro.gui.controller.action.Number1Action;
import rs.raf.kakuro.gui.controller.action.Number2Action;
import rs.raf.kakuro.gui.controller.action.PauseAction;
import rs.raf.kakuro.gui.controller.action.PlayAction;
import rs.raf.kakuro.gui.controller.action.PreviousAction;
import rs.raf.kakuro.gui.controller.action.SolveAction;
import rs.raf.kakuro.gui.controller.action.SwitchCellAction;
import rs.raf.kakuro.gui.controller.action.SwitchStateAction;

import java.util.List;

public class ActionManager {

    private static final List<KakuroAction> actions = List.of(
            new Number0Action(),
            new Number1Action(),
            new Number2Action(),
            new EditCellAction(),
            new EditStateAction(),
            new NextAction(),
            new PauseAction(),
            new PlayAction(),
            new PreviousAction(),
            new SolveAction(),
            new SwitchCellAction(),
            new SwitchStateAction()
                                                             );

    //region Setup

    /**
     * Setups all the actions.
     */
    public static void setup() {
        setupKeyStrokes();
        setupIcons();
        setupNames();
        setupTooltips();
    }

    /**
     * Setups all action icons.
     */
    public static void setupIcons() {
        for (KakuroAction action : actions) {
            action.setIcon();
        }
    }

    /**
     * Setups all action keystrokes.
     */
    public static void setupKeyStrokes() {
        for (KakuroAction action : actions) {
            action.setAcceleratorKey();
        }
    }

    /**
     * Setups all action names.
     */
    public static void setupNames() {
        for (KakuroAction action : actions) {
            action.setName();
        }
    }

    /**
     * Setups all action tooltips.
     */
    public static void setupTooltips() {
        for (KakuroAction action : actions) {
            action.setTooltip();
        }
    }

    //endregion

    /**
     * Returns the action from the list if it exists, otherwise returns null.
     * @param actionClass action class
     * @return action if exists, otherwise null
     */
    public static KakuroAction getAction(Class actionClass) {
        String actionName = actionClass.getSimpleName();
        for (KakuroAction action : actions)
            if (action.getId().equals(actionName))
                return action;

        return null;
    }

}
