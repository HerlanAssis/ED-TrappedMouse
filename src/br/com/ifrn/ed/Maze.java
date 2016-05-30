package br.com.ifrn.ed;

import br.com.ifrn.ed.Stack.MyStack;
import br.com.ifrn.ed.Stack.StackException;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Maze {

    private int rows = 0, cols = 0;
    private Cell currentCell, exitCell, entryCell;
    private MyStack<String> mazeRows;
    private ArrayList<String> maze;

    private final char exitMarker = 'e', entryMarker = 'm', visited = '.';
    private final char passage = '0', wall = '1';

    private final String fileMazeName = "maze.txt";

    private InputStreamReader isr;
    private FileInputStream fis;
    private BufferedReader br;

    private Scanner optionScanner;
    private int option;

    public Maze() throws IOException, FileNotFoundException, StackException {
        this.mazeRows = new MyStack<>(100);
        this.entryCell = new Cell();
        this.entryCell = new Cell();
        maze = new ArrayList<>();
        this.optionScanner = new Scanner(System.in);

        do {
            System.out.println("Selecione a entrada para o labirinto:"
                    + "\n1 - Teclado\n2 - arquivo");
            option = optionScanner.nextInt();
        } while (option != 1 && option != 2);

        switch (option) {
            case 1:
                Input(true);
                break;
            case 2:
                Input(false);
                break;
        }
    }

    private void Input(boolean keyboardInput) throws FileNotFoundException, IOException, StackException {
        String str = null;

        fis = new FileInputStream(this.fileMazeName);

        if (keyboardInput) {
            isr = new InputStreamReader(System.in);
            System.out.println("Digite um labirinto retangular utilizando os seguintes comandos: "
                    + "\nm - entrada\ne - saida\n1 - parede\n0 - passagem\n "
                    + "\nPara finalizar digite Ctrl-d:");
        } else {
            isr = new InputStreamReader(fis);
        }

        br = new BufferedReader(isr);

        try {
            str = br.readLine();//ler a primeira entrada de dados            
            while (str != null) {
                this.rows++;
                str = this.wall + str + this.wall;
                this.cols = str.length();

                searchEntryAndExitCell(str);

                this.mazeRows.push(str);
                str = br.readLine();
            }
            maze.add(lineWall(cols)); //acrescenta a parede ao topo do labirinto
            rows++;
            MyStack<String> mazeRowsAux = new MyStack<>(100);//pilha auxiliar para inverter o labirinto

            while (!mazeRows.isEmpty()) {
                mazeRowsAux.push((String) mazeRows.pop());//Realizar pops na pilha adicionando em maze
            }
            while (!mazeRowsAux.isEmpty()) {
                maze.add((String) mazeRowsAux.pop());//Realizar pops na pilha adicionando em maze
            }

            maze.add(lineWall(cols)); //acrescenta a parede ao final do labirinto
            rows++;
        } catch (EOFException eOFException) {
        }       
    }

    private String lineWall(int tam) {
        String walls = "";

        for (int i = 0; i < tam; i++) {
            walls += this.wall;
        }

        return walls;
    }

    private void searchEntryAndExitCell(String str) {
        //A função indexOf pesquisa em um array de char
        if (str.indexOf(exitMarker) != -1) {
            this.exitCell.setX(rows);
            this.exitCell.setY(str.indexOf(exitMarker));
        }
        if (str.indexOf(entryMarker) != -1) {
            this.entryCell.setX(rows);
            this.entryCell.setY(str.indexOf(entryMarker));
        }
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
