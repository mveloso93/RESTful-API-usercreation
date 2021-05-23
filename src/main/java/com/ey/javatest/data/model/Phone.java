package com.ey.javatest.data.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "phone")
public class Phone implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                @Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                )
            }
        )
    @Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@Column(name = "number",  length = 15)
	private String number;
	
	@Column(name = "citycode",  length = 25)
	private String citycode;
	
	@Column(name = "countrycode",  length = 25)
	private String countrycode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User userPhone;

	public Phone(String number, String citycode, String countrycode, User userPhone) {
		super();

		this.number = number;
		this.citycode = citycode;
		this.countrycode = countrycode;
		this.userPhone = userPhone;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public User getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(User userPhone) {
		this.userPhone = userPhone;
	}


	

	
	
	
	

}
