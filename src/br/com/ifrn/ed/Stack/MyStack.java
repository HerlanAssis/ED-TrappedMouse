package br.com.ifrn.ed.Stack;
/**
*
* @author herlan e savio
* @param <T> Class type generic
* Implement a stack of objects can stack arbitrary generic objects.
*/
public class MyStack<T> {

    private static final int CAPACITY = 10;
    private Object[] elements = {};
    private int stackPosition;

    /**
     * Standard stack builder.
     * */
    public MyStack() {
        this.stackPosition = -1;
        this.elements = new Object[this.CAPACITY];
    }

    /**
     * Builder, may receive as argument the initial size of the stack.
     * @param size Stack initial size
     * */
    public MyStack(int size) {
        this.stackPosition = -1;
        this.elements = new Object[size];
        //tratar erro de valor negativo para SIZE!
    }

    /**
     * Checks if the stack is empty.
     * @return Returns true if the Stack is empty
     * */
    public boolean isEmpty() {
        return this.stackPosition == -1;
    }

    /**
     * Checks if the stack is full.
     * @return Returns true if the Stack is full
     * */
    public boolean isFull() {
        return this.stackPosition == this.elements.length - 1;
    }

    /**
     * Returns the size of the stack.
     * @return Returns an integer referring to the size of the Stack
     * */
    public int size() {
        return this.stackPosition+1;
    }

    /**
     * Returns a reference to the element at the top of the stack.
     * @return Returns a reference to the element at the top of the stack.
     * */
    public T top() throws StackException {
        if (!isEmpty()) {
            return (T) this.elements[stackPosition];
        } else {
            throw new StackException("Stack underflow");
        }
    }

    /**
     * Inserts an element in the stack.
     * @param Receives the element to be added to the stack.
     * */
    public void push(T element) throws StackException {
        if (this.stackPosition < this.elements.length - 1) {
            this.elements[++stackPosition] = element;
        } else {
            throw new StackException("Stack overflow");
        }
    }

    /**
     * Removes and returns the element from the top of the stack.
     * @return Returns the element from the top of the stack.
     * */
    public Object pop() throws StackException {
        if (isEmpty()) {
            throw new StackException("Stack underflow");
        }
        
        Object removedElement = elements[this.stackPosition];
        this.stackPosition--;
        
        return removedElement;
    }

    /**
     * Makes the empty stack.
     * */
    public void clear() {
        this.stackPosition = -1;
    }

    /**
     * Prints the stack.
     * */
    public void print() throws StackException {        
        MyStack myStack = new MyStack(elements.length);
        
        Object tempElement;
        
        if (!isEmpty()) {            
            while(!isEmpty()){
                tempElement = pop();            
                myStack.push(tempElement);
                System.out.println("Element: " + tempElement);                
            }   
                        
            while(!myStack.isEmpty()){
                push((T) myStack.pop());
            }
            
        } else {
        	System.out.println("Nothing to show!");
            throw new StackException("Stack underflow");
        }
    }   
}
