package rs.raf.kakuro.gui.view.editor;

import com.formdev.flatlaf.util.ColorFunctions;
import rs.raf.kakuro.gui.controller.action.SwitchCellAction;
import rs.raf.kakuro.gui.model.cell.CellBase;
import rs.raf.kakuro.gui.model.cell.EmptyCell;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class EditorEmptyCell extends EditorCellBase {

    private static final Color BORDER_COLOR           = BASE_BORDER_COLOR;
    private static final Color BACKGROUND_COLOR       = ColorFunctions.darken(BASE_BACKGROUND_COLOR, 0.023f);
    private static final Color BORDER_FOCUS_COLOR     = BASE_BORDER_FOCUS_COLOR;
    private static final Color BACKGROUND_FOCUS_COLOR = ColorFunctions.lighten(BACKGROUND_COLOR, 0);

    private Color borderColor     = BORDER_COLOR;
    private Color backgroundColor = BACKGROUND_COLOR;

    private int borderThickness = 2;

    private final EmptyCell cell;

    public EditorEmptyCell(int row, int column) {
        super(row, column);

        cell = new EmptyCell(row, column);
    }

    //region Paint Cell

    @Override
    protected void paintCell(Graphics2D graphics) {
        paintCellBorder(graphics);
        paintCellBackground(graphics);
    }

    private void paintCellBorder(Graphics2D graphics) {
        graphics.setColor(borderColor);
        graphics.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
    }

    private void paintCellBackground(Graphics2D graphics) {
        graphics.setColor(backgroundColor);
        graphics.fill(new Rectangle2D.Double(borderThickness, borderThickness, getWidth() - 2 * borderThickness, getHeight() - 2 * borderThickness));
    }

    //endregion


    @Override
    public void setFocused() {
        if (currentAction instanceof SwitchCellAction)
            return;

        borderColor     = BORDER_FOCUS_COLOR;
        backgroundColor = BACKGROUND_FOCUS_COLOR;

        borderThickness = 3;

        repaint();
    }

    @Override
    public void setUnfocused() {
        borderColor     = BORDER_COLOR;
        backgroundColor = BACKGROUND_COLOR;

        borderThickness = 2;

        repaint();
    }

    @Override
    public EditorCellBase getSuccessor() {
        return new EditorClueCell(row, column);
    }

    @Override
    public void editCell() { }

    @Override
    public CellBase getCell() {
        return cell;
    }

}
