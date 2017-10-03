package studentCoursesBackup.myTree;

import java.util.ArrayList;
import studentCoursesBackup.myTree.SubjectInterface;
import studentCoursesBackup.myTree.ObserverInterface;

public class Node implements SubjectInterface, ObserverInterface, Cloneable
{
    private int bNumber; //unique ID
    private ArrayList<String> courses;//course list
    private ArrayList<ObserverInterface> observers;//for observers
    private Node leftChild; // left child
    private Node rightChild;//right child
    
    /**
     *Constructor for specific node
     *@param bNumberin, the BNumber of student
     **/
    public Node(int bNumberIn){
	bNumber = bNumberIn;
	courses = new ArrayList<String>();
	observers = new ArrayList<ObserverInterface>();
	leftChild = null;
	rightChild = null;
    }
    
    /**
     *gets the index of the node
     *@return the index(BNumber)
     **/
    public int getNodeIndex(){
	return this.bNumber;
    }

    /**
     *gets the right child of the node
     *@return the right child
     **/
    public Node getRightChild(){
	return this.rightChild;
    }

    /**
     *setter for right child
     *@param nodeIn, the node to be added
     **/
    public void setRightChild(Node nodeIn){
	rightChild = nodeIn;
    }

    /**
     *get method for left child
     *@return the left child
     **/
    public Node getLeftChild(){
	return this.leftChild;
    }

    /**
     *sets left child
     *@param nodeIn, the left child node
     **/
    public void setLeftChild(Node nodeIn){
	leftChild = nodeIn;
    }

    /**
     *inserts Course for a specific node
     *@param the course to be inserted
     **/
    public void insertCourse(String sIn){
	if(!courses.contains(sIn)){
	    courses.add(sIn);
	}
    }

    /**
     *deletes a course from a specific node
     *@param the course to be deleted
     **/
    public void deleteCourse(String sIn){
	if(courses.contains(sIn)){
	    courses.remove(courses.indexOf(sIn));
	}
    }

    /**
     *gets all course list for a node
     *@return the arraylist containing all courses
     **/
    public ArrayList<String> getAllCourses(){
	return courses;
    }

    //------------Prototype Pattern Implementation------------------
    /**
     *Implementation of clone()
     *@return a cloned Object
     **/
    public Node clone(){
	Node temp = null;
	try{
	    temp = (Node) super.clone();
	} catch (CloneNotSupportedException e){
	    e.printStackTrace();
	    System.exit(1);
	}
	return temp;
    }    
    
    //-------------------Observer Interface--------------------------

    /**
     *updates observer as to delete or insert a course
     *@param oIn the ObserverInterface
     *@param sIn, the subject name to be inserted or deleted
     **/
    public void update(ObserverInterface oIn, String sIn){
	Node temp = (Node) oIn ;
	temp.deleteCourse(sIn);
	
    }

    //-------------------Subject Interface---------------------------

    /**
     *adds observers to ArrayList
     *@param the ObserverInterface to be added
     **/
    public void registerObserver(ObserverInterface oIn){
	observers.add(oIn);
    }

    /**
     *removes observer from ArrayList
     *@param the Observerinterface to be removed
     **/
    public void removeObserver(ObserverInterface oIn){
	if(observers.contains(oIn)){
	    observers.remove(oIn);
	}
    }

    /**
     *updates state of all Observers
     *@param operationIndex - 1 for insertion, 2 for deletion
     *@param sIn, the course name to be inserted or deleted
     **/
    public void notifyAll(String sIn){
	if(!observers.isEmpty()){
	    for(ObserverInterface temp : observers){
		update(temp,sIn);
	    }
	}
    }
}
