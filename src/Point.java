public class Point {
    private int row;
    private int column;
    private Point parent;

    public Point(int row, int column){
        this.row = row;
        this.column = column;
        this.parent = null;
    }

    public Point(int row, int column, Point parent){
        this.row = row;
        this.column = column;
        this.parent = parent;
    }

    public int getRow() {return this.row;}
    public int getColumn() {return this.column;}
    public Point getParent() {return this.parent;}
    public void setParent(Point parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", this.row, this.column);
    }
}

