package com.etiya.ecommercedemopair6.business.concretes;

import com.etiya.ecommercedemopair6.business.abstracts.BrandService;
import com.etiya.ecommercedemopair6.business.constants.Message;
import com.etiya.ecommercedemopair6.business.dto.request.concretes.brand.CreateBrandRequest;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.brand.CreateBrandResponse;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.brand.GetAllBrandResponse;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.brand.GetBrandResponse;
import com.etiya.ecommercedemopair6.core.util.exceptions.BusinessException;
import com.etiya.ecommercedemopair6.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemopair6.core.util.result.DataResult;
import com.etiya.ecommercedemopair6.core.util.result.Result;
import com.etiya.ecommercedemopair6.core.util.result.SuccessDataResult;
import com.etiya.ecommercedemopair6.core.util.result.SuccessResult;
import com.etiya.ecommercedemopair6.entities.concretes.Brand;
import com.etiya.ecommercedemopair6.repository.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<GetBrandResponse> getById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse response = modelMapperService.forResponse().map(brand, GetBrandResponse.class);
        return new SuccessDataResult<GetBrandResponse>(response,Message.Brand.getByBrand);

        //return brandRepository.findById(id).orElseThrow();
    }

    @Override
    public DataResult<List<GetAllBrandResponse>> getAllBrand() {
        List<Brand> brands=brandRepository.findAll();
        List<GetAllBrandResponse> responses= brands.stream().
                map(brand -> modelMapperService.forResponse().map(brand,GetAllBrandResponse.class))
                .collect(Collectors.toList());
                return new SuccessDataResult<List<GetAllBrandResponse>>(responses,Message.Brand.getAllBrands);

        //return brandRepository.findAll();
    }

    @Override
    public Result createBrand(CreateBrandRequest createBrandRequest) {
        checkIfExistsBrandName(createBrandRequest.getName());
        //***********************************ManuelMapper******************************************

        //Brand brand=new Brand();
        // brand.setName(createBrandRequest.getName());
        // Brand savedBrand=brandRepository.save(brand);
        // CreateBrandResponse response=new CreateBrandResponse(savedBrand.getName());
        //return response;

        Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        Brand savedBrand = brandRepository.save(brand);
        CreateBrandResponse response = modelMapperService.forResponse().map(savedBrand, CreateBrandResponse.class);
        return new SuccessResult(Message.Brand.createBrand);
    }

    public void checkIfExistsBrandName(String name){
        boolean isExists = brandRepository.existsByName(name);
        if (isExists){
                throw new BusinessException(Message.Exception.runTimeException);
        }
    }
}
