package rs.raf.kakuro.gui.model;

public class ValueCell extends CellBase {

    private int value = 0;

    private final Boolean[] notes = new Boolean[] { false, false, false, false, false, false, false, false, false };

    public ValueCell(int row, int column) {
        super(row, column);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void activateIndex(int index) {
        notes[index] = true;
    }

    public void deactivateIndex(int index) {
        notes[index] = false;
    }

    public int getValue() {
        return value;
    }

    public Boolean[] getNotes() {
        return notes;
    }

}
