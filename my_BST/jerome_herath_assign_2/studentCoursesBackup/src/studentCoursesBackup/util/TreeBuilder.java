package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.binarySearchTree.BinarySearchTree;
import studentCoursesBackup.binarySearchTree.TreeInterface;

public class TreeBuilder 
{
   
    private TreeInterface masterTree;// main tree
    private TreeInterface backupTree1;// backup tree 1
    private TreeInterface backupTree2;// backup tree 2
    private FileProcessor inputFile;//input file
    private FileProcessor deleteFile;//file with deleted courses
    private String line; //line read at each iteration from file

    /**
     *Constructor
     *@param inputFileName, the string name for the input file
     *@param deleteFileName, the string name for the delete file
     **/
    public TreeBuilder(String inputFileName, String deleteFileName){
	//---------initialization of trees----------
	masterTree = new BinarySearchTree();
	backupTree1 = new BinarySearchTree();
	backupTree2 = new BinarySearchTree();
	//------------------------------------------
	inputFile = new FileProcessor(inputFileName);
	deleteFile = new FileProcessor(deleteFileName);
	try{
	    //----------insert operation----------------
	    line = inputFile.readLine();
	    while(line!=null){
		if(!isFormatValid(line)){
		    throw new RuntimeException("invalid line format in input.txt");
		}
		int tempIndex = getBNumber(line);
		populateTrees(tempIndex);//adds nodes or courses to trees(uses prototype pattern and Observer pattern)
		line = inputFile.readLine();
	    }
	    line ="";
	    //----------delete operation----------------
	    line = deleteFile.readLine();
	    while(line!=null){
		if(!isFormatValid(line)){
		    throw new RuntimeException("invalid line format in delete.txt");
		}
		int tempIndex = getBNumber(line);
		trimTrees(tempIndex);//removes courses from nodes(uses Observer pattern) 
		line = deleteFile.readLine();
	    }
	}catch(RuntimeException e){
	    e.printStackTrace();
	    System.exit(0);
	}finally{
	    inputFile.closeAll();
	    deleteFile.closeAll();	    
	}
    }

    /**
     *get function for specific tree
     *@param the flag for tree type, if 0 - main tree, 1-backup 1,2 - backup 2
     **/
    public TreeInterface getTree(int flag){
	TreeInterface tempTree = new BinarySearchTree();
	if(flag==0){
	    tempTree = masterTree;
	}else if(flag==1){
	    tempTree = backupTree1;
	}else if(flag==2){
	    tempTree = backupTree2;
	}
	return tempTree;
    }
    
    //--------------helper functions------------------

    /**
     *returns the BNumber from the read text file
     *@param the string input line
     *@return the BNumber in int format
     **/
    private int getBNumber(String sIn){
	//assuming the format would be 1234:A(course name) always
	return convertToInt(sIn.substring(0,4));
    }

    /**
     *Converts the string input to an Int
     *@param sIn the String input which is a number
     *@return the number in Int format
     **/
    private int convertToInt(String sIn){
	int number=0;
	for(int i=0;i<sIn.length();i++){
	    number = number*10+sIn.charAt(i)-'0';
	}
	return number;
    }

    /**
     *finds the course to be registered for student
     *@param sIn the course module to be entered
     *@return the String name of course
     **/
    private String findCourse(String sIn){
	//assuming there are no formatting issues with input text file
	return String.valueOf(sIn.charAt(sIn.length()-1));
    }

    /**
     *finds if a course given is valid
     *@param the course title
     *@return true if course is valid
     **/
    private boolean isCourseValid(String sIn){
	String list = "ABCDEFGHIJK";
	for(int i=0;i<list.length();i++){
	    String temp = String.valueOf(list.charAt(i));
	    if(temp.equals(sIn)){
		return true;
	    }
	}
	return false;
    }

    /**
     *function to check if an input line is valid
     *@param the input line 
     *@return true if format is valid
     **/
    private boolean isFormatValid(String sIn){
	if(sIn.length() > 6){
	    return false;
	}
	String sub = sIn.substring(0,4);
	String list ="0123456789";
	int count =0;
	for(int i=0;i<sub.length();i++){
	    for(int j=0;j<list.length();j++){
		if(sub.charAt(i)==list.charAt(j)){
		    count++;
		}
	    }
	}
	if(count<4){
	    return false;
	}
	return true;
    }

    //-------------------Tree functions---------------------------------
    
    /**
     *function to populate the three trees, clones nodes if necessary
     *@param the unique BNumber for the required node
     **/
    private void populateTrees(int nodeIndex){
        Node nodeFromMaster = masterTree.find(nodeIndex);
	String tempCourse = findCourse(line);
	if(isCourseValid(tempCourse)){
	    if(nodeFromMaster==null){
		//--------------------------------
		Node  masterNode = new Node(nodeIndex);
		masterNode.insertCourse(tempCourse);
		//--------------------------------
		Node backupNode1=null;
		Node backupNode2=null;
		if(masterNode instanceof Cloneable){
		    backupNode1 =  masterNode.clone();
		    backupNode2 =  masterNode.clone();
		    masterNode.registerObserver(backupNode1);
		    masterNode.registerObserver(backupNode2);
		}
		masterTree.insertNode(masterNode);
		backupTree1.insertNode(backupNode1);
		backupTree2.insertNode(backupNode2);
	    }else{
		nodeFromMaster.insertCourse(tempCourse);
		//-------------------------------
		Node backupNodes = backupTree1.find(nodeIndex);
		backupNodes.insertCourse(tempCourse);
		//-------------------------------
		backupNodes = backupTree2.find(nodeIndex);
		backupNodes.insertCourse(tempCourse);
	    }   
	}	    
    }

    /**
     *function to trim course lists of nodes in trees
     *@param the index of the node of the master tree
     **/
    private void trimTrees(int nodeIndex){
	Node nodeFromMaster = masterTree.find(nodeIndex);
	if(nodeFromMaster!=null){
	    String courseToDelete = findCourse(line);
	    nodeFromMaster.deleteCourse(courseToDelete);
	    nodeFromMaster.notifyAll(courseToDelete);
	}
    }
}
