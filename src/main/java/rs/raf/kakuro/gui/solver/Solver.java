package rs.raf.kakuro.gui.solver;

import rs.raf.kakuro.gui.controller.StepManager;
import rs.raf.kakuro.gui.controller.steps.AssignmentClueCellStep;
import rs.raf.kakuro.gui.controller.steps.AssignmentEmptyCellStep;
import rs.raf.kakuro.gui.controller.steps.AssignmentNotesStep;
import rs.raf.kakuro.gui.controller.steps.AssignmentValueCellStep;
import rs.raf.kakuro.gui.controller.steps.CalculateBottomCombinationsStep;
import rs.raf.kakuro.gui.controller.steps.CalculateRightCombinationsStep;
import rs.raf.kakuro.gui.controller.steps.ExcessCombinationsStep;
import rs.raf.kakuro.gui.controller.steps.ExcessNotesStep;
import rs.raf.kakuro.gui.controller.steps.TableBoundsStep;
import rs.raf.kakuro.gui.controller.steps.UpdateValueStep;
import rs.raf.kakuro.gui.model.attribute.Combinations;
import rs.raf.kakuro.gui.model.attribute.Notes;
import rs.raf.kakuro.gui.model.cell.CellBase;
import rs.raf.kakuro.gui.model.cell.ClueCell;
import rs.raf.kakuro.gui.model.cell.ClueValueCells;
import rs.raf.kakuro.gui.model.cell.EmptyCell;
import rs.raf.kakuro.gui.model.cell.ValueCell;
import rs.raf.kakuro.gui.model.data.Bounds;
import rs.raf.kakuro.gui.model.data.Table;
import rs.raf.kakuro.gui.view.Editor;
import rs.raf.kakuro.gui.view.solution.SolutionWindow;

import java.util.List;

public class Solver {

    private static final Editor editor = Editor.instance;

    private static Table editorTable;
    private static Table kakuroTable;

    public static void solve() {
        clear();

        initializeTables();
        assignKakuroTableCells();
        assignKakuroTableMetaData();
        removeExcessCellNotes();
    }

    private static void initializeTables() {
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

    public static void assignKakuroTableCells() {
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

    public static void assignKakuroTableMetaData() {
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

                    clueCell.setRightCombinations(Algorithms.combinationsOfNonRepeatingDigitsWithSum(clueCell.getRightClue(), clueCell.getRightValueCellCount()));

                    StepManager.addStep(new CalculateRightCombinationsStep(clueCell));

                    Notes notesForCombinations = Notes.fromCombinations(clueCell.getRightCombinations());

                    for (ValueCell cell : clueCell.getRightValueCells().getCells()) {
                        Notes oldNotes = cell.getNotes().copy();

                        cell.getNotes().conjunction(notesForCombinations);

                        StepManager.addStep(new AssignmentNotesStep(cell, notesForCombinations, oldNotes));

                        if (cell.getNotes().activeCount() == 1) {
                            cell.setValue(cell.getNotes().getActiveIndexes()[0] + 1);

                            StepManager.addStep(new UpdateValueStep(cell));
                        }
                    }
                }

                if (clueCell.getBottomClue() != 0) {
                    int clueRow = row;

                    while (++clueRow < kakuroTable.getHeight() && (kakuroTable.getCell(clueRow, column) instanceof ValueCell valueCell)) {
                        clueCell.addBottomValueCell(valueCell);
                        valueCell.setTopClue(clueCell);
                    }

                    clueCell.setBottomCombinations(Algorithms.combinationsOfNonRepeatingDigitsWithSum(clueCell.getBottomClue(), clueCell.getBottomValueCellCount()));

                    StepManager.addStep(new CalculateBottomCombinationsStep(clueCell));

                    Notes notesForCombinations = Notes.fromCombinations(clueCell.getBottomCombinations());

                    for (ValueCell cell : clueCell.getBottomValueCells().getCells()) {
                        Notes oldNotes = cell.getNotes().copy();

                        cell.getNotes().conjunction(notesForCombinations);

                        StepManager.addStep(new AssignmentNotesStep(cell, notesForCombinations, oldNotes));

                        if (cell.getNotes().activeCount() == 1) {
                            cell.setValue(cell.getNotes().getActiveIndexes()[0] + 1);

                            StepManager.addStep(new UpdateValueStep(cell));
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

                removeExcess(clueCell, clueCell.getRightClue(),  clueCell.getRightCombinations(),  clueCell.getRightValueCells() , true);
                removeExcess(clueCell, clueCell.getBottomClue(), clueCell.getBottomCombinations(), clueCell.getBottomValueCells(), false);
            }
    }

    private static void removeExcess(ClueCell cell, int clue, Combinations combinations, ClueValueCells clueValueCells, boolean isRight) {
        if (combinations == null)
            return;

        Combinations   oldCombinations   = combinations.copy();
        ClueValueCells oldClueValueCells = clueValueCells.copy();

        Algorithms.removeExcessCombinationsAndNotes(clue, clueValueCells.size(), combinations, clueValueCells.getNotes());

        notifyExcessCombinations(cell, oldCombinations, isRight);
        notifyExcessNotes(clueValueCells.getCells(), oldClueValueCells.getNotes());

        removeExcessChanges(clueValueCells, oldClueValueCells, isRight);
    }

    private static void notifyExcessCombinations(ClueCell cell, Combinations oldCombinations, boolean isRight) {
        StepManager.addStep(new ExcessCombinationsStep(cell, oldCombinations, isRight));
    }

    private static void notifyExcessNotes(List<ValueCell> valueCells, List<Notes> oldNotesList) {
        for (int index = 0; index < valueCells.size(); ++index) {
            ValueCell valueCell = valueCells.get(index);
            Notes     oldNotes  = oldNotesList.get(index);

            StepManager.addStep(new ExcessNotesStep(valueCell, oldNotes));

            if (valueCell.getValue() == 0 && valueCell.getNotes().activeCount() == 1) {
                valueCell.setValue(valueCell.getNotes().getActiveIndexes()[0] + 1);

                StepManager.addStep(new UpdateValueStep(valueCell));
            }
        }
    }

    private static void removeExcessChanges(ClueValueCells clueValueCells, ClueValueCells oldValueCells, boolean isRight) {
        for (int index = 0; index < clueValueCells.size(); ++index) {
            ValueCell valueCell = clueValueCells.get(index);

            if (valueCell.getNotes().equals(oldValueCells.get(index).getNotes()))
                continue;

            ClueCell clueCell = valueCell.getLeftClue();
            if (clueCell != null && !isRight)
                removeExcess(clueCell, clueCell.getRightClue(), clueCell.getRightCombinations(), clueCell.getRightValueCells(), true);

            clueCell = valueCell.getTopClue();
            if (clueCell != null && isRight)
                removeExcess(clueCell, clueCell.getBottomClue(), clueCell.getBottomCombinations(), clueCell.getBottomValueCells(), false);
        }
    }

    public static Table kakuroTable() {
        return kakuroTable;
    }

    public static Table editorTable() {
        return editorTable;
    }

    public static void clear() {
        kakuroTable = null;
        editorTable = null;

        StepManager.clear();
        Editor.instance.clear();
        SolutionWindow.window.clear();
    }

}
