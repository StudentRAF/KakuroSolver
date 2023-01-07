package rs.raf.kakuro.gui.view.solution.render;

import javax.swing.JList;
import java.awt.Component;

public interface IRenderer {

    Component getComponent(JList<? extends RendererBase> list, int index, boolean isSelected, boolean cellHasFocus);

}
