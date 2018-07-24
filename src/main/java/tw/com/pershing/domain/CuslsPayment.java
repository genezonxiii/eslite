package tw.com.pershing.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "eslite_cusls_payment")
public class CuslsPayment {
	@Id
	@Column(name="cusls_payment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String cuslsPaymentId;
	
	@Column(name="sl_key")
	private String slKey;
	
	private String paymentName;

	@Column(name="create_at")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createAt;
	
	@Column(name="update_at")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date updateAt;
	
	@Override
    public String toString(){
        return getCuslsPaymentId() + ", " + getSlKey() + ", " + getPaymentName();
    }

	public String getCuslsPaymentId() {
		return cuslsPaymentId;
	}

	public void setCuslsPaymentId(String cuslsPaymentId) {
		this.cuslsPaymentId = cuslsPaymentId;
	}

	public String getSlKey() {
		return slKey;
	}

	public void setSlKey(String slKey) {
		this.slKey = slKey;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
}
