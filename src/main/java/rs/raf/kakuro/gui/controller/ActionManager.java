package rs.raf.kakuro.gui.controller;

import com.formdev.flatlaf.util.StringUtils;
import rs.raf.kakuro.gui.controller.action.KakuroAction;

import java.util.List;

public class ActionManager {

    private static final List<KakuroAction> actions = List.of(

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
        String actionName = StringUtils.removeTrailing(actionClass.getSimpleName(), "Action");
        for (KakuroAction action : actions)
            if (action.getId().equals(actionName))
                return action;

        return null;
    }

}
