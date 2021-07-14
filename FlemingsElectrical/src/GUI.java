import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//GUI class
public class GUI  {
	
	//Initialize Global Variables
	private JFrame frame;
	private JLabel yearlabel, monthlabel, vatlabel, vatReflabel, goodslabel;
	private JButton vatButtonYes, vatButtonNo, cancelButton, saveButton, setButton;
	private JPanel panel;
	private JTextField yeartext, monthtext, vatText, goodsText;
	
	//GUI Constructor
	public GUI() {
		
		
		//Year Label
		yearlabel = new JLabel("Enter Year:");
		yearlabel.setBounds(10, 20, 80, 25);
		
		//Year Text
		yeartext = new JTextField(20);
		yeartext.setBounds(100, 20, 165, 25);
		
		//Month Label
		monthlabel = new JLabel("Enter Month:");
		monthlabel.setBounds(10, 50, 80, 25);
		
		//Month Text
		monthtext = new JTextField(20);
		monthtext.setBounds(100, 50, 165, 25);
		
		//setButton
		setButton = new JButton("Submit");
		setButton.setBounds(10, 80, 80, 25);
		setButton.addActionListener(new ButtonListener());
		
		//VAT Label
		vatlabel = new JLabel("Is VAT required for this Year/Month?");
		vatlabel.setBounds(10, 100, 250, 25);
		vatlabel.setVisible(false);
		
		//VAT Button
		vatButtonYes = new JButton("Yes");
		vatButtonYes.setBounds(10, 130, 80, 25);
		vatButtonYes.addActionListener(new ButtonListener());
		vatButtonYes.setVisible(false);
		
		//VAT Button
		vatButtonNo = new JButton("No");
		vatButtonNo.setBounds(100, 130, 80, 25);
		vatButtonNo.addActionListener(new ButtonListener());
		vatButtonNo.setVisible(false);
		
		//VAT Reference Label
		vatReflabel = new JLabel("Enter VAT Reference:");
		vatReflabel.setBounds(10, 160, 150, 25);
		vatReflabel.setVisible(false);
		
		//VAT Text
		vatText = new JTextField(20);
		vatText.setBounds(180, 160, 165, 25);
		vatText.setVisible(false);
		
		//Goods Amount Label
		goodslabel = new JLabel("Enter Amount of Goods:");
	    goodslabel.setBounds(10, 200, 150, 25);
	    goodslabel.setVisible(false);
	    
	    //Goods Text
	    goodsText = new JTextField(20);
		goodsText.setBounds(180, 200, 165, 25);
		goodsText.setVisible(false);
		
		//Cancel Button
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(100, 80, 80, 25);
		cancelButton.addActionListener(new ButtonListener());
		
		//Save Button
		saveButton = new JButton("Save");
		saveButton.setBounds(10, 250, 80, 25);
		saveButton.addActionListener(new ButtonListener());
		saveButton.setVisible(false);
		
		//Panel Parameters
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(null);
		
		//Add to Panel
		panel.add(yearlabel);
		panel.add(yeartext);
		panel.add(monthlabel);
		panel.add(monthtext);
		panel.add(vatlabel);
		panel.add(vatButtonYes);
		panel.add(vatButtonNo);
		panel.add(vatReflabel);
		panel.add(vatText);
		panel.add(goodslabel);
		panel.add(goodsText);
		panel.add(cancelButton);
		panel.add(saveButton);
		panel.add(setButton);
		
		//Frame Parameters
		frame = new JFrame();		
		frame.add(panel, BorderLayout.CENTER);
		frame.setSize(400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("PVA Fleming Electrical");
		frame.setVisible(true);
		
	}

	//Main Method - Just Calls GUI constructor
	public static void main(String[] args) {
		
		new GUI();

	}
	
	//Button Listener Class - Provide functionality to Buttons
	private class ButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == setButton) {
			
			//System.out.println("Submit Button Pressed");
			
