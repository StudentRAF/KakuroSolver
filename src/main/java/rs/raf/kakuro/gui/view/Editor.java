package rs.raf.kakuro.gui.view;

import rs.raf.kakuro.gui.view.editor.CellBase;
import rs.raf.kakuro.gui.view.editor.ClueCell;
import rs.raf.kakuro.gui.view.editor.EmptyCell;
import rs.raf.kakuro.gui.view.editor.ValueCell;

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

    private CellBase[][] kakuroCells;

    private Editor() { }

    //region Initialization

    private void initialize() {
        setup();
        initializeComponents();
        addComponents();
    }

    private void setup() {
        setLayout(new GridLayout(rows, columns, 3, 3));

        setBorder(BorderFactory.createLineBorder(new Color(55, 57, 59), 4));
        setBackground(new Color(110, 112, 114));
    }

    private void initializeComponents() {
        kakuroCells = new CellBase[rows][columns];

        for (int row = 0; row < rows; ++row)
            for (int column = 0; column < columns; ++column)
                kakuroCells[row][column] = getCell(row, column);
    }

    private CellBase getCell(int row, int column) {
        boolean isFistLayer   = row == 0 || row == rows - 1 || column == 0 || column == columns - 1;
        boolean isSecondLayer = row == 1 || row == rows - 2 || column == 1 || column == columns - 2;

        return isFistLayer ? new EmptyCell(row, column) : isSecondLayer ? new ClueCell(row, column) : new ValueCell(row, column);
    }

    private void addComponents() {
        for (int row = 0; row < rows; ++row)
            for (int column = 0; column < columns; ++column)
                add(kakuroCells[row][column]);

        if (ApplicationWindow.window != null)
            ApplicationWindow.window.getContentPane().requestFocus();
    }

    //endregion

    public void changeToSuccessor(int row, int column) {
        kakuroCells[row][column] = kakuroCells[row][column].getSuccessor();

        removeAll();
        revalidate();
        addComponents();
        repaint();
    }

    public void setSwitchState() {
        for (CellBase[] rowCells: kakuroCells)
            for (CellBase cell: rowCells)
                cell.setupSwitchAction();
    }

    public void setEditState() {
        for (CellBase[] rowCells: kakuroCells)
            for (CellBase cell: rowCells)
                cell.setupEditAction();
    }

}
