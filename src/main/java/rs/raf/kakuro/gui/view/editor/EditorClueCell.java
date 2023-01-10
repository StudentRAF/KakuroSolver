package rs.raf.kakuro.gui.view.editor;

import com.formdev.flatlaf.util.ColorFunctions;
import rs.raf.kakuro.gui.controller.action.SwitchCellAction;
import rs.raf.kakuro.gui.model.cell.CellBase;
import rs.raf.kakuro.gui.model.cell.ClueCell;
import rs.raf.kakuro.gui.util.model.Fonts;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class EditorClueCell extends EditorCellBase {

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

    private boolean activeRight  = false;
    private boolean activeBottom = false;

    private final ClueCell cell;

    public EditorClueCell(int row, int column) {
        super(row, column);

        cell = new ClueCell(row, column);

        addKeyListener(new CellKeyListener());
        addMouseListener(new CellMouseListener());
    }


    @Override
    public void setFocused() {
        if (currentAction instanceof SwitchCellAction)
            return;

        if (!activeRight && !activeBottom) {
            borderColor     = BORDER_FOCUS_COLOR;
            borderThickness = 3;
        }

        selectionColor  = BORDER_FOCUS_COLOR;
        backgroundColor = BACKGROUND_FOCUS_COLOR;
        foregroundColor = FOREGROUND_FOCUS_COLOR;

        repaint();
    }

    @Override
    public void setUnfocused() {
        borderColor     = BORDER_COLOR;
        selectionColor  = BORDER_COLOR;
        backgroundColor = BACKGROUND_COLOR;
        foregroundColor = FOREGROUND_COLOR;

        borderThickness = 2;

        activeBottom = activeRight = false;

        repaint();
    }


    @Override
    protected void paintCell(Graphics2D graphics) {
        paintCellBorder(graphics);
        paintCellBackground(graphics);
        paintCellRightClue(graphics);
        paintCellBottomClue(graphics);
        paintCellClueDivider(graphics);
        paintCellSelection(graphics);
    }

    //region Paint Cell

    private void paintCellBorder(Graphics2D graphics) {
        graphics.setColor(borderColor);
        graphics.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
    }

    private void paintCellBackground(Graphics2D graphics) {
        graphics.setColor(backgroundColor);
        graphics.fill(new Rectangle2D.Double(borderThickness, borderThickness, getWidth() - 2 * borderThickness, getHeight() - 2 * borderThickness));
    }

    private void paintCellRightClue(Graphics2D graphics) {
        if (cell.getRightClue() == 0)
            return;

        Font font = new Font(Fonts.DIN_MEDIUM.getName(), Font.PLAIN, 28);

        graphics.setFont(font);
        graphics.setColor(foregroundColor);

        Point right = getRightLocation(font);

        graphics.drawString(Integer.toString(cell.getRightClue()), right.x, right.y);
    }

    private Point getRightLocation(Font font) {
        FontMetrics metrics = new Canvas().getFontMetrics(font);
        return new Point(60 - metrics.stringWidth(Integer.toString(cell.getRightClue())) / 2, 37);
    }

    private void paintCellBottomClue(Graphics2D graphics) {
        if (cell.getBottomClue() == 0)
            return;

        Font font = new Font(Fonts.DIN_MEDIUM.getName(), Font.PLAIN, 28);

        graphics.setFont(font);
        graphics.setColor(foregroundColor);

        Point bottom = getBottomLocation(font);

        graphics.drawString(Integer.toString(cell.getBottomClue()), bottom.x, bottom.y);
    }

    private Point getBottomLocation(Font font) {
        FontMetrics metrics = new Canvas().getFontMetrics(font);
        return new Point(29 - metrics.stringWidth(Integer.toString(cell.getBottomClue())) / 2, 73);
    }

    private void paintCellClueDivider(Graphics2D graphics) {
        if (activeRight || activeBottom)
            return;

        graphics.setColor(foregroundColor);
        graphics.setStroke(new BasicStroke(diagonalThickness));

        graphics.draw(new Line2D.Double(getWidth() / 6, getHeight() / 6, 5 * getWidth() / 6 - 1, 5 * getHeight() / 6 - 1));
    }

    private void paintCellSelection(Graphics2D graphics) {
        if (activeRight)
            paintCellTopRightSelection(graphics);
        else if (activeBottom)
            paintCellBottomLeftSelection(graphics);
    }

    private void paintCellTopRightSelection(Graphics2D graphics) {
        graphics.setColor(selectionColor);
        graphics.setStroke(new BasicStroke(selectionThickness));

        graphics.draw(getTopRightPath());
    }

    private Path2D getTopRightPath() {
        Path2D path = new Path2D.Double();

        path.moveTo(1, 1);
        path.lineTo(getWidth() - 2, getHeight() - 2);
        path.lineTo(getWidth() - 2, 1);
        path.closePath();

        return path;
    }

    private void paintCellBottomLeftSelection(Graphics2D graphics) {
        graphics.setColor(selectionColor);
        graphics.setStroke(new BasicStroke(selectionThickness));

        graphics.draw(getBottomLeftPath());
    }

    private Path2D getBottomLeftPath() {
        Path2D path = new Path2D.Double();

        path.moveTo(1, 1);
        path.lineTo(getWidth() - 2, getHeight() - 2);
        path.lineTo(1, getHeight() - 2);
        path.closePath();

        return path;
    }

    //endregion

    @Override
    public EditorCellBase getSuccessor() {
        return new EditorValueCell(row, column);
    }

    @Override
    public void editCell() {

    }

    @Override
    public CellBase getCell() {
        return cell;
    }

    //region Listeners

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

            borderColor = BORDER_COLOR;

            activeRight = !(activeBottom = getBottomLeftShape().contains(event.getPoint()));
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
        if (activeRight)
            addToRightClue(value);
        else if (activeBottom)
            addToBottomClue(value);
    }

    private void addToRightClue(int value) {
        setRightClue(value == -2 ? 0 : value == -1 ? cell.getRightClue() / 10 : Math.min(cell.getRightClue() * 10 + value, 45));
    }

    private void addToBottomClue(int value) {
        setBottomClue(value == -2 ? 0 : value == -1 ? cell.getBottomClue() / 10 : Math.min(cell.getBottomClue() * 10 + value, 45));
    }

    /**
     * Sets the top right clue.
     * @param clue clue
     */
    public void setRightClue(int clue) {
        cell.setRightClue(clue);
        repaint();
    }

    /**
     * Sets the bottom left clue.
     * @param clue clue
     */
    public void setBottomClue(int clue) {
        cell.setBottomClue(clue);
        repaint();
    }

    //endregion

}
