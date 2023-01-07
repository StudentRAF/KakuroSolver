package rs.raf.kakuro.gui.view.solution.render;

import com.formdev.flatlaf.util.ColorFunctions;
import rs.raf.kakuro.gui.util.model.Fonts;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public abstract class RendererBase extends JPanel implements IRenderer {

    protected static final Color BASE_BACKGROUND_COLOR = new Color(59, 61, 63);
    protected static final Color BASE_FOREGROUND_COLOR = new Color(162, 164, 166);
    protected static final Color BASE_BORDER_COLOR = ColorFunctions.lighten(BASE_BACKGROUND_COLOR, 0.1f);

    protected static final int BASE_BORDER_THICKNESS = 2;

    protected static final Font BASE_FONT_SMALL = new Font(Fonts.ROBOTO_MEDIUM.getName(), Font.PLAIN, 14);
    protected static final Font BASE_FONT_NORMAL = new Font(Fonts.ROBOTO_MEDIUM.getName(), Font.PLAIN, 18);
    protected static final Font BASE_FONT_LARGE = new Font(Fonts.ROBOTO_MEDIUM.getName(), Font.PLAIN, 22);

    protected static final int BASE_SPACING_VERTICAL   = 12;
    protected static final int BASE_SPACING_HORIZONTAL = 16;

    protected static final int BASE_SEPARATOR_THICKNESS = 1;
    protected static final int BASE_SEPARATOR_PADDING   = 50;
    protected static final int BASE_COMPONENT_PADDING   = 16;
    protected static final int BASE_TEXT_PADDING        = BASE_SEPARATOR_PADDING - 20;

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        render(graphics2D);
    }

    protected abstract void render(Graphics2D graphics);

}
