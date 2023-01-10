package rs.raf.kakuro.gui.view.solution.render;

import com.formdev.flatlaf.util.ColorFunctions;
import rs.raf.kakuro.gui.model.data.Bounds;

import javax.swing.JList;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class TableBoundsRenderer extends RendererBase {

    private static final Color BORDER_COLOR           = BASE_BORDER_COLOR;
    private static final Color BACKGROUND_COLOR       = BASE_BACKGROUND_COLOR;
    private static final Color FOREGROUND_COLOR       = BASE_FOREGROUND_COLOR;
    private static final Color SEPARATOR_COLOR        = BASE_SEPARATOR_COLOR;
    private static final Color BACKGROUND_FOCUS_COLOR = BASE_BACKGROUND_FOCUS_COLOR;
    private static final Color FOREGROUND_FOCUS_COLOR = ColorFunctions.lighten(FOREGROUND_COLOR, 0.1f);

    private static final int BORDER_THICKNESS = BASE_BORDER_THICKNESS;

    private static final Font FONT_TITLE   = BASE_FONT_NORMAL;
    private static final Font FONT_CONTENT = BASE_FONT_SMALL;

    private static final int SPACING_VERTICAL   = BASE_SPACING_VERTICAL;
    private static final int SPACING_HORIZONTAL = BASE_SPACING_HORIZONTAL;

    private static final int SEPARATOR_THICKNESS = BASE_SEPARATOR_THICKNESS;
    private static final int SEPARATOR_PADDING   = BASE_SEPARATOR_PADDING;
    private static final int COMPONENT_PADDING   = BASE_COMPONENT_PADDING;

    private static final FontMetrics titleMetrics   = new Canvas().getFontMetrics(FONT_TITLE);
    private static final FontMetrics contentMetrics = new Canvas().getFontMetrics(FONT_CONTENT);

    private final Rectangle titleBounds;
    private final Rectangle contentBounds;

    private boolean isSelected = false;

    private final String title = "Table Bounds";
    private final String contentRow1Left;
    private final String contentRow1Right;
    private final String contentRow2Left;
    private final String contentRow2Right;

    public TableBoundsRenderer(Bounds bounds) {
        contentRow1Left  = "Start Row:  "    + (bounds.getStartRow()    + 1);
        contentRow1Right = "Start Column:  " + (bounds.getStartColumn() + 1);
        contentRow2Left  = "End Row:  "      + (bounds.getEndRow()      + 1);
        contentRow2Right = "End Column:  "   + (bounds.getEndColumn()   + 1);

        titleBounds   = FONT_TITLE.createGlyphVector(new FontRenderContext(null, true, true), "TITLE").getPixelBounds(null, 0, 0);
        contentBounds = FONT_CONTENT.createGlyphVector(new FontRenderContext(null, true, true), "CONTENT").getPixelBounds(null, 0, 0);

        setPreferredSize(calculateDimensions());
    }

    private Dimension calculateDimensions() {
        int height = 0;

        //      | Before Component
        height += COMPONENT_PADDING;

        //Title | Title Ascent             | Title Descent             | Title Separator
        height += titleBounds.getHeight()  + titleMetrics.getDescent() + SEPARATOR_THICKNESS;

        //Row1  | Before Content   | Content Height
        height += SPACING_VERTICAL + contentBounds.getHeight();

        //Row2  | Before Content   | Content Height
        height += SPACING_VERTICAL + contentBounds.getHeight();

        //      | After Component   | Border
        height += COMPONENT_PADDING + BORDER_THICKNESS;

        return new Dimension(0, height);
    }

    @Override
    public Component getComponent(JList<? extends RendererBase> list, int index, boolean isSelected, boolean cellHasFocus) {
        this.isSelected = isSelected;
        return this;
    }

    @Override
    protected void render(Graphics2D graphics) {
        paintBorder(graphics);
        paintBackground(graphics);
        paintTitle(graphics);
        paintSeparator(graphics);
        paintContent(graphics);
    }

    private void paintBorder(Graphics2D graphics) {
        graphics.setColor(BORDER_COLOR);
        graphics.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
    }

    private void paintBackground(Graphics2D graphics) {
        graphics.setColor(isSelected ? BACKGROUND_FOCUS_COLOR : BACKGROUND_COLOR);
        graphics.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight() - BORDER_THICKNESS));
    }

    private void paintTitle(Graphics2D graphics) {
        graphics.setFont(FONT_TITLE);
        graphics.setColor(isSelected ? FOREGROUND_FOCUS_COLOR : FOREGROUND_COLOR);

        int titleX = (getWidth() - titleMetrics.stringWidth(title)) / 2;
        int titleY = (int) (COMPONENT_PADDING + titleBounds.getHeight());

        graphics.drawString(title, titleX, titleY);
    }

    private void paintSeparator(Graphics2D graphics) {
        FontMetrics titleMetrics = graphics.getFontMetrics(FONT_TITLE);

        graphics.setColor(SEPARATOR_COLOR);
        graphics.setStroke(new BasicStroke(SEPARATOR_THICKNESS));

        int separatorX = SEPARATOR_PADDING;
        int separatorY = (int) (COMPONENT_PADDING + titleBounds.getHeight() + titleMetrics.getDescent() + (SEPARATOR_THICKNESS + 1) / 2);

        graphics.draw(new Line2D.Double(separatorX, separatorY, getWidth() - separatorX, separatorY));
    }

    private void paintContent(Graphics2D graphics) {
        graphics.setFont(FONT_CONTENT);
        graphics.setColor(isSelected ? FOREGROUND_FOCUS_COLOR : FOREGROUND_COLOR);

        int contentX = 0;
        int contentY = (int) (COMPONENT_PADDING + titleBounds.getHeight() + titleMetrics.getDescent() + SEPARATOR_THICKNESS);

        int row1LeftWidth  = contentMetrics.stringWidth(contentRow1Left);
        int row1RightWidth = contentMetrics.stringWidth(contentRow1Right);
        int row2LeftWidth  = contentMetrics.stringWidth(contentRow2Left);
        int row2RightWidth = contentMetrics.stringWidth(contentRow2Right);

        int maxLeftWidth  = Math.max(row1LeftWidth,  row2LeftWidth);
        int maxRightWidth = Math.max(row1RightWidth, row2RightWidth);

        //Row 1
        contentX += (getWidth() - maxLeftWidth - SPACING_HORIZONTAL - maxRightWidth) / 2;
        contentY += SPACING_VERTICAL + contentBounds.getHeight();

        graphics.drawString(contentRow1Left, contentX, contentY);

        contentX += SPACING_HORIZONTAL + maxLeftWidth;

        graphics.drawString(contentRow1Right, contentX, contentY);

        //Row 2
        contentY += SPACING_VERTICAL + contentBounds.getHeight();

        graphics.drawString(contentRow2Right, contentX, contentY);

        contentX -= SPACING_HORIZONTAL + maxLeftWidth;

        graphics.drawString(contentRow2Left, contentX, contentY);
    }

}
