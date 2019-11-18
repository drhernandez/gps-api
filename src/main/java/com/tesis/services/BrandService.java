package com.tesis.services;

import com.tesis.jooq.tables.pojos.Brands;
import com.tesis.models.ResponseDTO;

import java.util.List;

public interface BrandService {
    ResponseDTO<Brands> createBrand(Brands brand);
    ResponseDTO<List<Brands>> getBrands();
    ResponseDTO<Brands> getBrandByBrandID(Long brandID);
    ResponseDTO<Brands> updateBrand(Long brandID, Brands newBrand);
    ResponseDTO<Brands> deleteBrand(Long brandID);
}
