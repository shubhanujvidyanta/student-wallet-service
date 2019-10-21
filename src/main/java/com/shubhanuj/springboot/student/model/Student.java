/**
 * 
 */
package com.shubhanuj.springboot.student.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shubhanuj.springboot.student.exception.EncryptionException;
import com.shubhanuj.springboot.student.utils.StudentUtils;

/**
 * @author Shubhanuj
 *
 */

@Entity
@Table(name = "student")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Student implements Serializable,Comparable<Student>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2047683999287882357L;

	@Id
	 @Column(name = "STUDENT_ID")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@NotBlank
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@NotBlank
	@Column(name="email", unique=true)
	private String email;
	
	@NotBlank
	@Column(name="password")
	@Access(AccessType.PROPERTY)
	private String password;
	
	@Transient
	private String encryptedPassword;
	
	@Transient
	private int passwordEncrypted=0;
	
	@NotBlank
	@Column(name="COUNTRY")
	private String country;
	
	@NotNull
	@Column(name="phoneNumber")
	private Long phoneNumber;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="WALLET_ID", referencedColumnName = "WALLET_ID")
	private Wallet wallet;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="STUDENT_ID", referencedColumnName = "STUDENT_ID")
	private List<Address> addresses;
	
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public Long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the wallet
	 */
	public Wallet getWallet() {
		return wallet;
	}

	/**
	 * @param wallet the wallet to set
	 */
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	/**
	 * @return the addresses
	 */
	public List<Address> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
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
	 * @return the password
	 */
	protected String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 * @throws EncryptionException 
	 */
	public void setPassword(String password) throws EncryptionException {
		this.password = password;
		setEncryptedPassword(StudentUtils.encryptPassword(this.password));
		setPasswordEncrypted(1);
		if(getEncryptedPassword() == null || getEncryptedPassword().isEmpty()) {
			setPasswordEncrypted(0);
			setEncryptedPassword(getPassword());
		}
	}

	/**
	 * @return the passwordEncrypted
	 */
	public boolean isPasswordEncrypted() {
		return passwordEncrypted==0?false:true;
	}

	/**
	 * @return the encryptedPassword
	 */
	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	/**
	 * @param encryptedPassword the encryptedPassword to set
	 */
	private void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	/**
	 * @param passwordEncrypted the passwordEncrypted to set
	 */
	private void setPasswordEncrypted(int passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", encryptedPassword=" + encryptedPassword + ", passwordEncrypted=" + passwordEncrypted + ", country="
				+ country + ", phoneNumber=" + phoneNumber + ", wallet=" + wallet + ", addresses=" + addresses
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	@Override
	public int compareTo(Student o) {
		if(this.getCreatedAt().before(o.getCreatedAt()))
			return 1;
		
		else if(this.getCreatedAt().after(o.getCreatedAt()))
			return -1;
		
		return 0;
	}
	

}
