package MultipleFiles_SearchAndCopy_FINAL;

	/*this program work properly.....
	But there will be a problem when the files names are same,
	even though they are in different directory.....*/

import java.io.*;
import java.util.*;
import java.util.zip.*;
import javax.swing.*;


public class FileCopy {

	JFrame f = new JFrame(); 
	int count;

	public FileCopy(String to_path, ArrayList<String> foundFile_path) throws IOException {
		System.out.println("\n\n.......FileCopy Class Starts........\n"); 
		//Creating directory and sub-directory
				File des_path = new File(to_path);
				if(des_path.getParentFile().exists()) {
					 System.out.println(des_path.getParent()+ " directory already exists ..."); 
					 des_path.createNewFile();
					 System.out.println(des_path.getName()+ " is successfully created....");
				}
				else {
					 System.out.println(des_path.getParent()+" directory not exists, creating now."); 
					 boolean success = des_path.getParentFile().mkdirs() ;
					 if (success) {
						 System.out.printf("Successfully created new directory : %s%n", des_path.getParent()); 
						 des_path.createNewFile();
						 System.out.println(des_path.getName()+ " is successfully created....");
					 } 
					 else {
						 throw new IOException("Failed to create directory " + des_path.getParent()); 
					 }
				}// Finished directory and sub-directory Creation
				
				// Now copyFile() method starts to copy
				copyFile(des_path, foundFile_path );
		
	}

	private void copyFile(File dest_path, ArrayList<String> foundFile_path) {
		try {
            // create byte buffer
            byte[] buffer = new byte[1024];
 
            FileOutputStream fos = new FileOutputStream(dest_path);
            ZipOutputStream zos = new ZipOutputStream(fos);
             
            for (String path: foundFile_path) {
                 
                File srcFile = new File(path);
 
                FileInputStream fis = new FileInputStream(srcFile);
                ZipEntry zip_Entry = new ZipEntry(srcFile.getName());
                zos.putNextEntry(zip_Entry);
                 
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fis.close(); 
            }
            zos.close();
            System.out.println("Files are copied successfully.");
	        JOptionPane.showMessageDialog(f,"Files are copied successfully.");    
        }
        catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }	
	}	
}
