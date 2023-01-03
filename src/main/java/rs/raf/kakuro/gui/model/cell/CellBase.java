package rs.raf.kakuro.gui.model.cell;

public abstract class CellBase {

    private final int row;
    private final int column;

    public CellBase(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CellBase cellBase))
            return false;

        return getRow() == cellBase.getRow() && getColumn() == cellBase.getColumn();
    }

}
