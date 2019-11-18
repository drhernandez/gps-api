package com.tesis.controllers;

import com.google.inject.Inject;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.BrandLines;
import com.tesis.jooq.tables.pojos.Brands;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.BrandLineServiceImp;
import com.tesis.services.imp.BrandServiceImp;
import com.tesis.utils.JsonUtils;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.List;

public class BrandController {

    @Inject
    BrandServiceImp brandService;

    @Inject
    BrandLineServiceImp brandLineService;


    //   ---------------- Brand methods ----------------


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


//   ---------------- Brand Lines methods ----------------


    public Object createBrandLine(Request request, Response response) throws ApiException {
        BrandLines brandLine = JsonUtils.INSTANCE.GSON().fromJson(request.body(), BrandLines.class);
        ResponseDTO<BrandLines> responseDTO = brandLineService.createBrandLine(brandLine);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getBrandLines(Request request, Response response) throws ApiException {

        ResponseDTO<List<BrandLines>> responseDTO = brandLineService.getBrandLines();

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object getBrandLineByBrandLineID(Request request, Response response) throws ApiException{
        String param = request.params("brand_line_id");
        Long brandLineID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_brand_line_id] [method: BrandController.getBrandLineByBrandLineID]");
        }

        try {
            brandLineID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_brand_line_id] [method: BrandController.getBrandLineByBrandLineID]");
        }
        ResponseDTO<BrandLines> responseDTO = brandLineService.getBrandLineByBrandLineID(brandLineID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object updateBrandLine(Request request, Response response) throws ApiException{
        String param = request.params("brand_line_id");
        Long brandLineID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: BrandController.updateBrandLine]");
        }

        try {
            brandLineID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: BrandController.updateBrandLine]");
        }

        BrandLines newBrand = JsonUtils.INSTANCE.GSON().fromJson(request.body(), BrandLines.class);
        //Add validations

        ResponseDTO<BrandLines> responseDTO = brandLineService.updateBrandLine(brandLineID, newBrand);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }

    public Object deleteBrandLine(Request request, Response response) throws ApiException{
        String param = request.params("brand_line_id");
        Long brandLineID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: brandController.deleteBrandLine]");
        }
        try {
            brandLineID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_device_id] [method: brandController.deleteBrandLine]");
        }
        ResponseDTO responseDTO = brandLineService.deleteBrandLine(brandLineID);
        response.status(200);

        if (responseDTO.error != null) {
            response.status(500);
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }


    public Object getBrandLineByBrandID(Request request, Response response) throws ApiException{
        String param = request.params("brand_id");
        Long brandID;
        if (StringUtils.isBlank(param)) {
            throw new ApiException("invalid_data", "[reason: invalid_brand_id] [method: BrandController.getBrandLineByBrandID]");
        }

        try {
            brandID = Long.valueOf(param);
        } catch (NumberFormatException e) {
            throw new ApiException("invalid_data", "[reason: invalid_brand_id] [method: BrandController.getBrandLineByBrandID]");
        }
        ResponseDTO<List<BrandLines>> responseDTO = brandLineService.getBrandLineByBrandID(brandID);

        if (responseDTO.error != null) {
            throw responseDTO.error;
        }

        return responseDTO.getModelAsJson();
    }


}
