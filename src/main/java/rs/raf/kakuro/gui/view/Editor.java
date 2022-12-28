package rs.raf.kakuro.gui.view;

import rs.raf.kakuro.gui.model.CellBase;
import rs.raf.kakuro.gui.view.editor.EditorCellBase;
import rs.raf.kakuro.gui.view.editor.EditorClueCell;
import rs.raf.kakuro.gui.view.editor.EditorEmptyCell;
import rs.raf.kakuro.gui.view.editor.EditorValueCell;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;

public class Editor extends JPanel {

    public static final int rows = 10;
    public static final int columns = 13;

    public static final Editor editor = instantiate();

    private static Editor instantiate() {
        Editor instance = new Editor();
        instance.initialize();

        return instance;
    }

    private EditorCellBase[][] kakuroCells;

    private Editor() { }

    //region Initialization

    private void initialize() {
        setup();
        initializeComponents();
        addComponents();
    }

    private void setup() {
        setLayout(new GridLayout(rows, columns, 2, 2));

        setBorder(BorderFactory.createLineBorder(new Color(55, 57, 59), 3));
        setBackground(new Color(110, 112, 114));
    }

    private void initializeComponents() {
        kakuroCells = new EditorCellBase[rows][columns];

        for (int row = 0; row < rows; ++row)
            for (int column = 0; column < columns; ++column)
                kakuroCells[row][column] = getCell(row, column);
    }

    private EditorCellBase getCell(int row, int column) {
        boolean isFistLayer   = row == 0 || row == rows - 1 || column == 0 || column == columns - 1;
        boolean isSecondLayer = row == 1 || row == rows - 2 || column == 1 || column == columns - 2;

        return isFistLayer ? new EditorEmptyCell(row, column) : isSecondLayer ? new EditorClueCell(row, column) : new EditorValueCell(row, column);
    }

    private void addComponents() {
        for (int row = 0; row < rows; ++row)
            for (int column = 0; column < columns; ++column)
                add(kakuroCells[row][column]);

        if (ApplicationWindow.window != null)
            ApplicationWindow.window.getContentPane().requestFocus();
    }

    //endregion

    //region State Methods

    public void changeToSuccessor(int row, int column) {
        kakuroCells[row][column] = kakuroCells[row][column].getSuccessor();

        removeAll();
        revalidate();
        addComponents();
        repaint();
    }

    public void setSwitchState() {
        for (EditorCellBase[] rowCells: kakuroCells)
            for (EditorCellBase cell: rowCells)
                cell.setupSwitchAction();
    }

    public void setEditState() {
        for (EditorCellBase[] rowCells: kakuroCells)
            for (EditorCellBase cell: rowCells)
                cell.setupEditAction();
    }

    //endregion

    public CellBase[][] getCells() {
        CellBase[][] cells = new CellBase[rows][columns];

        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < columns; ++j)
                cells[i][j] = kakuroCells[i][j].getCell();

        return cells;
    }

}
