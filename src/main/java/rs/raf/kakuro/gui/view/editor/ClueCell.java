package rs.raf.kakuro.gui.view.editor;

import com.formdev.flatlaf.util.ColorFunctions;
import rs.raf.kakuro.gui.controller.action.SwitchCellAction;
import rs.raf.kakuro.gui.util.model.Fonts;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class ClueCell extends CellBase {

    private static final Color BORDER_COLOR           = BASE_BORDER_COLOR;
    private static final Color BACKGROUND_COLOR       = BASE_BACKGROUND_COLOR;
    private static final Color FOREGROUND_COLOR       = BASE_FOREGROUND_COLOR;
    private static final Color BORDER_FOCUS_COLOR     = BASE_BORDER_FOCUS_COLOR;
    private static final Color BACKGROUND_FOCUS_COLOR = ColorFunctions.lighten(BACKGROUND_COLOR, 0.01f);
    private static final Color FOREGROUND_FOCUS_COLOR = ColorFunctions.lighten(FOREGROUND_COLOR, 0.1f);

    private Color borderColor     = BORDER_COLOR;
    private Color selectionColor  = BORDER_COLOR;
    private Color backgroundColor = BACKGROUND_COLOR;
    private Color foregroundColor = FOREGROUND_COLOR;

    private int borderThickness    = 2;
    private int selectionThickness = 3;
    private int diagonalThickness  = 2;

    private int clueTopRight   = 0;
    private int clueBottomLeft = 0;

    private boolean activeTopRight   = false;
    private boolean activeBottomLeft = false;

    public ClueCell(int row, int column) {
        super(row, column);

        addKeyListener(new CellKeyListener());
        addFocusListener(new CellFocusListener());
        addMouseListener(new CellMouseListener());
    }

    @Override
    protected void paintCell(Graphics2D graphics) {
        Font font = new Font(Fonts.DIN_MEDIUM.getName(), Font.PLAIN, 28);

        graphics.setColor(borderColor);
        graphics.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

        graphics.setColor(backgroundColor);
        graphics.fill(new Rectangle2D.Double(borderThickness,                  borderThickness,
                                             getWidth() - 2 * borderThickness, getHeight() - 2 * borderThickness));

        graphics.setFont(font);

        graphics.setColor(foregroundColor);

        Point topRight   = getTopRightLocation(font);
        Point bottomLeft = getBottomLeftLocation(font);

        if (clueTopRight > 0)
            graphics.drawString(Integer.toString(clueTopRight), topRight.x, topRight.y);

        if (clueBottomLeft > 0)
            graphics.drawString(Integer.toString(clueBottomLeft), bottomLeft.x, bottomLeft.y);

        graphics.setStroke(new BasicStroke(selectionThickness));

        if (!activeTopRight && !activeBottomLeft) {
            graphics.setColor(foregroundColor);
            graphics.setStroke(new BasicStroke(diagonalThickness));
            graphics.draw(new Line2D.Double(15, 15, getWidth() - 16, getHeight() - 16));
        }
        else {
            graphics.setColor(selectionColor);
            graphics.setStroke(new BasicStroke(selectionThickness));

            if (activeTopRight)
                graphics.draw(getTopRightPath());
            else
                graphics.draw(getBottomLeftPath());

        }
    }

    private Point getTopRightLocation(Font font) {
        FontMetrics metrics = new Canvas().getFontMetrics(font);
        return new Point(60 - metrics.stringWidth(Integer.toString(clueTopRight)) / 2, 37);
    }

    private Point getBottomLeftLocation(Font font) {
        FontMetrics metrics = new Canvas().getFontMetrics(font);
        return new Point(28 - metrics.stringWidth(Integer.toString(clueBottomLeft)) / 2, 73);
    }

    private Path2D getTopRightPath() {
        Path2D path = new Path2D.Double();

        path.moveTo(1, 1);
        path.lineTo(getWidth() - 2, getHeight() - 2);
        path.lineTo(getWidth() - 2, 1);
        path.closePath();

        return path;
    }

    private Path2D getBottomLeftPath() {
        Path2D path = new Path2D.Double();

        path.moveTo(1, 1);
        path.lineTo(getWidth() - 2, getHeight() - 2);
        path.lineTo(1, getHeight() - 2);
        path.closePath();

        return path;
    }

    @Override
    public CellBase getSuccessor() {
        return new ValueCell(row, column);
    }

    @Override
    public void editCell() {

    }

    //region Listeners

    private class CellFocusListener extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent event) {
            if (currentAction instanceof SwitchCellAction)
                return;

            selectionColor  = BORDER_FOCUS_COLOR;
            backgroundColor = BACKGROUND_FOCUS_COLOR;
            foregroundColor = FOREGROUND_FOCUS_COLOR;


            repaint();
        }

        @Override
        public void focusLost(FocusEvent event) {
            selectionColor  = BORDER_COLOR;
            backgroundColor = BACKGROUND_COLOR;
            foregroundColor = FOREGROUND_COLOR;

            activeBottomLeft = activeTopRight = false;

            repaint();
        }

    }

    private static final Map<Integer, Integer> keyMap = Map.ofEntries(
            Map.entry(  8, -1), // BACKSPACE
            Map.entry( 27, -2), // ESCAPE
            Map.entry( 48,  0), // 0
            Map.entry( 49,  1), // 1
            Map.entry( 50,  2), // 2
            Map.entry( 51,  3), // 3
            Map.entry( 52,  4), // 4
            Map.entry( 53,  5), // 5
            Map.entry( 54,  6), // 6
            Map.entry( 55,  7), // 7
            Map.entry( 56,  8), // 8
            Map.entry( 57,  9), // 9
            Map.entry( 96,  0), // NumPad-0
            Map.entry( 97,  1), // NumPad-1
            Map.entry( 98,  2), // NumPad-2
            Map.entry( 99,  3), // NumPad-3
            Map.entry(100,  4), // NumPad-4
            Map.entry(101,  5), // NumPad-5
            Map.entry(102,  6), // NumPad-6
            Map.entry(103,  7), // NumPad-7
            Map.entry(104,  8), // NumPad-8
            Map.entry(105,  9)  // NumPad-9
    );

    private class CellKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            Integer value = keyMap.get(event.getKeyCode());

            if (value != null)
                addToActiveValue(value);
        }

    }

    private class CellMouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent event) {
            if (currentAction instanceof SwitchCellAction)
                return;

            activeTopRight = !(activeBottomLeft = getBottomLeftShape().contains(event.getPoint()));
        }

        private Path2D getBottomLeftShape() {
            Path2D path = new Path2D.Double();

            path.moveTo(0, 0);
            path.lineTo(getWidth(), getHeight());
            path.lineTo(0, getHeight());
            path.closePath();

            return path;
        }

    }

    //endregion

    //region Setters and Getters

    private void addToActiveValue(int value) {
        if (activeTopRight)
            addToClueTopRight(value);
        else
            addToClueBottomLeft(value);
    }

    private void addToClueTopRight(int value) {
        setClueTopRight(value == -2 ? 0 : value == -1 ? getClueTopRight() / 10 : Math.min(getClueTopRight() * 10 + value, 45));
    }

    private void addToClueBottomLeft(int value) {
        setClueBottomLeft(value == -2 ? 0 : value == -1 ? getClueBottomLeft() / 10 : Math.min(getClueBottomLeft() * 10 + value, 45));
    }

    /**
     * Sets the top right clue.
     * @param clue clue
     */
    public void setClueTopRight(int clue) {
        clueTopRight = clue;
        repaint();
    }

    /**
     * Sets the bottom left clue.
     * @param clue clue
     */
    public void setClueBottomLeft(int clue) {
        clueBottomLeft = clue;
        repaint();
    }

    /**
     * Returns the top right clue.
     * @return clue
     */
    public int getClueTopRight() {
        return clueTopRight;
    }

    /**
     * Returns the bottom left clue.
     * @return clue
     */
    public int getClueBottomLeft() {
        return clueBottomLeft;
    }

    //endregion

}
