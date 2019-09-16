/**
 * 
 */
package com.shubhanuj.springboot.student.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Shubhanuj
 *
 */

@Entity
@Table(name = "wallet")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt","updatedAt"}, allowGetters = true)
public class Wallet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7184745458052521224L;

	@Id
	 @Column(name = "WALLET_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name="TOTAL_BALANCE")
	private BigDecimal balance;
	
	@NotNull
	@Column(name="onHold")
	private BigDecimal onHold;
	
	@NotNull
	@Column(name="available_Balance")
	private BigDecimal availableBalance;
	
	@NotNull
	@Column(name="Student_id", unique=true)
	private Long StudentId;
	
	@NotNull
	@Column(name="currency")
	private String currency;
	
	@NotNull
	@Column(name="status")
	private int status;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
	
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	/**
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * @return the onHold
	 */
	public BigDecimal getOnHold() {
		return onHold;
	}

	/**
	 * @param onHold the onHold to set
	 */
	public void setOnHold(BigDecimal onHold) {
		this.onHold = onHold;
	}

	/**
	 * @return the availableBalance
	 */
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	/**
	 * @param availableBalance the availableBalance to set
	 */
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Wallet [id=" + id + ", balance=" + balance + ", onHold=" + onHold + ", availableBalance="
				+ availableBalance + ", currency=" + currency + ", status=" + status + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

	/**
	 * @return the studentId
	 */
	public Long getStudentId() {
		return StudentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Long studentId) {
		StudentId = studentId;
	}

}
