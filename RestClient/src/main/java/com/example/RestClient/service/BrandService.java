package com.example.RestClient.service;

import java.util.List;

import com.example.RestClient.model.BrandEntity;
import com.example.RestClient.model.ProductEntity;
import com.example.RestClient.service.DAO.BrandsDAO;

public class BrandService {

	BrandsDAO DAO=new BrandsDAO();
	public List<BrandEntity> getBrand() {
		
		return DAO.getBrand();
		
	}
	public void createBrand(BrandEntity entity) {
		
		DAO.createBrand(entity);
	}
	public void updateBrand(int brandId, BrandEntity brandEntity) {
		DAO.updateBrand(brandId , brandEntity);
	}
	public BrandEntity getBrandwithId(int brandId) {
		BrandEntity brandEntity = DAO.getBrandwithId(brandId);
		return brandEntity;
	}
	public List<ProductEntity> getProductWithBrandId(int brandId) {
		List<ProductEntity> productEntity=DAO.getProductWithBrandId(brandId);
		return productEntity;
	}
	public List<ProductEntity> getProductWithBrandIdAndCategory(int brandId, String category) {
		List<ProductEntity> productEntity=DAO.getProductWithBrandIdAndCategory(brandId , category);
		return productEntity;
	}
}
