package rs.raf.kakuro.gui.model.data;

public class Bounds {

    private int startRow    = Integer.MAX_VALUE;
    private int startColumn = Integer.MAX_VALUE;
    private int endRow      = Integer.MIN_VALUE;
    private int endColumn   = Integer.MIN_VALUE;

    public Bounds() { }

    public Bounds(int startRow, int startColumn, int endRow, int endColumn) {
        this.startRow    = startRow;
        this.startColumn = startColumn;
        this.endRow      = endRow;
        this.endColumn   = endColumn;
    }

    public void updateStartRow(int row) {
        startRow = Math.min(startRow, row);
    }

    public void updateStartColumn(int column) {
        startColumn = Math.min(startColumn, column);
    }

    public void updateEndRow(int row) {
        endRow = Math.max(endRow, row);
    }

    public void updateEndColumn(int column) {
        endColumn = Math.max(endColumn, column);
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public int getWidth() {
        return endColumn - startColumn + 1;
    }

    public int getHeight() {
        return endRow - startRow + 1;
    }

    public boolean isValid() {
        return startRow < endRow && startColumn < endColumn;
    }

    public void clear() {
        startRow    = Integer.MAX_VALUE;
        startColumn = Integer.MAX_VALUE;
        endRow      = Integer.MIN_VALUE;
        endColumn   = Integer.MIN_VALUE;
    }

}
