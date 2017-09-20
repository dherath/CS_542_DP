import java.util.ArrayList;

class Node
{
    private int bNumber; // the Bnumber of students
    // private ArrayList<String> subjects; // list of subjects taken by students
    private Node leftChild;
    private Node rightChild;

    public Node(){
	
    }

    public int getBNumber(){
	return bNumber;
    }

    public Node getLeftChild(){
	return leftChild;
    }

    public Node getRightChild(){
	return rightChild;
    }

}
