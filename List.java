/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }
    
    public Node getFirstNode()
    {
        return first;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        Node new_first = new Node (new CharData(chr), first); //creates a new node to be the first one on the list
        this.first = new_first; //assigns the new node
        this.size++; // updates the size of the list
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
        Node current = first; 
        String str = "(";
        while (current != null) //runs as lons as current is not null
        {
            str+=current.cp.toString() + " "; //ads the string 
            current = current.next; //moves to the next node
        }
        return (str.substring(0, str.length()-1) + ")"); //return the content of the list
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        Node current_node = first; 
        int count = 0; 
        while (current_node != null)
        {
            if (current_node.cp.equals(chr))
            {
                return count; //if the char is equal to the char in the current node, return it
            }
            count++; 
            current_node = current_node.next; // if not, moves to the next node
        }
        return -1; //if the char doesn't exist in the list, return -1
        
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
        if (indexOf(chr)!=-1) 
        {
            get(indexOf(chr)).count++; //if the char exist, increment the counter and leave the function
            return; 
        }
        addFirst(chr); //if the char doesn't exist, add it to the first of the list
    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        Node prev_node = first; 
        Node current_node = first.next;
        if (first.cp.equals(chr)) //checks if the char is equal to the first node
        {
            first = first.next; //updates the first node
            size--; //updates the size of the list
            return true; //return true
        }
        while (current_node != null)
        {
            if (current_node.cp.equals(chr))
            {
                prev_node.next = current_node.next; 
                size--; 
                return true; 
            }
            prev_node = prev_node.next;
            current_node = current_node.next;
        }
        return false; 
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        if (index >= size || index <0) //checks if the index in range
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            Node current_node = first; //assign the first node
            for (int i = 0; i<index; i++)
            {
                current_node = current_node.next; 
            }
            return current_node.cp; 
        }
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first;
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }
}
