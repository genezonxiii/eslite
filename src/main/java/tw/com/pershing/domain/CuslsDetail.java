package tw.com.pershing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "eslite_cusls_detail")
public class CuslsDetail {
	@Id
	@Column(name="cusls_detail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String cuslsDetailId;
	
	@Column(name="sl_key")
	private String slKey;
	
	@JsonProperty("P_NO")
	private String pNo;
	
	@JsonProperty("SL_QTY")
	private Integer slQty;
	
	@JsonProperty("SL_AMT")
	private Float slAmt;

	@Override
    public String toString(){
        return getCuslsDetailId() + ", " + getSlKey() + ", " + getpNo() + 
        		", " + getSlQty() + ", " + getSlAmt();
    }

	public String getCuslsDetailId() {
		return cuslsDetailId;
	}

	public void setCuslsDetailId(String cuslsDetailId) {
		this.cuslsDetailId = cuslsDetailId;
	}

	public String getSlKey() {
		return slKey;
	}

	public void setSlKey(String slKey) {
		this.slKey = slKey;
	}

	public String getpNo() {
		return pNo;
	}

	public void setpNo(String pNo) {
		this.pNo = pNo;
	}

	public Integer getSlQty() {
		return slQty;
	}

	public void setSlQty(Integer slQty) {
		this.slQty = slQty;
	}

	public Float getSlAmt() {
		return slAmt;
	}

	public void setSlAmt(Float slAmt) {
		this.slAmt = slAmt;
	}
}
