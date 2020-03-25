package com.tesis.services.imp;

import com.google.inject.Inject;
import com.tesis.daos.BrandDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Brands;
import com.tesis.models.ResponseDTO;
import com.tesis.services.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BrandServiceImp implements BrandService {

    Logger logger = LoggerFactory.getLogger(BrandServiceImp.class);

    @Inject
    BrandDaoExt brandDao;

    @Override
    public ResponseDTO<Brands> createBrand(Brands brand) {
        ResponseDTO<Brands> responseDTO = new ResponseDTO<>();

        try {
            brandDao.insert(brand);
            responseDTO.model = brand;
        } catch (Exception e) {
            logger.error("[message: No se pudo guardar la brand {}] [error: {}]", brand.toString(), e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar la brand.", e);
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<List<Brands>> getBrands() {
        return new ResponseDTO(brandDao.findAll(), null);
    }

    @Override
    public ResponseDTO<Brands> getBrandByBrandID(Long brandID) {
        return new ResponseDTO<Brands>(brandDao.fetchOneById(brandID), null);
    }

    @Override
    public ResponseDTO<Brands> updateBrand(Long brandID, Brands newBrand) {
        ResponseDTO<Brands> responseDTO = new ResponseDTO<>();
        try {
            Brands brand = brandDao.fetchOneById(brandID);
            brand.setName(newBrand.getName());
            brandDao.update(brand);
            responseDTO.model = brand;
        } catch (Exception e){
            logger.error("[message: No se pudo modificar la brand] [error: {}]", e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar la brand.", e);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Brands> deleteBrand(Long brandID) {
        ResponseDTO<Brands> responseDTO = new ResponseDTO<>();
        try {
            brandDao.deleteById(brandID);
        }catch (Exception e) {
            logger.error("[message: No se pudo eliminar la brand {}] [error: {}]", brandID, e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar la brand.", e);
        }
        return responseDTO;
    }
}
