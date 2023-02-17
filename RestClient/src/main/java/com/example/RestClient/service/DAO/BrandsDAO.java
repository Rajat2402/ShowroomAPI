package com.example.RestClient.service.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.RestClient.model.BrandEntity;
import com.example.RestClient.model.ProductEntity;

public class BrandsDAO {

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(BrandEntity.class)
			.addAnnotatedClass(ProductEntity.class).buildSessionFactory();
	Session session = null;

	public List<BrandEntity> getBrand() {

		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<BrandEntity> list = session.createQuery("from brands").getResultList();
			return list;
		} finally {
			session.close();
		}
	}

	public void createBrand(BrandEntity entity) {
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	public void updateBrand(int brandId, BrandEntity brandEntity) {
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			int id = brandEntity.getBrandId();
			BrandEntity brand = session.get(BrandEntity.class, id);
			brand.setBrandName(brandEntity.getBrandName());
			session.getTransaction().commit();
		} finally {
			session.close();
		}

	}

	public BrandEntity getBrandwithId(int brandId) {
		BrandEntity brandEntity;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			brandEntity = session.get(BrandEntity.class, brandId);
		} finally {
			session.close();
		}
		return brandEntity;
	}

	public List<ProductEntity> getProductWithBrandId(int brandId) {
		List<ProductEntity> productList;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			String HQL="from products where brandId= '"+brandId+"' ";
			productList= session.createQuery(HQL).getResultList();
		} finally {
			session.close();
		}
		return productList;
	}

	public List<ProductEntity> getProductWithBrandIdAndCategory(int brandId, String category) {
		List<ProductEntity> productList;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			String HQL="from products where brandId= '"+brandId+"' and category= '"+category+"' ";
			productList= session.createQuery(HQL).getResultList();
		} finally {
			session.close();
		}
		return productList;
	}

}
