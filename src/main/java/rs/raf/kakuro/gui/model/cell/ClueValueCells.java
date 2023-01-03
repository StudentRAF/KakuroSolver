package rs.raf.kakuro.gui.model.cell;

import rs.raf.kakuro.gui.model.attribute.Notes;

import java.util.ArrayList;
import java.util.List;

public class ClueValueCells {

    private List<ValueCell> cells = new ArrayList<>();

    public ClueValueCells() { }

    public ClueValueCells(ClueValueCells copy) {
        for (ValueCell cell : copy.getCells())
            cells.add(cell.copy());
    }

    public void add(ValueCell cell) {
        cells.add(cell);
    }

    public void remove(ValueCell cell) {
        cells.remove(cell);
    }

    public ValueCell get(int index) {
        if (index >= size() || index < 0)
            return null;

        return cells.get(index);
    }

    public List<ValueCell> getCells() {
        return cells;
    }

    public List<Notes> getNotes() {
        List<Notes> notesList = new ArrayList<>();

        for (ValueCell cell : cells)
            notesList.add(cell.getNotes());

        return notesList;
    }

    public int size() {
        return cells.size();
    }

    public void clear() {
        cells.clear();
    }

    public ClueValueCells copy() {
        return new ClueValueCells(this);
    }

}
