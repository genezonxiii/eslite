package tw.com.pershing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "eslite_item")
public class Item {
	@Id
	@Column(name="matnr")
	@JsonProperty("MATNR")
	private String matnr;
	
	@JsonProperty("NEWVENDOR")
	private String newvender;
	
	@Column(name="group_1_name")
	@JsonProperty("GROUP_1_NAME")
	private String group1Name;
	
	@JsonProperty("MAKTX")
	private String maktx;
	
	@JsonProperty("ZZTHEC")
	private String zzthec;
	
	@JsonProperty("KBETR")
	private Float kbetr;

	@Override
    public String toString(){
        return getMatnr() + ", " + getNewvender() + ", " + getGroup1Name() + 
        		", " + getMaktx() + ", " + getZzthec() + ", " + getKbetr();
    }

	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getNewvender() {
		return newvender;
	}

	public void setNewvender(String newvender) {
		this.newvender = newvender;
	}

	public String getGroup1Name() {
		return group1Name;
	}

	public void setGroup1Name(String group1Name) {
		this.group1Name = group1Name;
	}

	public String getMaktx() {
		return maktx;
	}

	public void setMaktx(String maktx) {
		this.maktx = maktx;
	}

	public String getZzthec() {
		return zzthec;
	}

	public void setZzthec(String zzthec) {
		this.zzthec = zzthec;
	}

	public Float getKbetr() {
		return kbetr;
	}

	public void setKbetr(Float kbetr) {
		this.kbetr = kbetr;
	}
}
