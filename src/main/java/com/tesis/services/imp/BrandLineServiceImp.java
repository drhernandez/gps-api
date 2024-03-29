package com.tesis.services.imp;

import com.google.inject.Inject;
import com.tesis.daos.BrandDaoExt;
import com.tesis.daos.BrandLineDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.exceptions.ApiException;
import com.tesis.jooq.tables.pojos.BrandLines;
import com.tesis.models.ResponseDTO;
import com.tesis.services.BrandLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BrandLineServiceImp implements BrandLineService {

    Logger logger = LoggerFactory.getLogger(BrandLineServiceImp.class);

    @Inject
    BrandLineDaoExt brandLineDao;

    @Override
    public ResponseDTO<BrandLines> createBrandLine(BrandLines brandLine) {
        ResponseDTO<BrandLines> responseDTO = new ResponseDTO<>();

        try {
            brandLineDao.insert(brandLine);
            responseDTO.model = brandLine;
        } catch (Exception e) {
            logger.error("[message: No se pudo guardar la brandLine {}] [error: {}]", brandLine.toString(), e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al guardar la brandLine.", e);
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<List<BrandLines>> getBrandLines() {
        return new ResponseDTO(brandLineDao.findAll(), null);
    }

    @Override
    public ResponseDTO<BrandLines> getBrandLineByBrandLineID(Long brandLineID) {
        return new ResponseDTO<BrandLines>(brandLineDao.fetchOneById(brandLineID), null);
    }

    @Override
    public ResponseDTO<List<BrandLines>> getBrandLineByBrandID(Long brandID) {
        ResponseDTO<List<BrandLines>> responseDTO = new ResponseDTO<>();

        try {
            responseDTO.setModel(brandLineDao.fetchByBrandId(brandID));
        }catch (Exception e){
            logger.error("[message: No se pudo encontrar las brandLines ] [error: {}]", e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al buscar las brandLines.", e);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<BrandLines> updateBrandLine(Long brandLineID, BrandLines newBrandLine) {
        ResponseDTO<BrandLines> responseDTO = new ResponseDTO<>();
        try {

            BrandLines brandLine = brandLineDao.fetchOneById(brandLineID);
            brandLine.setName(newBrandLine.getName());
            brandLine.setBrandId(newBrandLine.getBrandId());
            brandLineDao.update(brandLine);
            responseDTO.model = brandLine;
        } catch (Exception e){
            logger.error("[message: No se pudo modificar la brandLine] [error: {}]", e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al modificar la brandLine.", e);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<BrandLines> deleteBrandLine(Long brandLineID) {
        ResponseDTO<BrandLines> responseDTO = new ResponseDTO<>();
        try {
            brandLineDao.deleteById(brandLineID);
        }catch (Exception e) {
            logger.error("[message: No se pudo eliminar la brandLine {}] [error: {}]}", brandLineID, e.getMessage());
            responseDTO.error = new ApiException(ErrorCodes.internal_error.toString(), "Error al eliminar la brandLine.", e);
        }
        return responseDTO;
    }



}
