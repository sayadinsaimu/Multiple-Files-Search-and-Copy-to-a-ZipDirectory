package MultipleFiles_SearchAndCopy_FINAL;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class FileSearch {
	JFrame f = new JFrame();  
	static boolean found = false;
	static boolean flag = false;
	String file_dir = null;
	 
	static ArrayList<String> foundFile_path = new ArrayList<String>();
	static ArrayList <String> foundFile = new ArrayList<String>();
	//ArrayList <String> file_list = new ArrayList<String>();
	static String[] str_list;
	
	FileSearch(String from_path, String to_path, ArrayList<String> file_list) throws IOException{
		System.out.println("\n\n.......FileSearch Class Starts........\n");
		 //copy the file_list to a string array
		 str_list = file_list.toArray(new String[file_list.size()]);
		 
		 //Searching starts..........	
		 File src_path = new File(from_path);
		 for(String file_name: str_list) {
			find_File(src_path, file_name);
			if(flag) {
				foundFile.add(file_name);
				file_list.remove(file_name);
				flag = false;
			}
		}
		 if(foundFile.size()==str_list.length) {
			 System.out .println("\nAll files are found...\n");
			 JOptionPane.showMessageDialog(f,"All files are found...");
			 foundFile.clear();
			 System.out.println("\nFound File Location : ");
				for (String fPath : foundFile_path) {
					System.out.println(fPath); 
				}
			 
		 }
		 else if(foundFile_path.size()>0) {
			//JOptionPane.showMessageDialog(f,"Found File : " + foundFile);
			JOptionPane.showMessageDialog(f,"Not found File : " + file_list);
			System.out.print("\nFound File : ");
			System.out.println(foundFile); 
			System.out.println("\nFound File Location : ");
			for (String fPath : foundFile_path) {
				System.out.println(fPath); 
			}
				
		}
		else {
			System.out .println("\nNo file founds...\n");
			JOptionPane.showMessageDialog(f,"No file founds...");
			System.exit(0);
		}
		//Destination paths & found file list are send to FileCopy class
		FileCopy fileCopy = new FileCopy(to_path, foundFile_path);
		
	}

	private void find_File(File dir_path, String file_name) {
	
		if (dir_path.isDirectory()) {
			File[] listOfFiles = dir_path.listFiles();
			if (listOfFiles.length < 1) {
				System.out.println("There is no File inside the Directory.");
			}
			else {					
				//print the file or folder list inside the directory
				for (File file_or_folder : listOfFiles) {	
					System.out.println((file_or_folder.toString()));
				   	if (file_or_folder.isDirectory()) {
				   		find_File(file_or_folder, file_name);
				   		System.out .print("\n");
		       		 }
		    	
	        		 if (file_or_folder.getName().toLowerCase().contains(file_name.toLowerCase())){
	        			 found = true;
	        			 file_dir = file_or_folder.toString(); 	 
		        		 foundFile_path.add(file_dir);  
	        		 }
		        }
		    }
		
			if (found) {
				System.out .println("\n"+ file_name+" File is found.\n");
				found = false;  flag = true;
				
			}
			else{
				System.out .println("\n"+ file_name+" File is not found.\n");
			}	
		}
		else {
			System.out .println("There is no Folder @ given path : " + dir_path);
			JOptionPane.showMessageDialog(f,"There is no Folder @ given path : " + dir_path);
			System.exit(0);
		}   	  
	}	
}
