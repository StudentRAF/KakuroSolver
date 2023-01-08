package rs.raf.kakuro.gui.model.cell;

import rs.raf.kakuro.gui.model.attribute.Combinations;

public class ClueCell extends CellBase {

    private int rightClue  = 0;
    private int bottomClue = 0;

    private final ClueValueCells rightValueCells  = new ClueValueCells();
    private final ClueValueCells bottomValueCells = new ClueValueCells();

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

    public void setRightCombinations(Combinations rightCombinations) {
        this.rightCombinations = rightCombinations;
    }

    public void setBottomCombinations(Combinations bottomCombinations) {
        this.bottomCombinations = bottomCombinations;
    }

    public void addRightValueCell(ValueCell cell) {
        rightValueCells.add(cell);
    }

    public void addBottomValueCell(ValueCell cell) {
        bottomValueCells.add(cell);
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

    public ClueValueCells getRightValueCells() {
        return rightValueCells;
    }

    public Combinations getBottomCombinations() {
        return bottomCombinations;
    }

    public ClueValueCells getBottomValueCells() {
        return bottomValueCells;
    }

}
