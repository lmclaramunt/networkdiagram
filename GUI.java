import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GUI {

	public JFrame frmTeam;
	private JTextField textFieldName, textFieldPredecessor, textFieldDuration;
	private JTextArea outputCreatedActivities, outputSortedPaths;
	private JButton btnRestart, btnAbout, btnEnter, btnSubmit, btnHelp;
	private List<Activity> activities;
	private NetworkDiagram network;		//Might or might not need it 
	
	public GUI(List<Activity> listActivity) {
		this.activities = listActivity;
		
		frmTeam = new JFrame();
		frmTeam.setTitle("Team 11");
		frmTeam.setBounds(100, 100, 577, 565);
		frmTeam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTeam.getContentPane().setLayout(null);
		
		//Labels 		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(27, 62, 120, 40);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmTeam.getContentPane().add(lblName);
		
		JLabel lblDependencies = new JLabel("Dependencies:");
		lblDependencies.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDependencies.setBounds(27, 168, 120, 40);
		frmTeam.getContentPane().add(lblDependencies);
		
		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDuration.setBounds(27, 274, 120, 40);
		frmTeam.getContentPane().add(lblDuration);
		
		JLabel lblPreview = new JLabel("Activities Created:");
		lblPreview.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPreview.setBounds(225, 76, 180, 26);
		frmTeam.getContentPane().add(lblPreview);
		
		JLabel lblSortedPath = new JLabel("Sorted Paths:");
		lblSortedPath.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSortedPath.setBounds(225, 270, 140, 26);
		frmTeam.getContentPane().add(lblSortedPath);
		
		//TextFields
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldName.setBounds(27, 115, 166, 40);
		frmTeam.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		textFieldName.setText("");
		
		textFieldPredecessor = new JTextField();
		textFieldPredecessor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPredecessor.setColumns(10);
		textFieldPredecessor.setBounds(27, 221, 166, 40);
		frmTeam.getContentPane().add(textFieldPredecessor);
		textFieldPredecessor.setText("");
		
		textFieldDuration = new JTextField();
		textFieldDuration.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldDuration.setColumns(10);
		textFieldDuration.setBounds(27, 327, 166, 40);
		frmTeam.getContentPane().add(textFieldDuration);
		textFieldDuration.setText("");
		
		//Text Area
		outputCreatedActivities = new JTextArea();			
		outputCreatedActivities.setEditable(false);
		JScrollPane scrollPanel1 = new JScrollPane(outputCreatedActivities);
		scrollPanel1.setBounds(225, 115, 300, 150);
		frmTeam.getContentPane().add(scrollPanel1);
		
		outputSortedPaths = new JTextArea();			
		outputSortedPaths.setEditable(false);
		JScrollPane scrollPanel2 = new JScrollPane(outputSortedPaths);
		scrollPanel2.setBounds(225, 300, 300, 150);
		frmTeam.getContentPane().add(scrollPanel2);
		
		//Buttons
		btnEnter = new JButton("Enter");
		btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnter.setBounds(27, 480, 97, 25);
		frmTeam.getContentPane().add(btnEnter);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(238, 481, 97, 25);
		frmTeam.getContentPane().add(btnSubmit);
	
		btnRestart = new JButton("Restart");
		btnRestart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRestart.setBounds(450, 481, 97, 25);
		frmTeam.getContentPane().add(btnRestart);
		
		//Add action Listeners
		ActionListener listener = new ButtonListener();
		btnEnter.addActionListener(listener);
		btnSubmit.addActionListener(listener);
		btnRestart.addActionListener(listener);
		
		//About button already has an action  
		btnAbout = new JButton("");
		btnAbout.setForeground(Color.BLACK);
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frmTeam, "FAQ: \n 1. This is a test \n 2. This is a test \n 3. This is a test", null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		btnAbout.setIcon(new ImageIcon(Main.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-confirm.png")));
		btnAbout.setBounds(0, 0, 48, 48);
		frmTeam.getContentPane().add(btnAbout);
		
		btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmTeam, "About: \n Hello and Welcome to Team 11's CSE 360 Project", null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btnHelp.setBounds(462, 0, 97, 25);
		frmTeam.getContentPane().add(btnHelp);
	}
	
	//This class determines what the buttons are actually going to do 
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {		
			List<String> predecessors = new ArrayList<String>();
			
			
			String nodeName = textFieldName.getText();
			String getDuration = textFieldDuration.getText();
			String getPredecessor = textFieldPredecessor.getText();
			predecessors = Arrays.asList(getPredecessor.split(" "));	//Split the input into multiple strings
			
			try {
	        	   int numberForTesting = Integer.parseInt(getDuration);	//Check if the user entered a number for the duration		
	           }
	           catch(NumberFormatException numberForTesting){				
	        	  //Display error message			
	           }  
			//Also, add code to check if the name and/or duration are empty 
			
			//User clicks enter
			if (event.getSource() == btnEnter) {
				Activity newActivity = new Activity(null);
				newActivity.setName(nodeName);
				newActivity.setDuration(Integer.parseInt(getDuration));
				for(int i = 0; i < predecessors.size(); i++) {			//Add each predecessor
					newActivity.addPredecessor(predecessors.get(i));
				}			
				
				activities.add(newActivity);	//Add the new Activity
				outputCreatedActivities.append(newActivity.toString());
			}
			
			//User clicks submit
			if(event.getSource() == btnSubmit) {
				outputSortedPaths.append(network.createTree(activities));
			}
			
			if(event.getSource() == btnRestart) {
				activities.clear();		//Work later on clearing the TextArea and TextField
				
			}
				
		}
	}
	
		
}