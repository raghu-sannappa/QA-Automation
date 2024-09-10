package POJO;

import java.util.List;

public class coursessubjson {
	
	//courses is a Sub Json under Getcourse which consisits of 3 array of Json 
	// Inject this mini json to main json
	/* How to do it is while declaring variable type is String right there change it to 
	   class name here class is coursessubjson */ 

	private List<WebAutomation> webAutomation;
	private List<Api> api;
	private List<Mobile> mobile;
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<Api> getApi() {
		return api;
	}
	public void setApi(List<Api> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}
	
	

	

}
