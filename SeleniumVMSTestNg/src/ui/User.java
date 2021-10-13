package ui;

public class User {
	private String id;
	private String fullname;
	private String email;
	private String password;
	private String phone;
	private String dob;
	private String fCentre;
	private String fTime;
	private String fDate;
	private String sCentre;
	private String sTime;
	private String sDate;
	private Boolean fDose;
	private Boolean sDose;
	
	public User(String id, String fullname, String email, String password, String dob, String phone, String fCentre,
			String fTime, String fDate, String sCentre, String sTime, String sDate, Boolean fDose, Boolean sDose) {
		this.id=id.toUpperCase();
		this.fullname=fullname;
		this.email=email;
		this.password=password;
		this.dob=dob;
		this.phone=phone;
		this.fCentre=fCentre;
		this.fDate=fDate;
		this.fTime=fTime;
		this.sCentre=sCentre;
		this.sDate=sDate;
		this.sTime=sTime;
		this.fDose=fDose;
		this.sDose=sDose;
	}
	
	public User(String id, String fullname, String email, String password, String dob, String phone) {
		this.id=id;
		this.fullname=fullname;
		this.email=email;
		this.password=password;
		this.dob=dob;
		this.phone=phone;
		this.fDose=false;
		this.sDose=false;
	}
	
	public User(String id, String password,String email) {
		this.id=id;
		this.password=password;
		this.email=email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getfCentre() {
		return fCentre;
	}

	public void setfCentre(String fCentre) {
		this.fCentre = fCentre;
	}

	public String getfTime() {
		return fTime;
	}

	public void setfTime(String fTime) {
		this.fTime = fTime;
	}

	public String getfDate() {
		return fDate;
	}

	public void setfDate(String fDate) {
		this.fDate = fDate;
	}

	public String getsCentre() {
		return sCentre;
	}

	public void setsCentre(String sCentre) {
		this.sCentre = sCentre;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public Boolean getfDose() {
		return fDose;
	}

	public void setfDose(Boolean fDose) {
		this.fDose = fDose;
	}

	public Boolean getsDose() {
		return sDose;
	}

	public void setsDose(Boolean sDose) {
		this.sDose = sDose;
	}
	
	
}
