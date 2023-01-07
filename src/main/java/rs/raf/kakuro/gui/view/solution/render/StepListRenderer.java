package rs.raf.kakuro.gui.view.solution.render;

import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Component;

public class StepListRenderer implements ListCellRenderer<RendererBase> {

    @Override
    public Component getListCellRendererComponent(JList<? extends RendererBase> list, RendererBase value, int index, boolean isSelected, boolean cellHasFocus) {
        return value.getComponent(list, index, isSelected, cellHasFocus);
    }

}
