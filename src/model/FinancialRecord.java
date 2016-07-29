package model;

public class FinancialRecord {
	private String label;
	private double price;
	
	public FinancialRecord(String label, double price) {
		super();
		this.label = label;
		this.price = price;
	}
	public String getLabel() {
		return label;
	}
	public double getPrice() {
		return price;
	}
}
