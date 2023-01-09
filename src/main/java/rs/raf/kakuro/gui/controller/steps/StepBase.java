package rs.raf.kakuro.gui.controller.steps;

import rs.raf.kakuro.gui.model.data.Table;
import rs.raf.kakuro.gui.solver.Solver;
import rs.raf.kakuro.gui.view.Editor;

public abstract class StepBase {

    protected final Editor editor = Editor.instance;

    protected final Table editorTable = Solver.editorTable();
    protected final Table kakuroTable = Solver.kakuroTable();

    public abstract void perform();

    public abstract void focus();

    public abstract void display();

    public abstract void hide();

    protected final int getEditorRow(int kakuroRow) {
        return kakuroTable.getBounds().getStartRow() + kakuroRow;
    }

    protected final int getEditorColumn(int kakuroColumn) {
        return kakuroTable.getBounds().getStartColumn() + kakuroColumn;
    }

}
