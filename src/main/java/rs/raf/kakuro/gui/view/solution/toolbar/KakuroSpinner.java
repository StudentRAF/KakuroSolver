package rs.raf.kakuro.gui.view.solution.toolbar;

import javax.swing.BorderFactory;
import javax.swing.JSpinner;
import java.awt.Dimension;

public class KakuroSpinner extends JSpinner {

    public KakuroSpinner(int value) {
        setBorder(BorderFactory.createEmptyBorder());
        setMaximumSize(new Dimension(63, 24));

        setValue(value);

        addChangeListener(event -> setValue(Math.max(100, (int) getValue())));
        addMouseWheelListener(event -> setValue(Math.max(100, (int) getValue() - event.getWheelRotation() * 100)));
    }

}
