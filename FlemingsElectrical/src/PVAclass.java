import java.io.Serializable;

import javax.swing.JOptionPane;

//Base PVA Class for Flemings Electrical 
public class PVAclass implements Serializable {
		
	private static final long serialVersionUID = 1L;
	//Initialize Variables
	private int year; //>2020 <2050
	private int month; //1 - 12
	private String vatReference; //min 10 characters
	private float goodsAmount;  //allow negative
	
	//Constructor
	public PVAclass(int year, int month, String vatReference, float goodsAmount) {
		
		this.year = year;
		this.month = month;
		this.vatReference = vatReference;
		this.goodsAmount = goodsAmount;
	}

	//Get year
	public int getYear() {
			
		return year;
	}
	
	//Set year
	public void setYear(int year) {
		
		//check if within range
		if(year >= 2020 && year <= 2050) {
		this.year = year;
		}
		else{
			 
			JOptionPane.showMessageDialog(null, "Please enter a year within the correct range: 2020 - 2050");
		}
	}
	
	//get month
	public int getMonth() {
		return month;
	}

	//set month
	public void setMonth(int month) {
		
		//check if within range
		if(month >= 1 && month <= 12) {
		this.month = month;
		}
		else {
			JOptionPane.showMessageDialog(null, "Please enter a month within the correct range: 1 - 12");
		}
	}

	//get VAT reference
	public String getVatReference() {
		return vatReference;
	}
	
	//set VAT reference
	public void setVatReference(String vatReference) {
		
		//check form min amount of characters
		if(vatReference.length() >= 10) {
		this.vatReference = vatReference;
		}
		else {
			JOptionPane.showMessageDialog(null, "VAT Reference must contain a minimun of 10 characters. Please try again.");
		}
	}

	//get goods amount
	public float getGoodsAmount() {
		return goodsAmount;
	}

	//set goods amount
	public void setGoodsAmount(float goodsAmount) {
		
		//accept negative and positive values
		if(goodsAmount <= 0 || goodsAmount >= 0) {
		this.goodsAmount = goodsAmount;
		}
	}
	
	
	

}
