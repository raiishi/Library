package tx.copp.oak.finance;

public class Testfinance {

	public static void main(String[] args) {

		
		
		Holding h1 = new Holding();
		Holding h2 = new Holding();
		Holding h3 = new Holding();
		Holding h4 = new Holding();
		Holding h5= new Holding();
		Holding h6 = new Holding();
		
		Company ibmc = new Company();
		ibmc.setName("International Business Machines");
		ibmc.setCountryCode("US");
		ibmc.setEmployeeAmount(288300);
		ibmc.setOutstandingShares(910000000);
		ibmc.setSector("TECHNOLOGY");
		Security ibm = new Security();
		ibm.setTicker("IBM");
		ibm.setCusip("459200101");
		ibm.setIsin("US4592001014");
		ibm.setPrice(291.53);
		ibm.setSector("TECHNOLOGY");
		ibm.setCompany(ibmc);

		Company applec = new Company();
		applec.setName("Apple Inc.");
		applec.setCountryCode("US");
		applec.setEmployeeAmount(161000);
		applec.setOutstandingShares(1550000000); // large number uses L
		applec.setSector("TECHNOLOGY");
		Security apple = new Security();
		apple.setTicker("AAPL");
		apple.setCusip("037833100");
		apple.setIsin("US0378331005");
		apple.setPrice(214.49);
		apple.setSector("TECHNOLOGY");
		apple.setCompany(applec);

		Company microsoftc = new Company();
		microsoftc.setName("Microsoft Corporation");
		microsoftc.setCountryCode("US");
		microsoftc.setEmployeeAmount(221000);
		microsoftc.setOutstandingShares(745000000);
		microsoftc.setSector("TECHNOLOGY");
		Security microsoft = new Security();
		microsoft.setTicker("MSFT");
		microsoft.setCusip("594918104");
		microsoft.setIsin("US5949181045");
		microsoft.setPrice(446.34);
		microsoft.setSector("TECHNOLOGY");
		microsoft.setCompany(microsoftc);

		Company teslac = new Company();
		teslac.setName("Tesla Inc.");
		teslac.setCountryCode("US");
		teslac.setEmployeeAmount(140473);
		teslac.setOutstandingShares(320000000);
		teslac.setSector("TECHNOLOGY");
		Security tesla = new Security();
		tesla.setTicker("TSLA");
		tesla.setCusip("88160R101");
		tesla.setIsin("US88160R1014");
		tesla.setPrice(216.90);
		tesla.setSector("TECHNOLOGY");
		tesla.setCompany(teslac);

		Company amazonc = new Company();
		amazonc.setName("Amazon.com Inc.");
		amazonc.setCountryCode("US");
		amazonc.setEmployeeAmount(1500000);
		amazonc.setOutstandingShares(1040000000);
		amazonc.setSector("TECHNOLOGY");
		Security amazon = new Security();
		amazon.setTicker("AMZN");
		amazon.setCusip("023135106");
		amazon.setIsin("US0231351067");
		amazon.setPrice(193.61);
		amazon.setSector("TECHNOLOGY");
		amazon.setCompany(amazonc);

		Company nvidiaa = new Company();
		nvidiaa.setName("NVIDIA Corporation");
		nvidiaa.setCountryCode("US");
		nvidiaa.setEmployeeAmount(29600);
		nvidiaa.setOutstandingShares(245000000);
		nvidiaa.setSector("TECHNOLOGY");
		Security nvidia = new Security();
		nvidia.setTicker("NVDA");
		nvidia.setCusip("67066G104");
		nvidia.setIsin("US67066G1040");
		nvidia.setPrice(126.56);
		nvidia.setSector("TECHNOLOGY");
		nvidia.setCompany(nvidiaa);

		
		h1.setStock(ibm);
		h1.setAmtofshares(500);
	//	System.out.println(h1);
		
		h2.setStock(apple);
		h2.setAmtofshares(238);
	//	System.out.println(h2);
		
		h3.setStock(microsoft);
		h3.setAmtofshares(691);
	//	System.out.println(h3);
		
		h4.setStock(tesla);
		h4.setAmtofshares(104);
	//	System.out.println(h4);
		
		h5.setStock(amazon);
		h5.setAmtofshares(35000);
		
		h6.setStock(nvidia);
		h6.setAmtofshares(104);
		
		Portfolio rishi = new Portfolio();
		rishi.setName("Skilled Gambling");
		rishi.setId("RAII -748291");
		rishi.addHolding(h1);
		rishi.addHolding(h2);
		rishi.addHolding(h3);
		rishi.addHolding(h4);
		rishi.addHolding(h5);
		rishi.addHolding(h6);
		
		System.out.println(rishi);
		
		System.out.println(rishi.totalmarketvalue());
		
		
	}

}
