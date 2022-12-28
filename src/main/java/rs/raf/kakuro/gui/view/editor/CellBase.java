package rs.raf.kakuro.gui.view.editor;

import com.formdev.flatlaf.extras.components.FlatButton;
import com.formdev.flatlaf.util.ColorFunctions;
import rs.raf.kakuro.gui.controller.ActionManager;
import rs.raf.kakuro.gui.controller.action.EditCellAction;
import rs.raf.kakuro.gui.controller.action.SwitchCellAction;

import javax.swing.Action;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public abstract class CellBase extends FlatButton {

    protected static final Color BASE_BACKGROUND_COLOR = new Color(59, 61, 63);
    protected static final Color BASE_FOREGROUND_COLOR = new Color(162, 164, 166);
    protected static final Color BASE_BORDER_COLOR = ColorFunctions.lighten(new Color(59, 61, 63), 0.1f);
    protected static final Color BASE_BORDER_FOCUS_COLOR = new Color(10, 106, 230);

    protected static Action currentAction = null;

    protected int row;
    protected int column;

    public CellBase(int row, int column) {
        setButtonType(FlatButton.ButtonType.square);

        this.row    = row;
        this.column = column;

        setFocusPainted(false);
        setBorderPainted(false);

        setAction(currentAction);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintCell(graphics2D);
    }

    public void setupSwitchAction() {
        currentAction = ActionManager.getAction(SwitchCellAction.class);
        setAction(currentAction);
    }

    public void setupEditAction() {
        currentAction = ActionManager.getAction(EditCellAction.class);
        setAction(currentAction);
    }

    protected abstract void paintCell(Graphics2D graphics);

    public abstract CellBase getSuccessor();

    public abstract void editCell();

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
