package rs.raf.kakuro.gui.view.editor;

import com.formdev.flatlaf.util.ColorFunctions;
import rs.raf.kakuro.gui.util.model.Fonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Rectangle2D;

public class ValueCell extends CellBase {

    private static final Color BORDER_COLOR           = BASE_BORDER_COLOR;
    private static final Color BACKGROUND_COLOR       = ColorFunctions.lighten(BASE_BACKGROUND_COLOR, 0.01f);
    private static final Color FOREGROUND_COLOR       = ColorFunctions.lighten(BASE_FOREGROUND_COLOR, 0.1f);
    private static final Color BORDER_FOCUS_COLOR     = BASE_BORDER_FOCUS_COLOR;
    private static final Color BACKGROUND_FOCUS_COLOR = ColorFunctions.lighten(BACKGROUND_COLOR, 0.01f);
    private static final Color FOREGROUND_FOCUS_COLOR = ColorFunctions.lighten(FOREGROUND_COLOR, 0.1f);

    private Color borderColor     = BORDER_COLOR;
    private Color backgroundColor = BACKGROUND_COLOR;
    private Color foregroundColor = FOREGROUND_COLOR;

    private int borderThickness = 2;

    private int value = 0;

    public ValueCell(int row, int column) {
        super(row, column);

        addFocusListener(new CellFocusListener());
    }

    @Override
    protected void paintCell(Graphics2D graphics) {
        Font font = new Font(Fonts.DIN_MEDIUM.getName(), Font.PLAIN, 36);

        graphics.setColor(borderColor);
        graphics.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

        graphics.setColor(backgroundColor);
        graphics.fill(new Rectangle2D.Double(borderThickness,                  borderThickness,
                                             getWidth() - 2 * borderThickness, getHeight() - 2 * borderThickness));

        FontMetrics metrics = graphics.getFontMetrics(font);

        int x = (getWidth() - metrics.stringWidth(Integer.toString(value))) / 2;
        int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent() + 4;

        graphics.setFont(font);
        graphics.setColor(foregroundColor);

        if (value != 0)
            graphics.drawString(Integer.toString(value), x, y);
    }

    @Override
    public CellBase getSuccessor() {
        return new EmptyCell(row, column);
    }

    @Override
    public void editCell() { }

    //region Listeners

    private class CellFocusListener extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent event) {
            borderColor     = BORDER_FOCUS_COLOR;
            backgroundColor = BACKGROUND_FOCUS_COLOR;
            foregroundColor = FOREGROUND_FOCUS_COLOR;

            repaint();
        }

        @Override
        public void focusLost(FocusEvent event) {
            borderColor     = BORDER_COLOR;
            backgroundColor = BACKGROUND_COLOR;
            foregroundColor = FOREGROUND_COLOR;

            repaint();
        }

    }

    //endregion

    //region Setters and Getters

    /**
     * Sets the cell value.
     * @param value value
     */
    public void setValue(int value) {
        this.value = value;
        repaint();
    }

    /**
     * Returns the cell value.
     * @return value
     */
    public int getValue() {
        return value;
    }

    //endregion

}