			try {
			String yearStr = yeartext.getText();
			int year = Integer.parseInt(yearStr);
			
			String monthStr = monthtext.getText();
			int month = Integer.parseInt(monthStr);
			
			PVAclass pva = new PVAclass(year, month, "N/A" , (float)0.00);
			pva.setYear(year);
			pva.setMonth(month);
			
			
			vatlabel.setVisible(true);
			vatButtonYes.setVisible(true);
			vatButtonNo.setVisible(true);
			
			}catch (NumberFormatException e3) {
				JOptionPane.showMessageDialog(null, "Please Enter a Valid Input");
			}
		}
		
		//VAT Yes Button
		if(e.getSource() == vatButtonYes) {
			
			//System.out.println("Yes pressed");
			
			//Show Vat and Goods Amount Fields
			vatReflabel.setVisible(true);
			vatText.setVisible(true);
			goodslabel.setVisible(true);
			goodsText.setVisible(true);
			saveButton.setVisible(true);
			
			
		}
		
		//VAT No Button
		else if(e.getSource() == vatButtonNo) {
			
			//System.out.println("No Pressed");
			
			//Get text from textfield as String and Parse to Integer
			String yearStr = yeartext.getText();
			int year = Integer.parseInt(yearStr);
			
			String monthStr = monthtext.getText();
			int month = Integer.parseInt(monthStr);
			
			//Create new instance of PVAclass and input these values as parameters
			PVAclass pva = new PVAclass(year, month, "N/A" , (float)0.00);
			
			pva.setYear(year);
			pva.setMonth(month);
			
			
			
			//System.out.println("year  " + year);
			//System.out.println("month  " + month);
			
			//Write Object to Binary Text File and Close
			try { 
				
			    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("PVA_FlemingsElectrical.txt"));
				out.writeObject(pva);
				out.close();
				JOptionPane.showMessageDialog(null, "Details Succesfully Saved to the File: PVA_FlemingsElectrical.txt");
				System.exit(0);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		//Cancel Button
		else if(e.getSource() == cancelButton) {
			
			//System.out.println("Cancel Pressed");
			
			//Confirmation Dialog - if Yes, Close
			int yes = JOptionPane.showConfirmDialog(cancelButton, "Are You Sure?");
			if(yes == 0) {
				System.exit(0);
			}
			
		}
		
		//Save Button - Save all fields to a binary text file.
		else if(e.getSource() == saveButton) {
			
			//System.out.println("Save Pressed");
			
			int yes = JOptionPane.showConfirmDialog(saveButton, "Save and Quit?");
			if(yes == 0) {
			
			try {
	
			//Get all text fields as Strings and change them to corresponding data types
			String yearStr = yeartext.getText();
			int year = Integer.parseInt(yearStr);
			
			String monthStr = monthtext.getText();
			int month = Integer.parseInt(monthStr);
			
			String vatRef = vatText.getText();
			
			String goodsStr = goodsText.getText();
			float goods = Float.parseFloat(goodsStr);
			
			//Create new instance of PVAclass
			PVAclass pva1 = new PVAclass(year, month, vatRef, goods);
			
			pva1.setYear(year);
			pva1.setMonth(month);
			pva1.setVatReference(vatRef);
			pva1.setGoodsAmount(goods);
			
			//Write Object to Binary File and Close 
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("PVA_FlemingsElectrical.txt"));
			out.writeObject(pva1);
			out.close();
			JOptionPane.showMessageDialog(null, "Details Succesfully Saved to File: PVA_FlemingsElectrical.txt");
			System.exit(0);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NumberFormatException e3) {
				JOptionPane.showMessageDialog(null, "Please Enter a Valid Input");
			}
			
			/*try {
				ObjectInputStream is = new ObjectInputStream(new FileInputStream("PVA_FlemingsElectrical.txt"));
				PVAclass pvaIn = (PVAclass) is.readObject();
				JOptionPane.showMessageDialog(null,"Read Year = " + pvaIn.getYear() + "Month = " + pvaIn.getMonth() + "VAT Reference = " + pvaIn.getVatReference() + "Goods Amount = " + pvaIn.getGoodsAmount());
				is.close();
			
    			} catch (IOException e2) {	
				e2.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} */
			
				
			}	
		}				
	}
 }
}


