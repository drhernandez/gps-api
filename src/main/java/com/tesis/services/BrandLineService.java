package com.tesis.services;

import com.tesis.jooq.tables.pojos.BrandLines;
import com.tesis.models.ResponseDTO;

import java.util.List;

public interface BrandLineService {
    ResponseDTO<BrandLines> createBrandLine(BrandLines brandLine);
    ResponseDTO<List<BrandLines>> getBrandLines();
    ResponseDTO<BrandLines> getBrandLineByBrandLineID(Long brandLineID);
    ResponseDTO<List<BrandLines>> getBrandLineByBrandID(Long brandID);
    ResponseDTO<BrandLines> updateBrandLine(Long brandID, BrandLines newBrandLine);
    ResponseDTO<BrandLines> deleteBrandLine(Long brandLineID);

}
