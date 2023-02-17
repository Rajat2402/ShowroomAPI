package com.example.RestClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.example.RestClient.model.BrandEntity;
import com.example.RestClient.model.Links;
import com.example.RestClient.model.ProductEntity;
import com.example.RestClient.service.BrandService;

@Path("/showroom")
public class Brands {

	BrandService service = new BrandService();

	@GET
	@Path("/brands")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BrandEntity> getBrand() {

		List<BrandEntity> list = service.getBrand();
		return list;
	}

	@GET
	@Path("/brands/{brandId}")
	@Produces(MediaType.APPLICATION_JSON)
	public BrandEntity getBrandwithId(@PathParam("brandId") int brandId, @Context UriInfo UriInfo) {

		
		Links link = new Links(UriInfo.getAbsolutePath().toString(), "self");
		Links products = new Links(UriInfo.getAbsolutePathBuilder().path("products").toString(), "products");
		BrandEntity brandEntity = service.getBrandwithId(brandId);
		List<Links> links = new ArrayList<Links>();
		

		links.add(link);
		links.add(products);
		brandEntity.setLinks(links);
		return brandEntity;
	}
	

	@GET
	@Path("/brands/{brandId}/products")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductEntity> getProductWithBrandId(@PathParam("brandId") int brandId,
			@QueryParam("category") String category, @QueryParam("start") int start, @QueryParam("end") int end) {

		if (category != null) {
			if (start > 1 && end > 2) {
				List<ProductEntity> productEntity = service.getProductWithBrandIdAndCategory(brandId, category);
				productEntity = productEntity.subList(start, end);
				return productEntity;
			} else {
				List<ProductEntity> productEntity = service.getProductWithBrandIdAndCategory(brandId , category);
				return productEntity;
			}

		} else {
			List<ProductEntity> productEntity = service.getProductWithBrandId(brandId);
			return productEntity;
		}
	}
	@POST
	@Path("/brands")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ceateBrand(BrandEntity entity, @Context UriInfo UriInfo) {
		service.createBrand(entity);
		URI uri = UriInfo.getAbsolutePathBuilder().path(Integer.toString(entity.getBrandId())).build();
		return Response.created(uri).entity(entity).build();

	}

	@PUT
	@Path("/brands/{brandId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateBrand(@PathParam("brandId") int brandId, BrandEntity brandEntity) {
		brandEntity.setBrandId(brandId);
		service.updateBrand(brandId, brandEntity);
	}

}
