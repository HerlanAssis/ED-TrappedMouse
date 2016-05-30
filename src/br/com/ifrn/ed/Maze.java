package br.com.ifrn.ed;

import br.com.ifrn.ed.Stack.MyStack;
import java.util.ArrayList;

public class Maze {

    private Cell currentCell;
    private Cell exitCell;
    private Cell entryCell;
    private MyStack<Cell> mazeStack;
    private ArrayList<String> maze;

    private final char exitMarker = 'e', entryMarker = 'm', visited = '.';
    private final char passage = '0', wall = '1';

    public Maze() {
        this.mazeStack = new MyStack<>(100);
        this.entryCell = new Cell();
        this.entryCell = new Cell();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private void pushUnvisited(int row, int col) {

    }

    public void exitMaze() {

    }        
}