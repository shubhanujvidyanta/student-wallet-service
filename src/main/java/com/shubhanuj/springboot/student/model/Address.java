/**
 * 
 */
package com.shubhanuj.springboot.student.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
 * @author Shubhanuj
 *
 */

@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4650517942226615703L;

	@Id
	 @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "IS_PRIMARY")
	private String isDefault;
	
	@NotNull
	@Column(name = "STUDENT_ID")
	private Long studentId;
	
	@NotBlank
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@NotBlank
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@NotBlank
	@Column(name = "ADDRESS_1")
	private String address1;
	
	@Column(name = "ADDRESS_2")
	private String address2;
	
	@Column(name = "ADDRESS_3")
	private String address3;
	
	@NotNull
	@Column(name = "PHONE_1")
	private Long phone1;
	
	@Column(name = "PHONE_2")
	private Long phone2;
	
	@NotNull
	@Column(name = "PINCODE")
	private Long pincode;
	
	@Column(name = "AREA")
	private String area;
	
	@NotBlank
	@Column(name = "CITY")
	private String city;
	
	@NotBlank
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "NICKNAME")
	@Access(AccessType.PROPERTY)
	private String nickName;
	
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
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * @param address3 the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	/**
	 * @return the phone1
	 */
	public Long getPhone1() {
		return phone1;
	}

	/**
	 * @param phone1 the phone1 to set
	 */
	public void setPhone1(Long phone1) {
		this.phone1 = phone1;
	}

	/**
	 * @return the phone2
	 */
	public Long getPhone2() {
		return phone2;
	}

	/**
	 * @param phone2 the phone2 to set
	 */
	public void setPhone2(Long phone2) {
		this.phone2 = phone2;
	}

	/**
	 * @return the pincode
	 */
	public Long getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		if(nickName == null || nickName.isEmpty()) {
			setNickName();
		}
		else {
			this.nickName = nickName;
		}
	}
	
	/**
	 * the nickName to set
	 */
	public void setNickName() {
		this.nickName = getId().toString()+"-"+getFirstName();
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
	 * @return the isDefault
	 */
	public @NotBlank String getIsDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault( @NotBlank String isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * @return the studentId
	 */
	public Long getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Address [id=" + id + ", isDefault=" + isDefault + ", studentId=" + studentId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", address1=" + address1 + ", address2=" + address2
				+ ", address3=" + address3 + ", phone1=" + phone1 + ", phone2=" + phone2 + ", pincode=" + pincode
				+ ", area=" + area + ", city=" + city + ", country=" + country + ", nickName=" + nickName
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
