// Extracted from lecture slides

public class Stack<E>
{
    private Node <E> head;
    
    class Node<E>{
        E element;
        Node<E> next;

        public Node(E e, Node n)
        {
            element = e;
            next = n;
        }
    }

    public Stack()
    {
        head = null;
    }
    
    public boolean isEmpty(){
        return(head == null);
    }
    
    public void push(E e)
    {
        head = new Node<E>(e, head);
        //System.out.println("Pushed --> " + head.element);
    }

    public E pop()
    {
        if(isEmpty())
        {
            throw new NullPointerException();
        }
        else
        {
            E e = head.element;
            head = head.next;
            //System.out.println("Popped --> " + head);
            return e;
        }
    }

    public E top()
    {
        if(isEmpty())
        {
            throw new NullPointerException();
            
        }
        else
            return(head.element);
                
    }
}
