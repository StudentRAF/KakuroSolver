package rs.raf.kakuro.gui.view.editor;

import com.formdev.flatlaf.util.ColorFunctions;
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
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class ClueCell extends CellBase {

    private static final Color BORDER_COLOR           = BASE_BORDER_COLOR;
    private static final Color BACKGROUND_COLOR       = BASE_BACKGROUND_COLOR;
    private static final Color FOREGROUND_COLOR       = BASE_FOREGROUND_COLOR;
    private static final Color BORDER_FOCUS_COLOR     = BASE_BORDER_FOCUS_COLOR;
    private static final Color BACKGROUND_FOCUS_COLOR = ColorFunctions.lighten(BACKGROUND_COLOR, 0.01f);
    private static final Color FOREGROUND_FOCUS_COLOR = ColorFunctions.lighten(FOREGROUND_COLOR, 0.1f);

    private Color borderColor     = BORDER_COLOR;
    private Color backgroundColor = BACKGROUND_COLOR;
    private Color foregroundColor = FOREGROUND_COLOR;

    private int borderThickness = 2;

    private int clueTopRight   = 0;
    private int clueBottomLeft = 0;

    public ClueCell(int row, int column) {
        super(row, column);

        addFocusListener(new CellFocusListener());
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

        Line2D diagonal = new Line2D.Double(15, 15, getWidth() - 16, getHeight() - 16);

        graphics.setColor(foregroundColor);

        Point topRight = getTopRightLocation(font);
        Point bottomLeft = getBottomLeftLocation(font);

        graphics.drawString(Integer.toString(clueTopRight), topRight.x, topRight.y);
        graphics.drawString(Integer.toString(clueBottomLeft), bottomLeft.x, bottomLeft.y);

        graphics.setStroke(new BasicStroke(2F));
        graphics.draw(diagonal);
    }

    private Point getTopRightLocation(Font font) {
        FontMetrics metrics = new Canvas().getFontMetrics(font);
        return new Point(60 - metrics.stringWidth(Integer.toString(clueTopRight)) / 2, 37);
    }

    private Point getBottomLeftLocation(Font font) {
        FontMetrics metrics = new Canvas().getFontMetrics(font);
        return new Point(28 - metrics.stringWidth(Integer.toString(clueBottomLeft)) / 2, 73);
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
        public void focusGained(FocusEvent e) {
            borderColor     = BORDER_FOCUS_COLOR;
            backgroundColor = BACKGROUND_FOCUS_COLOR;
            foregroundColor = FOREGROUND_FOCUS_COLOR;

            repaint();
        }

        @Override
        public void focusLost(FocusEvent e) {
            borderColor     = BORDER_COLOR;
            backgroundColor = BACKGROUND_COLOR;
            foregroundColor = FOREGROUND_COLOR;

            repaint();
        }

    }

    //endregion

    //region Setters and Getters

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
