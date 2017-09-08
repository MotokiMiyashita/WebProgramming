package model;
import java.io.Serializable;

public class SearchBeans implements Serializable{

	private String login_id;
	private String name;
	private String startBirth;
	private String endBirth;


	public SearchBeans() {

	}

	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartBirth() {
		return startBirth;
	}
	public void setStartBirth(String startBirth) {
		this.startBirth = startBirth;
	}
	public String getEndBirth() {
		return endBirth;
	}
	public void setEndBirth(String endBirth) {
		this.endBirth = endBirth;
	}

}
