package tw.com.pershing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "eslite_customer")
public class Customer {
	@Id
	@Column(name="c_no")
	@JsonProperty("C_NO")
	private String cNo;
	
	@JsonProperty("C_COUNTRY")
	private String cCountry;
	
	@JsonProperty("C_SEX")
	private String cSex;
	
	@JsonProperty("C_ZIP")
	private String cZip;
	
	@JsonProperty("C_EMAIL")
	private String cEmail;

	@Override
    public String toString(){
        return getcNo() + ", " + getcCountry() + ", " + getcSex() + 
        		", " + getcZip() + ", " + getcEmail();
    }

	public String getcNo() {
		return cNo;
	}

	public void setcNo(String cNo) {
		this.cNo = cNo;
	}

	public String getcCountry() {
		return cCountry;
	}

	public void setcCountry(String cCountry) {
		this.cCountry = cCountry;
	}

	public String getcSex() {
		return cSex;
	}

	public void setcSex(String cSex) {
		this.cSex = cSex;
	}

	public String getcZip() {
		return cZip;
	}

	public void setcZip(String cZip) {
		this.cZip = cZip;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
}
