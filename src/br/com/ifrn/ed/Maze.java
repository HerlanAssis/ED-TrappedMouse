package br.com.ifrn.ed;

import br.com.ifrn.ed.Stack.MyStack;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Maze {

    private Cell currentCell;
    private Cell exitCell;
    private Cell entryCell;
    private MyStack<Cell> mazeStack;
    private ArrayList<String> maze;

    private final char exitMarker = 'e', entryMarker = 'm', visited = '.';
    private final char passage = '0', wall = '1';

    private InputStreamReader isr;
    private BufferedReader buffer;   
    private Scanner optionScanner;            
    private int option;

    public Maze() throws IOException {
        this.mazeStack = new MyStack<>(100);
        this.entryCell = new Cell();
        this.entryCell = new Cell();
        
        System.out.println("Selecione a entrada para o labirinto:"
                + "\n1 - Teclado\n2 - arquivo");
        do{
            option = optionScanner.nextInt();
        }while(option !=1 || option !=2);
        
        switch (option){
            case 1:
                break;
            case 2:
                break;
        }
        
        System.out.println("Digite um labirinto retangular utilizando os seguintes comandos: "
                + "\nm - entrada\ne - saida\n1 - parede\n0 - passagem\n "
                + "\nPara finalizar digite Ctrl-d:");
        
        isr = new InputStreamReader(System.in);
        buffer = new BufferedReader(isr);        
        try {
            String str = buffer.readLine();
            while (str != null) {
                str = buffer.readLine();
            }
            buffer.close();
            System.out.println(str);
        } catch (EOFException eofe) {

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
