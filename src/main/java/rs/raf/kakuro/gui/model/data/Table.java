package rs.raf.kakuro.gui.model.data;

import rs.raf.kakuro.gui.model.cell.CellBase;

public class Table {

    private Bounds bounds;

    private CellBase[][] cells;

    public Table() { }

    public Table(CellBase[][] cells) {
        this.cells = cells;

        bounds = new Bounds(0, 0, cells.length - 1, cells[0].length - 1);
    }

    public void setBounds(Bounds bounds) {
        if (!bounds.isValid())
            return;

        this.bounds = bounds;
        cells = new CellBase[bounds.getHeight()][bounds.getWidth()];
    }

    public boolean isValid() {
        return bounds.isValid();
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setCell(CellBase cell) {
        cells[cell.getRow()][cell.getColumn()] = cell;
    }

    public CellBase getCell(int row, int column) {
        if (!bounds.isValid())
            return null;

        return cells[row][column];
    }

    public CellBase getCell(int row, int column, Bounds bounds) {
        return cells[bounds.getStartRow() + row][bounds.getStartColumn() + column];
    }

    public int getWidth() {
        return bounds.getWidth();
    }

    public int getHeight() {
        return bounds.getHeight();
    }

    public void clear() {
        bounds.clear();
        cells = null;
    }

}
