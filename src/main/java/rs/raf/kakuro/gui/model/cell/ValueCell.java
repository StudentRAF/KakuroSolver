package rs.raf.kakuro.gui.model.cell;

import rs.raf.kakuro.gui.model.attribute.Notes;

public class ValueCell extends CellBase {

    private int value = 0;

    private ClueCell topClue  = null;
    private ClueCell leftClue = null;

    private final Notes notes = new Notes(true, true, true, true, true, true, true, true, true);

    public ValueCell(int row, int column) {
        super(row, column);
    }

    public ValueCell(ValueCell copy) {
        super(copy.getRow(), copy.getColumn());

        value = copy.getValue();
        notes.set(copy.getNotes());
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setTopClue(ClueCell topClue) {
        this.topClue = topClue;
    }

    public void setLeftClue(ClueCell leftClue) {
        this.leftClue = leftClue;
    }

    public int getValue() {
        return value;
    }

    public Notes getNotes() {
        return notes;
    }

    public ClueCell getTopClue() {
        return topClue;
    }

    public ClueCell getLeftClue() {
        return leftClue;
    }

    public ValueCell copy() {
        return new ValueCell(this);
    }

}
