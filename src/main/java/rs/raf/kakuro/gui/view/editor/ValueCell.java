package rs.raf.kakuro.gui.view.editor;

import com.formdev.flatlaf.util.ColorFunctions;
import rs.raf.kakuro.gui.util.model.Fonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Map;

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
        addKeyListener(new CellKeyListener());
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

            borderThickness = 3;

            repaint();
        }

        @Override
        public void focusLost(FocusEvent event) {
            borderColor     = BORDER_COLOR;
            backgroundColor = BACKGROUND_COLOR;
            foregroundColor = FOREGROUND_COLOR;

            borderThickness = 2;

            repaint();
        }

    }

    private static final Map<Integer, Integer> keyMap = Map.ofEntries(
            Map.entry( 27, 0), // ESCAPE
            Map.entry( 48, 0), // 0
            Map.entry( 49, 1), // 1
            Map.entry( 50, 2), // 2
            Map.entry( 51, 3), // 3
            Map.entry( 52, 4), // 4
            Map.entry( 53, 5), // 5
            Map.entry( 54, 6), // 6
            Map.entry( 55, 7), // 7
            Map.entry( 56, 8), // 8
            Map.entry( 57, 9), // 9
            Map.entry( 96, 0), // NumPad-0
            Map.entry( 97, 1), // NumPad-1
            Map.entry( 98, 2), // NumPad-2
            Map.entry( 99, 3), // NumPad-3
            Map.entry(100, 4), // NumPad-4
            Map.entry(101, 5), // NumPad-5
            Map.entry(102, 6), // NumPad-6
            Map.entry(103, 7), // NumPad-7
            Map.entry(104, 8), // NumPad-8
            Map.entry(105, 9)  // NumPad-9
                                                                     );

    private class CellKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            Integer value = keyMap.get(event.getKeyCode());

            if (value != null)
                setValue(value);
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
