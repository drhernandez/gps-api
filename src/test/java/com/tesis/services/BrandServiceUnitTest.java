package com.tesis.services;

import com.tesis.configs.UnitTestConfigs;
import com.tesis.daos.BrandDaoExt;
import com.tesis.enums.ErrorCodes;
import com.tesis.jooq.tables.pojos.Brands;
import com.tesis.models.ResponseDTO;
import com.tesis.services.imp.BrandServiceImp;
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
public class BrandServiceUnitTest extends UnitTestConfigs {

    @Mock
    BrandDaoExt brandDao;

    @InjectMocks
    BrandServiceImp brandService;

    @Test
    public void createBrandTest_ok(){
        Brands brand = new Brands(1L, "brandName");
        ResponseDTO<Brands> responseDTO = brandService.createBrand(brand);
        assertEquals(responseDTO.getModel().getId(), brand.getId());
        assertEquals(responseDTO.getModel().getName(), brand.getName());
    }

    @Test
    public void createBrandTest_error(){
        Brands brand = new Brands(1L, "brandName");
        Mockito.doThrow(DataAccessException.class).when(brandDao).insert(any(Brands.class));

        ResponseDTO<Brands> responseDTO = brandService.createBrand(brand);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al guardar la brand.");
    }

    @Test
    public void getBrandsTest_ok(){
        List<Brands> brandsList = new ArrayList<>();
        brandsList.add(Mockito.mock(Brands.class));
        brandsList.add(Mockito.mock(Brands.class));

        Mockito.when(brandDao.findAll()).thenReturn(brandsList);
        ResponseDTO<List<Brands>> responseDTO = brandService.getBrands();

        assertEquals(responseDTO.getModel().size(), brandsList.size());
    }

    @Test
    public void getBrandsTest_error(){
        List<Brands> brandsList = new ArrayList<>();
        Mockito.when(brandDao.findAll()).thenReturn(brandsList);

        ResponseDTO<List<Brands>> responseDTO = brandService.getBrands();
        assertEquals(responseDTO.getModel().size(), brandsList.size());
    }

    @Test
    public void getBrandByBrandIDTest_ok(){
        Brands brand = new Brands(1L, "brandName");
        Mockito.when(brandDao.fetchOneById(any(Long.class))).thenReturn(brand);

        ResponseDTO<Brands> responseDTO = brandService.getBrandByBrandID(brand.getId());
        assertEquals(responseDTO.getModel().getId(), brand.getId());
        assertEquals(responseDTO.getModel().getName(), brand.getName());
    }

    @Test
    public void getBrandByBrandIDTest_error(){
        Mockito.when(brandDao.fetchOneById(any(Long.class))).thenReturn(null);
        ResponseDTO<Brands> responseDTO = brandService.getBrandByBrandID(1L);
        assertNull(responseDTO.getModel());
    }

    @Test
    public void updateBrandTest_ok(){
        Brands brand = new Brands(1L, "brandName");
        Brands newBrand = new Brands(1L, "brandName2");

        Mockito.when(brandDao.fetchOneById(any(Long.class))).thenReturn(brand);

        ResponseDTO<Brands> responseDTO = brandService.updateBrand(1L, newBrand);
        assertEquals(responseDTO.getModel().getId(), newBrand.getId());
        assertEquals(responseDTO.getModel().getName(), newBrand.getName());
    }

    @Test
    public void updateBrandTest_error(){
        ResponseDTO<Brands> responseDTO = brandService.updateBrand(1L, Mockito.mock(Brands.class));

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al modificar la brand.");
    }

    @Test
    public void deleteBrandTest_ok(){
        ResponseDTO responseDTO = brandService.deleteBrand(1L);
        Mockito.verify(brandDao, times(1)).deleteById(any(Long.class));
        assertNull(responseDTO.getModel());
    }
    @Test
    public void deleteBrandTest_error(){
        Mockito.doThrow(DataAccessException.class).when(brandDao).deleteById(any(Long.class));
        ResponseDTO responseDTO = brandService.deleteBrand(1L);

        assertEquals(responseDTO.getError().getError(), ErrorCodes.internal_error.name());
        assertEquals(responseDTO.getError().getMessage(), "Error al eliminar la brand.");
    }
}
