package br.com.ifrn.ed;

import br.com.ifrn.ed.Stack.MyStack;
import br.com.ifrn.ed.Stack.StackException;
import br.com.ifrn.ed.gui.TrappedMouse;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Maze {
    TrappedMouse tp;
    private int rows = 0, cols = 0;//linhas e colunas do labirinto
    private Cell currentCell, exitCell, entryCell;
    private MyStack<String> mazeRows;
    private MyStack<Cell> mazeStack;
    private ArrayList<String> maze;

    private String sucesso = "/br/com/ifrn/ed/gui/Sucess.jpg";
    private ImageIcon iconSucess= new ImageIcon(getClass().getResource(sucesso));

    private String gameOver = "/br/com/ifrn/ed/gui/GameOver.png";
    private ImageIcon iconGameOver= new ImageIcon(getClass().getResource(gameOver));
    
    private final char exitMarker = 'e', entryMarker = 'm', visited = '.';
    private final char passage = '0', wall = '1';

    private final String fileMazeName = "maze.txt";

    private InputStreamReader isr;
    private FileInputStream fis;
    private BufferedReader br;

    private Scanner optionScanner;
    private int option;

    public Maze() {
        mazeRows = new MyStack<>(1000);
        entryCell = new Cell();
        exitCell = new Cell();
        maze = new ArrayList<>();
        optionScanner = new Scanner(System.in);

        do {
            System.out.println("Selecione a entrada para o labirinto:"
                    + "\n0 - Teclado\n1 - arquivo");
            option = optionScanner.nextInt();
        } while (option != 0 && option != 1);

        Input(option);
    }

    private void Input(int Input) {
        String str = null;

        try {
            fis = new FileInputStream(this.fileMazeName);
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado!");
        }

        if (Input == 0) {
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

            MyStack<String> mazeRowsAux = new MyStack<>(1000);//pilha auxiliar para inverter o labirinto

            while (!mazeRows.isEmpty()) {
                mazeRowsAux.push((String) mazeRows.pop());//Realizar pops na pilha adicionando em maze
            }
            while (!mazeRowsAux.isEmpty()) {
                maze.add((String) mazeRowsAux.pop());//Realizar pops na pilha adicionando em maze
            }

            maze.add(lineWall(cols)); //acrescenta a parede ao final do labirinto
            rows++;

        } catch (IOException ex) {
            System.out.println("Erro de leitura do arquivo!");
        } catch (StackException ex) {
            System.out.println("Erro na pilha!");
        }
    }

    private String lineWall(int tam) {
        String walls = "";
        for (int i = 0; i < tam; i++) {
            walls += wall;
        }
        return walls;
    }

    private void searchEntryAndExitCell(String str) {
        //A função indexOf pesquisa em um array de char
        if (str.indexOf(exitMarker) != -1) {
            exitCell.setX(rows);
            exitCell.setY(str.indexOf(exitMarker));
        }
        if (str.indexOf(entryMarker) != -1) {
            entryCell.setX(rows);
            entryCell.setY(str.indexOf(entryMarker));
        }
    }

    @Override
    public String toString() {
        String msg = "";        
        
        for (String arrayMaze : maze) {
            msg += arrayMaze + "\n";            
        }                
        
        return msg;
    }
    
    private void mountMaze(int r, int c){
        String lab = "";

        int mousePosition = (r*cols)+(c);                
        
        for (String arrayMaze : maze) {            
            lab += arrayMaze;
        }
        //System.out.println(mousePosition);
        tp.mountMaze(lab, mousePosition);
    }

    private boolean isVisited(int row, int col) {
        String marcado = maze.get(row);
        char[] toCharArray = marcado.toCharArray();
        Character pont = toCharArray[col];
        return pont.equals(visited);
    }

    private void marked(int row, int col) {
        String marcado = maze.get(row);
        char[] toCharArray = marcado.toCharArray();
        Character pont = toCharArray[col];

        if (pont.equals(passage) || pont.equals(entryMarker)) {
            if (!pont.equals(entryMarker)) {
                toCharArray[col] = visited;
            }
            marcado = new String(toCharArray);
            maze.set(currentCell.getX(), marcado);
        }
    }

    public void pushUnvisited(int row, int col) {
        String str = maze.get(row);
        char[] toCharArray = str.toCharArray();
        Character pont = toCharArray[col];

        if (pont.equals(passage) || pont.equals(exitMarker)) {
            try {
                mazeStack.push(new Cell(row, col));
            } catch (StackException ex) {
            }
        }
    }

    public void exitMaze() {       
        tp = new TrappedMouse(rows, cols);
        tp.setVisible(true);
        mazeStack = new MyStack<>(1000);
        currentCell = entryCell;        
        System.out.println(toString());
        while (!currentCell.equals(exitCell)) {
            int row = currentCell.getX(), col = currentCell.getY();
            if (!isVisited(row, col)) {
                marked(row, col);
                pushUnvisited(row + 1, col);//BAIXO
                pushUnvisited(row - 1, col);//CIMA
                pushUnvisited(row, col - 1);//ESQ         
                pushUnvisited(row, col + 1);//DIR                           
            } else if (mazeStack.isEmpty()) {
                System.out.println("Falha em sair do labirinto");
                JOptionPane.showMessageDialog(null, "Especial Mouse está com fome!", "Game Over!", JOptionPane.PLAIN_MESSAGE, iconGameOver);
                break;
            }

            try {
                currentCell = (Cell) mazeStack.pop();
            } catch (StackException ex) {
            }            
            System.out.println(toString());
            mountMaze(row, col);
        }
        
        if (currentCell.equals(exitCell)) {
            System.out.println("O rato está de barriga cheia!");
            int row = currentCell.getX(), col = currentCell.getY();
            mountMaze(row, col);
            JOptionPane.showMessageDialog(null, "Especial Mouse encontrou o queijo!", "Sucesso!", JOptionPane.PLAIN_MESSAGE, iconSucess);

        }
    }
}