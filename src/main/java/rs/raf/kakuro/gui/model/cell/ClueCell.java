package rs.raf.kakuro.gui.model.cell;

import rs.raf.kakuro.gui.model.attribute.Combinations;
import rs.raf.kakuro.gui.model.attribute.Notes;
import rs.raf.kakuro.gui.solver.Algorithms;

public class ClueCell extends CellBase {

    private int rightClue  = 0;
    private int bottomClue = 0;

    private ClueValueCells rightValueCells  = new ClueValueCells();
    private ClueValueCells bottomValueCells = new ClueValueCells();

    private Combinations rightCombinations;
    private Combinations bottomCombinations;

    public ClueCell(int row, int column) {
        super(row, column);
    }

    public void setRightClue(int rightClue) {
        this.rightClue = rightClue;
    }

    public void setBottomClue(int bottomClue) {
        this.bottomClue = bottomClue;
    }

    public void addRightValueCell(ValueCell cell) {
        rightValueCells.add(cell);
    }

    public void addBottomValueCell(ValueCell cell) {
        bottomValueCells.add(cell);
    }

    public void calculateRightCombinations() {
        rightCombinations = Algorithms.combinationsOfNonRepeatingDigitsWithSum(rightClue, getRightValueCellCount());

        Notes notesForCombinations = Notes.fromCombinations(rightCombinations);

        for (ValueCell cell : rightValueCells.getCells()) {
            cell.getNotes().conjunction(notesForCombinations);

            if (cell.getNotes().activeCount() == 1)
                cell.setValue(cell.getNotes().getActiveIndexes()[0] + 1);
        }
    }

    public void calculateBottomCombinations() {
        bottomCombinations = Algorithms.combinationsOfNonRepeatingDigitsWithSum(bottomClue, getBottomValueCellCount());

        Notes notesForCombinations = Notes.fromCombinations(bottomCombinations);

        for (ValueCell cell : bottomValueCells.getCells()) {
            cell.getNotes().conjunction(notesForCombinations);

            if (cell.getNotes().activeCount() == 1)
                cell.setValue(cell.getNotes().getActiveIndexes()[0] + 1);
        }
    }

    public void removeExcess() {
        removeRightExcess();
        removeBottomExcess();
    }

    private void removeRightExcess() {
        if (rightCombinations == null)
            return;

        ClueValueCells oldValueCells = rightValueCells.copy();

        Algorithms.removeExcessCombinationsAndNotes(rightClue, getRightValueCellCount(), rightCombinations, rightValueCells.getNotes());

        removeExcessChanges(rightValueCells, oldValueCells);
    }

    private void removeBottomExcess() {
        if (bottomCombinations == null)
            return;

        ClueValueCells oldValueCells = bottomValueCells.copy();

        Algorithms.removeExcessCombinationsAndNotes(bottomClue, getBottomValueCellCount(), bottomCombinations, bottomValueCells.getNotes());

        removeExcessChanges(bottomValueCells, oldValueCells);
    }

    private void removeExcessChanges(ClueValueCells clueValueCells, ClueValueCells oldValueCells) {
        for (int index = 0; index < clueValueCells.size(); ++index)
            if (!clueValueCells.get(index).getNotes().equals(oldValueCells.get(index).getNotes())) {
                ValueCell cell = clueValueCells.get(index);

                if (cell.getNotes().activeCount() == 1)
                    cell.setValue(cell.getNotes().getActiveIndexes()[0] + 1);

                if (cell.getTopClue() != null)
                    cell.getTopClue().removeExcess();

                if (cell.getLeftClue() != null)
                    cell.getLeftClue().removeExcess();
            }
    }

    public int getRightValueCellCount() {
        return rightValueCells.size();
    }

    public int getBottomValueCellCount() {
        return bottomValueCells.size();
    }

    public int getRightClue() {
        return rightClue;
    }

    public int getBottomClue() {
        return bottomClue;
    }

    public Combinations getRightCombinations() {
        return rightCombinations;
    }

    public Combinations getBottomCombinations() {
        return bottomCombinations;
    }

}
