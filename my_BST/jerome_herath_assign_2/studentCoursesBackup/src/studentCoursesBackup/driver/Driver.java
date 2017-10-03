package studentCoursesBackup.driver;

import studentCoursesBackup.util.TreeBuilder;
import studentCoursesBackup.util.Results;

public class Driver 
{

    public static void main(String[] args) 
    {

	//Name - Jerome Dinal Herath Muthukumaranage
	//B no - B00708543
	//email - jherath1@binghamton.edu
	try{
	    for(int i=0;i<args.length;i++){
		if(args[i].equals("${arg"+i+"}") || args.length!=5){
		    throw new RuntimeException("number of arguments not sufficant.\nRetry with exactly 5 arguments using the following format with absolute paths for files:\n-Darg0=target for input file\n-Darg1=target for delete file\n-Darg2=target for output from main tree\n-Darg3=target for output from backup tree 1\n-Darg4=target for output from backup tree 2\n-Darg5 & onwards are ignored ");		    
		}
	    }
	    TreeBuilder myTreeBuilder = new TreeBuilder(args[0],args[1]);
	    Results output1 = new Results(args[2]);//main tree
	    Results output2 = new Results(args[3]);//backup tree 1
	    Results output3 = new Results(args[4]);//backup tree 2

	    //---------------for master tree-------------------
	    output1.writeToStdout("initiating studentCoursesBackup");
	    output1.storeNewResult(myTreeBuilder.getTree(0).printNodes());
	    output1.writeToFile();
	    output1.writeToStdout("master tree complete");
	    //---------------for backup 1-----------------------
	    output2.storeNewResult(myTreeBuilder.getTree(1).printNodes());
	    output2.writeToFile();
	    output2.writeToStdout("backup tree 1 complete");
	    //--------------for backup 2----------------------
	    output3.storeNewResult(myTreeBuilder.getTree(2).printNodes());
	    output3.writeToFile();
	    output3.writeToStdout("backup tree 2 complete");
	    
	    output1.writeToStdout("studentCoursesbackup complete: \ncheck "+args[2]+", "+args[3]+", "+args[4]+" for output");
	}catch(RuntimeException e){
	    e.printStackTrace();
	    System.exit(2);
	}finally{}
    }
	
}
