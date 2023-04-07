package MultipleFiles_SearchAndCopy_FINAL;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class GUI {

	static JFrame frame;
	static JTextField textField;
	static JLabel lblNewLabel_1;
	static JTextField textField_1;
	static JLabel lblNewLabel_2;
	static JTextArea textArea;
	static JButton btnNewButton;
	static JButton btnCancel;

	GUI() {
		initialize();
	}
	public void initialize() {
		frame = new JFrame("File Information");
		frame.setSize(550,640); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("File Location : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(34, 22, 462, 25);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		lblNewLabel.setLabelFor(textField);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(35, 49, 461, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("File Destination : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(34, 86, 462, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		lblNewLabel_1.setLabelFor(textField_1);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(34, 112, 461, 32);
		frame.getContentPane().add(textField_1);
		
		lblNewLabel_2 = new JLabel("File Names : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(34, 146, 462, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea.setBounds(34, 173, 468, 368);
		frame.getContentPane().add(textArea);
		
		btnNewButton = new JButton("SEARCH");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String from_path = textField.getText();
				String to_path = textField_1.getText();
				String[] file_array = textArea.getText().split("\n");
				
				// declare an array list that hold strings
				ArrayList<String> file_list = new ArrayList<String>(); 
				// add elements of array to array list
				file_list.addAll(Arrays.asList(file_array)); 
				
				//getList() method is called to get the paths and list
				try {
					getList(from_path, to_path, file_list);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				frame.dispose();
			}
		});
		btnNewButton.setBounds(34, 552, 95, 25);
		frame.getContentPane().add(btnNewButton);
		
		btnCancel = new JButton("CANCEL");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(139, 552, 95, 25);
		frame.getContentPane().add(btnCancel);
		
		frame.setVisible(true);
	}
	
	private void getList(String from_path, String to_path, ArrayList<String> file_list) throws IOException {
		System.out.println("\nSource File path :"+from_path);
		System.out.println("Destination File path :"+to_path);
		System.out.println("File list are : ");
		for (String f : file_list) {
			System.out.println(f);
		}
		System.out.println("\nTill this portion OK...........\n");
		// Directory paths & file list are send to FileSearch  class
		FileSearch fileSearch = new FileSearch(from_path, to_path, file_list);		
	}

}
