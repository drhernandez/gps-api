package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.Brands;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.BrandServiceImp;
import com.tesis.utils.JsonUtils;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.List;

public class BrandController {

    @Inject
    BrandServiceImp brandService;

    public Object createBrand(Request request, Response response) throws ApiException {
        Brands brand = JsonUtils.INSTANCE.GSON().fromJson(request.body(), Brands.class);
        ResponseDTO<Brands> responseDTO = brandService.createBrand(brand);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getBrands(Request request, Response response) throws ApiException {

        ResponseDTO<List<Brands>> responseDTO = brandService.getBrands();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getBrandByBrandID(Request request, Response response) throws ApiException{
        String param = request.params("brand_id");
        Long brandID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_brand_id] [method: BrandController.getDeciveByBrandID]");
        }

        try {
            brandID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_brand_id] [method: BrandController.getDeciveByBrandID]");
        }
        ResponseDTO<Brands> responseDTO = brandService.getBrandByBrandID(brandID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object updateBrand(Request request, Response response) throws ApiException{
        String param = request.params("brand_id");
        Long brandID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: BrandController.updateBrand]");
        }

        try {
            brandID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: BrandController.updateBrand]");
        }

        Brands newBrand = JsonUtils.INSTANCE.GSON().fromJson(request.body(), Brands.class);
        //Add validations

        ResponseDTO<Brands> responseDTO = brandService.updateBrand(brandID, newBrand);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteBrand(Request request, Response response) throws ApiException{
        String param = request.params("brand_id");
        Long brandID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: brandController.deleteBrand]");
        }
        try {
            brandID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: brandController.deleteBrand]");
        }
        ResponseDTO responseDTO = brandService.deleteBrand(brandID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }
}
