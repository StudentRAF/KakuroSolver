package rs.raf.kakuro.gui.view;

import rs.raf.kakuro.gui.model.cell.CellBase;
import rs.raf.kakuro.gui.view.editor.EditorCellBase;
import rs.raf.kakuro.gui.view.editor.EditorClueCell;
import rs.raf.kakuro.gui.view.editor.EditorEmptyCell;
import rs.raf.kakuro.gui.view.editor.EditorValueCell;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;

public class Editor extends JPanel {

    public static final int rows    = 10;
    public static final int columns = 13;

    public static final Editor instance = instantiate();

    private static Editor instantiate() {
        Editor instance = new Editor();
        instance.initialize();

        return instance;
    }

    private EditorCellBase[][] editorCells;
    private EditorCellBase     activeCell;

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
        editorCells = new EditorCellBase[rows][columns];

        for (int row = 0; row < rows; ++row)
            for (int column = 0; column < columns; ++column)
                editorCells[row][column] = new EditorEmptyCell(row, column);

        example2();
    }

    private void example1() {
        addClueCell(1, 4, 30,  0);
        addClueCell(1, 5,  9,  0);
        addClueCell(1, 6,  5,  0);
        addClueCell(2, 3,  4,  9);
        addClueCell(3, 2,  0, 19);
        addClueCell(4, 2,  0,  5);
        addClueCell(4, 5,  8,  0);
        addClueCell(5, 2,  5,  0);
        addClueCell(5, 3, 17,  5);
        addClueCell(6, 1,  0, 28);
        addClueCell(7, 1,  0, 12);


        addValueCell(2, 4);
        addValueCell(2, 5);
        addValueCell(2, 6);
        addValueCell(3, 3);
        addValueCell(3, 4);
        addValueCell(3, 5);
        addValueCell(3, 6);
        addValueCell(4, 3);
        addValueCell(4, 4);
        addValueCell(5, 4);
        addValueCell(5, 5);
        addValueCell(6, 2);
        addValueCell(6, 3);
        addValueCell(6, 4);
        addValueCell(6, 5);
        addValueCell(7, 2);
        addValueCell(7, 3);
        addValueCell(7, 4);
    }

    private void example2() {
        addClueCell(0,  2,  6,  0);
        addClueCell(0,  3, 16,  0);
        addClueCell(0,  6, 35,  0);
        addClueCell(0,  7,  6,  0);
        addClueCell(0, 11, 10,  0);
        addClueCell(0, 12,  6,  0);
        addClueCell(1,  1, 17, 11);
        addClueCell(1,  4, 30,  0);
        addClueCell(1,  5,  0,  7);
        addClueCell(1, 10,  0,  6);
        addClueCell(2,  0,  0, 23);
        addClueCell(2,  5,  3,  9);
        addClueCell(2, 10, 29,  4);
        addClueCell(3,  0,  0, 11);
        addClueCell(3,  3, 30, 20);
        addClueCell(3,  9,  0, 10);
        addClueCell(4,  2,  0, 23);
        addClueCell(4,  7, 35,  0);
        addClueCell(4,  9, 10,  9);
        addClueCell(5,  2, 29, 16);
        addClueCell(5,  5,  0, 13);
        addClueCell(5,  8,  3, 11);
        addClueCell(6,  1,  7, 13);
        addClueCell(6,  6,  6, 18);
        addClueCell(6, 11, 23,  0);
        addClueCell(6, 12, 17,  0);
        addClueCell(7,  0,  0, 19);
        addClueCell(7,  5,  0, 13);
        addClueCell(7, 10, 16, 17);
        addClueCell(8,  0,  0, 11);
        addClueCell(8,  5,  0,  8);
        addClueCell(8,  8,  0, 26);
        addClueCell(9,  0,  0,  8);
        addClueCell(9,  5,  0, 12);
        addClueCell(9,  9,  0, 16);

        addValueCell(1,  2);
        addValueCell(1,  3);
        addValueCell(1,  6);
        addValueCell(1,  7);
        addValueCell(1, 11);
        addValueCell(1, 12);
        addValueCell(2,  1);
        addValueCell(2,  2);
        addValueCell(2,  3);
        addValueCell(2,  4);
        addValueCell(2,  6);
        addValueCell(2,  7);
        addValueCell(2, 11);
        addValueCell(2, 12);
        addValueCell(3,  1);
        addValueCell(3,  2);
        addValueCell(3,  4);
        addValueCell(3,  5);
        addValueCell(3,  6);
        addValueCell(3,  7);
        addValueCell(3, 10);
        addValueCell(3, 11);
        addValueCell(3, 12);
        addValueCell(4,  3);
        addValueCell(4,  4);
        addValueCell(4,  5);
        addValueCell(4,  6);
        addValueCell(4, 10);
        addValueCell(4, 11);
        addValueCell(5,  3);
        addValueCell(5,  4);
        addValueCell(5,  6);
        addValueCell(5,  7);
        addValueCell(5,  9);
        addValueCell(5, 10);
        addValueCell(6,  2);
        addValueCell(6,  3);
        addValueCell(6,  7);
        addValueCell(6,  8);
        addValueCell(6,  9);
        addValueCell(6, 10);
        addValueCell(7,  1);
        addValueCell(7,  2);
        addValueCell(7,  3);
        addValueCell(7,  6);
        addValueCell(7,  7);
        addValueCell(7,  8);
        addValueCell(7,  9);
        addValueCell(7, 11);
        addValueCell(7, 12);
        addValueCell(8,  1);
        addValueCell(8,  2);
        addValueCell(8,  6);
        addValueCell(8,  7);
        addValueCell(8,  9);
        addValueCell(8, 10);
        addValueCell(8, 11);
        addValueCell(8, 12);
        addValueCell(9,  1);
        addValueCell(9,  2);
        addValueCell(9,  6);
        addValueCell(9,  7);
        addValueCell(9, 10);
        addValueCell(9, 11);
    }

    private void example3() {
        addClueCell(1, 5,  6,  0);
        addClueCell(1, 6, 17,  0);
        addClueCell(2, 4, 22,  5);
        addClueCell(3, 3, 10, 20);
        addClueCell(3, 7, 22,  0);
        addClueCell(3, 8,  4,  0);
        addClueCell(4, 2,  0, 10);
        addClueCell(4, 5,  0, 13);
        addClueCell(5, 2, 10,  6);
        addClueCell(5, 6, 12,  9);
        addClueCell(6, 1,  0,  4);
        addClueCell(6, 4, 16,  0);
        addClueCell(6, 5,  0, 17);
        addClueCell(7, 1,  0, 21);
        addClueCell(7, 5, 14,  4);
        addClueCell(8, 3,  0, 14);
        addClueCell(9, 3,  0, 10);

        addValueCell(2, 5);
        addValueCell(2, 6);
        addValueCell(3, 4);
        addValueCell(3, 5);
        addValueCell(3, 6);
        addValueCell(4, 3);
        addValueCell(4, 4);
        addValueCell(4, 6);
        addValueCell(4, 7);
        addValueCell(4, 8);
        addValueCell(5, 3);
        addValueCell(5, 4);
        addValueCell(5, 7);
        addValueCell(5, 8);
        addValueCell(6, 2);
        addValueCell(6, 3);
        addValueCell(6, 6);
        addValueCell(6, 7);
        addValueCell(7, 2);
        addValueCell(7, 3);
        addValueCell(7, 4);
        addValueCell(7, 6);
        addValueCell(7, 7);
        addValueCell(8, 4);
        addValueCell(8, 5);
        addValueCell(8, 6);
        addValueCell(9, 4);
        addValueCell(9, 5);
    }


    private void addValueCell(int row, int column) {
        editorCells[row][column] = new EditorValueCell(row, column);
    }

    private void addClueCell(int row, int column, int bottom, int right) {
        editorCells[row][column] = getClueCell(row, column, bottom, right);
    }

    private EditorClueCell getClueCell(int row, int column, int bottom, int right) {
        EditorClueCell cell = new EditorClueCell(row, column);
        cell.setBottomClue(bottom);
        cell.setRightClue(right);
        return cell;
    }

    private void addComponents() {
        for (int row = 0; row < rows; ++row)
            for (int column = 0; column < columns; ++column)
                add(editorCells[row][column]);

        if (ApplicationWindow.window != null)
            ApplicationWindow.window.getContentPane().requestFocus();
    }

    //endregion

    //region State Methods

    public void changeToSuccessor(int row, int column) {
        editorCells[row][column] = editorCells[row][column].getSuccessor();

        removeAll();
        revalidate();
        addComponents();
        repaint();
    }

    public void setSwitchState() {
        for (EditorCellBase[] rowCells: editorCells)
            for (EditorCellBase cell: rowCells)
                cell.setupSwitchAction();

        setEditorCellFocused(activeCell != null ? activeCell.getRow() : 0, activeCell != null ? activeCell.getColumn() : 0);
    }

    public void setEditState() {
        for (EditorCellBase[] rowCells: editorCells)
            for (EditorCellBase cell: rowCells)
                cell.setupEditAction();

        setEditorCellFocused(activeCell != null ? activeCell.getRow() : 0, activeCell != null ? activeCell.getColumn() : 0);
    }

    //endregion

    public void setActiveCell(EditorCellBase activeCell) {
        this.activeCell = activeCell;
    }

    public CellBase[][] getCells() {
        CellBase[][] cells = new CellBase[rows][columns];

        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < columns; ++j)
                cells[i][j] = editorCells[i][j].getCell();

        return cells;
    }

    public EditorCellBase getActiveCell() {
        return activeCell;
    }

    public EditorCellBase getEditorCell(int row, int column) {
        return editorCells[row][column];
    }

    public void setEditorCellFocused(int row, int column) {
        if (activeCell != null)
            activeCell.setUnfocused();

        activeCell = editorCells[row][column];

        activeCell.setFocused();
    }

    public void clear() {
        for (int row = 0; row < rows; ++row)
            for (int column = 0; column < columns; ++column)
                if (editorCells[row][column] instanceof EditorValueCell editorValueCell)
                    editorValueCell.clear();

        repaint();
    }

}
