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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class is used for creating payment transaction for wallet operations.
 * transaction type -
	1- ADD-TO-WALLET
	2- PUT-ON-HOLD
	3- DEPOSIT-FROM-HOLD
	4- RELEASE-HOLD
	5- DEPOSIT-DIRECT
	6- REFUND-DEPOSIT
	7- RETRIEVE-FROM-WALLET 

  * transaction status
	1- ACTIVE
	2- SUCCESS
	3- FAILURE
	4- ON HOLD
 * @author Shubhanuj
 *
 */
@Entity
@Table(name = "payment_transaction")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class PaymentTransaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4536413437428240200L;
 
	@Id
	@Column(name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "amount")
	private BigDecimal amount;
	
	@NotBlank
	@Column(name = "currency")
	private String currency;
	
	@NotNull
	@Column(name = "status")
	private int transactionStatus;
	
	@NotNull
	@Column(name = "type")
	private int transactionType;
	
	@NotBlank
	@Column(name = "payment_system")
	private String paymentSystem;
	
	@Column(name = "source_student_id")
	private Long source;
	
	@Column(name = "destination_student_id")
	private Long destination;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the amount
	 */
	protected BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	protected void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the currency
	 */
	protected String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	protected void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the transactionStatus
	 */
	public int getTransactionStatus() {
		return transactionStatus;
	}

	/**
	 * @param transactionStatus the transactionStatus to set
	 */
	public void setTransactionStatus(int transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	/**
	 * @return the transactionType
	 */
	public int getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the paymentSystem
	 */
	public String getPaymentSystem() {
		return paymentSystem;
	}

	/**
	 * @param paymentSystem the paymentSystem to set
	 */
	public void setPaymentSystem(String paymentSystem) {
		this.paymentSystem = paymentSystem;
	}

	/**
	 * @return the source
	 */
	public Long getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Long source) {
		this.source = source;
	}

	/**
	 * @return the destination
	 */
	public Long getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Long destination) {
		this.destination = destination;
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
	 * @return the money
	 */
	public Money getMoney() {
		return new Money().setCurrency(getCurrency()).setValue(getAmount());
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(Money money) {
		setAmount(money.getValue());
		setCurrency(money.getCurrency());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentTransaction [id=" + id + ", amount=" + amount + ", currency=" + currency + ", transactionStatus="
				+ transactionStatus + ", transactionType=" + transactionType + ", paymentSystem=" + paymentSystem
				+ ", source=" + source + ", destination=" + destination + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

}
