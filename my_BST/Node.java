import java.util.ArrayList;

class Node
{
    private int bNumber; // the Bnumber of students
    private ArrayList<String> subjects; // list of subjects taken by students
    private Node leftChild;
    private Node rightChild;

    public Node(int bNumberIn){
	bNumber = bNumberIn;
	subjects = new ArrayList<String>();
	leftChild = null;
	rightChild = null;
    }

    public int getBNumber(){
	return bNumber;
    }
    /*
    public void setBNumber(int xIn){
	bNumber = xIn;
	}*/

    public Node getLeftChild(){
	return leftChild;
    }

    public void setLeftChild(Node nodeIn){
	leftChild = nodeIn;
    }

    public Node getRightChild(){
	return rightChild;
    }

    public void insertSubject(String s){
	if(!subjects.contains(s)){
	    subjects.add(s);
	}
    }

}
