package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.repository.ProductRepository;
import com.ecommerce.library.service.ProductService;
import com.ecommerce.library.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageUpload imageUpload;
    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product product:products){
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setCostPrice(product.getCostPrice());
            productDto.setSalePrice(product.getSalePrice());
            productDto.setCurrentQuantity(product.getCurrentQuantity());
            productDto.setCategory(product.getCategory());
            productDto.setImage(product.getImage());
            productDto.setActivated(product.isIs_activated());
            productDto.setDeleted(product.isIs_deleted());
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public Product save(MultipartFile imageproduct, ProductDto productDto) {
        Product product = new Product();
        try {
            if (imageproduct==null){
                product.setImage(null);
            }else {
                if (imageUpload.uploadImage(imageproduct)){
                    System.out.println("Upload successfully");
                }
                imageUpload.uploadImage(imageproduct);
                product.setImage(Base64.getEncoder().encodeToString(imageproduct.getBytes()));
            }
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setSalePrice(productDto.getSalePrice());
            product.setCostPrice(productDto.getCostPrice());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.setIs_activated(true);
            product.setIs_deleted(false);
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product update(ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void enableById(Long id) {

    }
}
