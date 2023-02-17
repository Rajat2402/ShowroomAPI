package com.example.RestClient.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@javax.persistence.Entity(name="brands")
@Table(name="brands")
public class BrandEntity {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="brandId")
	private int brandId;
	
	@Column(name="brandName")
	private String brandName;
	
	@Transient
	private List<Links> links;
	
	public BrandEntity() {
	}
	public BrandEntity(int brandId, String brandName) {
		this.brandId = brandId;
		this.brandName = brandName;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public List<Links> getLinks() {
		return links;
	}
	public void setLinks(List<Links> links) {
		this.links = links;
	}
	
}