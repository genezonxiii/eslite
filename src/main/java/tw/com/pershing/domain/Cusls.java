package tw.com.pershing.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "eslite_cusls")
public class Cusls {
	@Id
	@Column(name="sl_key")
	@JsonProperty("SL_KEY")
	private String slKey;
	
	@JsonProperty("C_NO")
	private String cNo;
	
	@JsonProperty("SL_AMT")
	private Float slAmt;
	
	@Column(name="sl_datetime")
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", timezone="GMT+8")
	@JsonProperty("SL_DATETIME")
	private Date slDatetime;
	
	@JsonProperty("SL_SOURCE")
	private String slSource;
	
	@OneToMany(targetEntity = CuslsDetail.class, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "sl_key", foreignKey = @ForeignKey(name = "FK_VERIFY_CUSLS"), insertable = false, updatable = false)
	@JsonProperty("details")
	private List<CuslsDetail> detail;
	
	@ElementCollection
	@CollectionTable(name = "eslite_cusls_payment", joinColumns = @JoinColumn(name = "sl_key"))
	@OrderColumn(name = "pay_order")
	@JsonProperty("payments")
	private String[] payments;
	
	@Override
    public String toString(){
        return getSlKey() + ", " + getcNo() + ", " + getSlAmt() + 
        		", " + getSlDatetime() + ", " + getSlSource() +
        		", " + Arrays.toString(getPayments());
    }

	public String getSlKey() {
		return slKey;
	}

	public void setSlKey(String slKey) {
		this.slKey = slKey;
	}

	public String getcNo() {
		return cNo;
	}

	public void setcNo(String cNo) {
		this.cNo = cNo;
	}

	public Float getSlAmt() {
		return slAmt;
	}

	public void setSlAmt(Float slAmt) {
		this.slAmt = slAmt;
	}

	public Date getSlDatetime() {
		return slDatetime;
	}

	public void setSlDatetime(Date slDatetime) {
		this.slDatetime = slDatetime;
	}

	public String getSlSource() {
		return slSource;
	}

	public void setSlSource(String slSource) {
		this.slSource = slSource;
	}

	public List<CuslsDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<CuslsDetail> detail) {
		this.detail = detail;
	}

	public String[] getPayments() {
		return payments;
	}

	public void setPayments(String[] payments) {
		this.payments = payments;
	}
}
