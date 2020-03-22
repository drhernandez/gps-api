package com.tesis.services;

import com.tesis.configs.UnitTestConfigs;
import com.tesis.daos.BrandLineDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.jooq.tables.pojos.BrandLines;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.BrandLineServiceImp;
import org.jooq.exception.DataAccessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class BrandLineServiceUnitTest {

    @Mock
    BrandLineDaoExt brandLineDao;

    @InjectMocks
    BrandLineServiceImp brandLineService;

    @Test
    public void createBrandLineTest_ok(){
        BrandLines brand = new BrandLines(1L, "brandName", 1L);
        ResponseDTO<BrandLines> responseDTO = brandLineService.createBrandLine(brand);
        assertEquals(responseDTO.getModel().getId(), brand.getId());
        assertEquals(responseDTO.getModel().getName(), brand.getName());
        assertEquals(responseDTO.getModel().getBrandId(), brand.getBrandId());
    }

    @Test
    public void createBrandLineTest_error(){

        BrandLines brand = new BrandLines(1L, "brandName", 1L);
        Mockito.doThrow(DataAccessException.class).when(brandLineDao).insert(any(BrandLines.class));

        ResponseDTO<BrandLines> responseDTO = brandLineService.createBrandLine(brand);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al guardar la brandLine.");
    }

    @Test
    public void getBrandLinesTest_ok(){
        List<BrandLines> brandLineList = new ArrayList<>();
        brandLineList.add(Mockito.mock(BrandLines.class));
        brandLineList.add(Mockito.mock(BrandLines.class));

        Mockito.when(brandLineDao.findAll()).thenReturn(brandLineList);
        ResponseDTO<List<BrandLines>> responseDTO = brandLineService.getBrandLines();

        assertEquals(responseDTO.getModel().size(), brandLineList.size());
    }

    @Test
    public void getBrandLinesTest_error(){
        List<BrandLines> brandLineList = new ArrayList<>();
        Mockito.when(brandLineDao.findAll()).thenReturn(brandLineList);

        ResponseDTO<List<BrandLines>> responseDTO = brandLineService.getBrandLines();
        assertEquals(responseDTO.getModel().size(), brandLineList.size());
    }

    @Test
    public void getBrandLineByBrandLineID_ok(){
        BrandLines brand = new BrandLines(1L, "brandName", 1L);
        Mockito.when(brandLineDao.fetchOneById(any(Long.class))).thenReturn(brand);

        ResponseDTO<BrandLines> responseDTO = brandLineService.getBrandLineByBrandLineID(brand.getId());
        assertEquals(responseDTO.getModel().getId(), brand.getId());
        assertEquals(responseDTO.getModel().getName(), brand.getName());
        assertEquals(responseDTO.getModel().getBrandId(), brand.getBrandId());
    }

    @Test
    public void getBrandLineByBrandLineID_error(){
        Mockito.when(brandLineDao.fetchOneById(any(Long.class))).thenReturn(null);
        ResponseDTO<BrandLines> responseDTO = brandLineService.getBrandLineByBrandLineID(1L);
        assertNull(responseDTO.getModel());
    }

    @Test
    public void getBrandLineByBrandIDTest_ok(){
        List<BrandLines> brandLineList = new ArrayList<>();
        brandLineList.add(Mockito.mock(BrandLines.class));
        brandLineList.add(Mockito.mock(BrandLines.class));

        Mockito.when(brandLineDao.fetchByBrandId(any(Long.class))).thenReturn(brandLineList);
        ResponseDTO<List<BrandLines>> responseDTO = brandLineService.getBrandLineByBrandID(1L);

        assertEquals(responseDTO.getModel().size(), brandLineList.size());
    }

    @Test
    public void getBrandLineByBrandIDTest_error(){
        Mockito.when(brandLineDao.fetchByBrandId(any(Long.class))).thenThrow(DataAccessException.class);

        ResponseDTO<List<BrandLines>> responseDTO = brandLineService.getBrandLineByBrandID(1L);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al buscar las brandLines.");
    }

    @Test
    public void updateBrandLineTest_ok(){
        BrandLines brandLine = new BrandLines(1L, "brandLineName", 1L);
        BrandLines newBrandLine = new BrandLines(1L, "brandLineName2", 1L);

        Mockito.when(brandLineDao.fetchOneById(any(Long.class))).thenReturn(brandLine);

        ResponseDTO<BrandLines> responseDTO = brandLineService.updateBrandLine(1L, newBrandLine);
        assertEquals(responseDTO.getModel().getId(), newBrandLine.getId());
        assertEquals(responseDTO.getModel().getName(), newBrandLine.getName());
    }

    @Test
    public void updateBrandLineTest_error(){
        ResponseDTO<BrandLines> responseDTO = brandLineService.updateBrandLine(1L, Mockito.mock(BrandLines.class));

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al modificar la brandLine.");
    }

    @Test
    public void deleteBrandLineTest_ok(){
        ResponseDTO responseDTO = brandLineService.deleteBrandLine(1L);
        Mockito.verify(brandLineDao, times(1)).deleteById(any(Long.class));
        assertNull(responseDTO.getModel());
    }

    @Test
    public void deleteBrandLineTest_error(){
        Mockito.doThrow(DataAccessException.class).when(brandLineDao).deleteById(any(Long.class));
        ResponseDTO responseDTO = brandLineService.deleteBrandLine(1L);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al eliminar la brandLine.");
    }
}
