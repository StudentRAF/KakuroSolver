package rs.raf.kakuro.gui.solver;

import rs.raf.kakuro.gui.model.cell.CellBase;
import rs.raf.kakuro.gui.model.cell.ClueCell;
import rs.raf.kakuro.gui.model.cell.EmptyCell;
import rs.raf.kakuro.gui.model.cell.ValueCell;
import rs.raf.kakuro.gui.view.Editor;

public class Solver {

    private static int startRow;
    private static int startColumn;
    private static int endRow;
    private static int endColumn;

    private static CellBase[][] cells;
    private static CellBase[][] kakuro;

    public static void initialize() {
        clear();

        initializeEditorTable();
        initializeKakuroTable();
        initializeKakuroCells();

        updateEditorValues();
    }

    private static void initializeEditorTable() {
        cells = Editor.instance.getCells();

        for (int row = 0; row < cells.length; ++row)
            for (int column = 0; column < cells[row].length; ++column) {
                if (cells[row][column] instanceof EmptyCell)
                    continue;

                startRow    = Math.min(startRow,    row   );
                endRow      = Math.max(endRow,      row   );
                startColumn = Math.min(startColumn, column);
                endColumn   = Math.max(endColumn,   column);
             }
    }

    public static void initializeKakuroTable() {
        if (startRow > endRow)
            return;

        kakuro = new CellBase[endRow - startRow + 1][endColumn - startColumn + 1];

        for (int row = 0; row < kakuro.length; ++row)
            for (int column = 0; column < kakuro[row].length; ++column) {
                if (cells[row + startRow][column + startColumn] instanceof ValueCell)
                    kakuro[row][column] = new ValueCell(row, column);
                else if (cells[row + startRow][column + startColumn] instanceof ClueCell clueCell) {
                    ClueCell cell = new ClueCell(row, column);

                    cell.setRightClue(clueCell.getRightClue());
                    cell.setBottomClue(clueCell.getBottomClue());

                    kakuro[row][column] = cell;
                }
                else if (cells[row + startRow][column + startColumn] instanceof EmptyCell)
                    kakuro[row][column] = new EmptyCell(row, column);

            }
    }

    public static void initializeKakuroCells() {
        for (int row = 0; row < kakuro.length; ++row)
            for (int column = 0; column < kakuro[row].length; ++ column) {
                if (!(kakuro[row][column] instanceof ClueCell clueCell))
                    continue;

                if (clueCell.getRightClue() != 0) {
                    int clueColumn = column;

                    while (++clueColumn < kakuro[row].length && (kakuro[row][clueColumn] instanceof ValueCell valueCell)) {
                        clueCell.addRightValueCell(valueCell);
                        valueCell.setLeftClue(clueCell);
                    }

                    clueCell.calculateRightCombinations();
                }

                if (clueCell.getBottomClue() != 0) {
                    int clueRow = row;

                    while (++clueRow < kakuro.length && (kakuro[clueRow][column] instanceof ValueCell valueCell)) {
                        clueCell.addBottomValueCell(valueCell);
                        valueCell.setTopClue(clueCell);
                    }

                    clueCell.calculateBottomCombinations();
                }
            }
    }

    public static void removeExcessCellNotes() {
        for (int row = 0; row < kakuro.length; ++row)
            for (int column = 0; column < kakuro[row].length; ++ column) {
                if (!(kakuro[row][column] instanceof ClueCell clueCell))
                    continue;

                clueCell.removeExcess();
            }

        updateEditorValues();
    }

    public static void updateEditorValues() {
        for (int row = 0; row < kakuro.length; ++row)
            for (int column = 0; column < kakuro[row].length; ++column) {
                if (kakuro[row][column] instanceof ValueCell kakuroValueCell) {
                    ValueCell editorValueCell = (ValueCell) cells[startRow + row][startColumn + column];
                    editorValueCell.getNotes().set(kakuroValueCell.getNotes().getValues());
                    editorValueCell.setValue(kakuroValueCell.getValue());
                }
            }

        Editor.instance.repaint();
    }

    public static void clear() {
        startRow    = Editor.rows;
        startColumn = Editor.columns;
        endRow      = 0;
        endColumn   = 0;

        cells = null;
    }

}
