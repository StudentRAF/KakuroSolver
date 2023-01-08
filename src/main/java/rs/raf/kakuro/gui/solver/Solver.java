package rs.raf.kakuro.gui.solver;

import rs.raf.kakuro.gui.controller.StepManager;
import rs.raf.kakuro.gui.controller.steps.AssignmentClueCellStep;
import rs.raf.kakuro.gui.controller.steps.AssignmentEmptyCellStep;
import rs.raf.kakuro.gui.controller.steps.AssignmentValueCellStep;
import rs.raf.kakuro.gui.controller.steps.CalculateBottomCombinationsStep;
import rs.raf.kakuro.gui.controller.steps.CalculateRightCombinationsStep;
import rs.raf.kakuro.gui.controller.steps.UpdateValueCellValueStep;
import rs.raf.kakuro.gui.controller.steps.TableBoundsStep;
import rs.raf.kakuro.gui.controller.steps.UpdateValueCellNotesStep;
import rs.raf.kakuro.gui.model.attribute.Notes;
import rs.raf.kakuro.gui.model.cell.CellBase;
import rs.raf.kakuro.gui.model.cell.ClueCell;
import rs.raf.kakuro.gui.model.cell.EmptyCell;
import rs.raf.kakuro.gui.model.cell.ValueCell;
import rs.raf.kakuro.gui.model.data.Bounds;
import rs.raf.kakuro.gui.model.data.Table;
import rs.raf.kakuro.gui.view.Editor;

public class Solver {

    private static final Editor editor = Editor.instance;

    private static Table editorTable;
    private static Table kakuroTable;

    public static void initialize() {
        clear();

        initializeTableBounds();
        initializeTableCells();
        initializeTableData();

        updateEditorValues();
    }

    private static void initializeTableBounds() {
        editorTable = new Table(editor.getCells());
        kakuroTable = new Table();

        Bounds bounds = new Bounds();

        for (int row = 0; row < editorTable.getHeight(); ++row)
            for (int column = 0; column < editorTable.getWidth(); ++column) {
                if (editorTable.getCell(row, column) instanceof EmptyCell)
                    continue;

                bounds.updateStartRow(row);
                bounds.updateEndRow(row);
                bounds.updateStartColumn(column);
                bounds.updateEndColumn(column);
             }

        kakuroTable.setBounds(bounds);

        StepManager.addStep(new TableBoundsStep(kakuroTable.getBounds()));
    }

    public static void initializeTableCells() {
        if (!kakuroTable.isValid())
            return;

        for (int row = 0; row < kakuroTable.getHeight(); ++row)
            for (int column = 0; column < kakuroTable.getWidth(); ++column) {
                CellBase editorCell = editorTable.getCell(row, column, kakuroTable.getBounds());
                if (editorCell instanceof ValueCell) {
                    ValueCell cell = new ValueCell(row, column);

                    kakuroTable.setCell(cell);

                    StepManager.addStep(new AssignmentValueCellStep(cell));
                }
                else if (editorCell instanceof ClueCell clueCell) {
                    ClueCell cell = new ClueCell(row, column);

                    cell.setRightClue(clueCell.getRightClue());
                    cell.setBottomClue(clueCell.getBottomClue());

                    kakuroTable.setCell(cell);

                    StepManager.addStep(new AssignmentClueCellStep(cell));
                }
                else {
                    EmptyCell cell = new EmptyCell(row, column);

                    kakuroTable.setCell(cell);

                    StepManager.addStep(new AssignmentEmptyCellStep(cell));
                }
            }
    }

    public static void initializeTableData() {
        for (int row = 0; row < kakuroTable.getHeight(); ++row)
            for (int column = 0; column < kakuroTable.getWidth(); ++ column) {
                if (!(kakuroTable.getCell(row, column) instanceof ClueCell clueCell))
                    continue;

                if (clueCell.getRightClue() != 0) {
                    int clueColumn = column;

                    while (++clueColumn < kakuroTable.getWidth() && (kakuroTable.getCell(row, clueColumn) instanceof ValueCell valueCell)) {
                        clueCell.addRightValueCell(valueCell);
                        valueCell.setLeftClue(clueCell);
                    }

                    clueCell.calculateRightCombinations();

                    StepManager.addStep(new CalculateRightCombinationsStep(clueCell));

                    Notes notesForCombinations = Notes.fromCombinations(clueCell.getRightCombinations());

                    for (ValueCell cell : clueCell.getRightValueCells().getCells()) {
                        Notes before = cell.getNotes().copy();

                        cell.getNotes().conjunction(notesForCombinations);

                        StepManager.addStep(new UpdateValueCellNotesStep(cell, notesForCombinations, before));

                        if (cell.getNotes().activeCount() == 1) {
                            cell.setValue(cell.getNotes().getActiveIndexes()[0] + 1);

                            StepManager.addStep(new UpdateValueCellValueStep(cell));
                        }
                    }
                }

                if (clueCell.getBottomClue() != 0) {
                    int clueRow = row;

                    while (++clueRow < kakuroTable.getHeight() && (kakuroTable.getCell(clueRow, column) instanceof ValueCell valueCell)) {
                        clueCell.addBottomValueCell(valueCell);
                        valueCell.setTopClue(clueCell);
                    }

                    clueCell.calculateBottomCombinations();

                    StepManager.addStep(new CalculateBottomCombinationsStep(clueCell));

                    Notes notesForCombinations = Notes.fromCombinations(clueCell.getBottomCombinations());

                    for (ValueCell cell : clueCell.getBottomValueCells().getCells()) {
                        Notes before = cell.getNotes().copy();

                        cell.getNotes().conjunction(notesForCombinations);

                        StepManager.addStep(new UpdateValueCellNotesStep(cell, notesForCombinations, before));

                        if (cell.getNotes().activeCount() == 1) {
                            cell.setValue(cell.getNotes().getActiveIndexes()[0] + 1);

                            StepManager.addStep(new UpdateValueCellValueStep(cell));
                        }
                    }
                }
            }
    }

    public static void removeExcessCellNotes() {
        for (int row = 0; row < kakuroTable.getHeight(); ++row)
            for (int column = 0; column < kakuroTable.getWidth(); ++ column) {
                if (!(kakuroTable.getCell(row, column) instanceof ClueCell clueCell))
                    continue;

                clueCell.removeExcess();
            }

        updateEditorValues();
    }


    public static Table kakuroTable() {
        return kakuroTable;
    }


    public static void updateEditorValues() {
        for (int row = 0; row < kakuroTable.getHeight(); ++row)
            for (int column = 0; column < kakuroTable.getWidth(); ++column) {
                if (kakuroTable.getCell(row, column) instanceof ValueCell kakuroValueCell) {
                    ValueCell editorValueCell = (ValueCell) editorTable.getCell(row, column, kakuroTable.getBounds());
                    editorValueCell.getNotes().set(kakuroValueCell.getNotes().getValues());
                    editorValueCell.setValue(kakuroValueCell.getValue());
                }
            }

        Editor.instance.repaint();
    }

    public static void clear() {
        kakuroTable = null;
        editorTable = null;
    }

}
