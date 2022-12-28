package rs.raf.kakuro.gui.model;

public class ClueCell extends CellBase {

    private int rightClue  = 0;
    private int bottomClue = 0;

    public ClueCell(int row, int column) {
        super(row, column);
    }

    public void setRightClue(int rightClue) {
        this.rightClue = rightClue;
    }

    public void setBottomClue(int bottomClue) {
        this.bottomClue = bottomClue;
    }

    public int getRightClue() {
        return rightClue;
    }

    public int getBottomClue() {
        return bottomClue;
    }

}
