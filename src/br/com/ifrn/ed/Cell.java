package br.com.ifrn.ed;

public class Cell {

    private int x;
    private int y;

    public Cell() {

    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Cell cell) {
        return this.x == cell.x && this.y == cell.y;
    }

}
