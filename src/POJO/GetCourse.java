package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCourse {

	private String url;
	private String instructor;
	private String services;
	private String expertise;
	private coursessubjson courses;
	private String linkedIN;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public coursessubjson getCourses() {
		return courses;
	}
	public void setCourses(coursessubjson courses) {
		this.courses = courses;
	}
	public String getLinkedIN() {
		return linkedIN;
	}
	public void setLinkedIN(String linkedIN) {
		this.linkedIN = linkedIN;
	}
	
}
