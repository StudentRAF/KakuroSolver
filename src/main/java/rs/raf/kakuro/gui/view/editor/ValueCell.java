package rs.raf.kakuro.gui.view.editor;

import com.formdev.flatlaf.util.ColorFunctions;
import rs.raf.kakuro.gui.controller.action.SwitchCellAction;
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
import java.util.ArrayList;
import java.util.List;
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

    private final List<Boolean> notes = new ArrayList<>() {
        {
            add(true);
            add(true);
            add(true);
            add(true);
            add(true);
            add(true);
            add(true);
            add(true);
            add(true);
        }
    };

    private int value = 0;

    public ValueCell(int row, int column) {
        super(row, column);

        addFocusListener(new CellFocusListener());
        addKeyListener(new CellKeyListener());
    }

    @Override
    protected void paintCell(Graphics2D graphics) {
        paintCellBorder(graphics);
        paintCellBackground(graphics);

        if (value != 0)
            paintCellValue(graphics);
        else
            paintCellNotes(graphics);
    }

    private void paintCellBorder(Graphics2D graphics) {
        graphics.setColor(borderColor);
        graphics.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
    }

    private void paintCellBackground(Graphics2D graphics) {
        graphics.setColor(backgroundColor);
        graphics.fill(new Rectangle2D.Double(borderThickness, borderThickness, getWidth() - 2 * borderThickness, getHeight() - 2 * borderThickness));
    }

    private void paintCellValue(Graphics2D graphics) {
        Font font = new Font(Fonts.DIN_MEDIUM.getName(), Font.PLAIN, 36);

        FontMetrics metrics = graphics.getFontMetrics(font);

        graphics.setFont(font);
        graphics.setColor(foregroundColor);

        int x = (getWidth() - metrics.stringWidth(Integer.toString(value))) / 2;
        int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent() + 4;

        graphics.drawString(Integer.toString(value), x, y);
    }

    private void paintCellNotes(Graphics2D graphics) {
        for (int index = 0; index < notes.size(); ++index)
            if (notes.get(index))
                paintCellNote(graphics, index);
    }

    private void paintCellNote(Graphics2D graphics, int value) {
        Font font = new Font(Fonts.DIN_MEDIUM.getName(), Font.PLAIN, 22);

        FontMetrics metrics = graphics.getFontMetrics(font);

        graphics.setFont(font);
        graphics.setColor(foregroundColor);

        int thirdWidth  = getWidth() / 3 - 4;
        int thirdHeight = getHeight() / 3 - 4;

        int x = (thirdWidth - metrics.stringWidth(Integer.toString(value))) / 2 + thirdWidth * (value % 3) + 6;
        int y = (thirdHeight + metrics.getAscent()) / 2 + thirdHeight * (value / 3) + 6;

        graphics.drawString(Integer.toString(value + 1), x, y);
    }


    @Override
    public CellBase getSuccessor() {
        return new EmptyCell(row, column);
    }

    @Override
    public void editCell() { }

    @Override
    public CellType getType() {
        return CellType.VALUE;
    }

    //region Listeners

    private class CellFocusListener extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent event) {
            if (currentAction instanceof SwitchCellAction)
                return;

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
