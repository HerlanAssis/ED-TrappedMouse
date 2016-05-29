package br.com.ifrn.ed;

import br.com.ifrn.ed.Stack.MyStack;
import java.util.ArrayList;

public class Maze {

    private Cell currentCell;
    private Cell exitCell;
    private Cell entryCell;
    private MyStack<Cell> mazeStack;
    private ArrayList<String> maze;
    private final char exitMarker = 'e';
    private final char entryMarker = 'm';
    private final char visited = '.';
    private final char passege = '0';
    private final char wall = '1';

    public void exitMaze() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
