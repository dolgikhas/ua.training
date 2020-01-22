package ua.training.model;

public class NoTaxReportException extends Exception {
	private String attribute;
	private String value;
	
	public NoTaxReportException(String attribute, String value) {
		this.attribute = attribute;
		this.value = value;
	}

	public NoTaxReportException(String attribute, Integer value) {
		this.attribute = attribute;
		this.value = value.toString();
	}

	@Override
	public String toString() {
		return "No TaxReport with " + attribute + ": " + value;
	}
}
