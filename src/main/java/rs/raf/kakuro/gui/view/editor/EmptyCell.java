package rs.raf.kakuro.gui.view.editor;

import com.formdev.flatlaf.util.ColorFunctions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Rectangle2D;

public class EmptyCell extends CellBase {

    private static final Color BORDER_COLOR           = BASE_BORDER_COLOR;
    private static final Color BACKGROUND_COLOR       = ColorFunctions.darken(BASE_BACKGROUND_COLOR, 0.03f);
    private static final Color BORDER_FOCUS_COLOR     = BORDER_COLOR;
    private static final Color BACKGROUND_FOCUS_COLOR = BACKGROUND_COLOR;

    private Color borderColor     = BORDER_COLOR;
    private Color backgroundColor = BACKGROUND_COLOR;

    private int borderThickness = 2;

    public EmptyCell(int row, int column) {
        super(row, column);

        addFocusListener(new CellFocusListener());
    }

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

    @Override
    public CellBase getSuccessor() {
        return new ClueCell(row, column);
    }

    @Override
    public void editCell() { }

    @Override
    public CellType getType() {
        return CellType.EMPTY;
    }

    //region Listeners

    private class CellFocusListener extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent event) {
            borderColor     = BORDER_FOCUS_COLOR;
            backgroundColor = BACKGROUND_FOCUS_COLOR;

            repaint();
        }

        @Override
        public void focusLost(FocusEvent event) {
            borderColor     = BORDER_COLOR;
            backgroundColor = BACKGROUND_COLOR;

            repaint();
        }

    }

    //endregion

}
