package tx.copp.oak.finance;

public class Company {

	private String name;
	private String sector;
	private int outstandingShares;
	private String countryCode;

	private int employeeAmount;
	private String companyType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public int getOutstandingShares() {
		return outstandingShares;
	}

	public void setOutstandingShares(int outstandingShares) {
		this.outstandingShares = outstandingShares;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getEmployeeAmount() {
		return employeeAmount;
	}

	public void setEmployeeAmount(int employeeAmount) {
		this.employeeAmount = employeeAmount;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Override
	public String toString() {
		return "Company [name = " + name + ", sector = " + sector + ", outstandingShares = " + outstandingShares
				+ ", countryCode = " + countryCode + ", employeeAmount = " + employeeAmount + ", companyType = " + companyType
				+ "]";
	}

}
