package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.repository.ProductRepository;
import com.ecommerce.library.service.ProductService;
import com.ecommerce.library.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Product update(MultipartFile imageProduct,ProductDto productDto) {
        try {
            Product product = productRepository.getById(productDto.getId());
            if (imageProduct==null){
                product.setImage(product.getImage());
            }else{
                if (imageUpload.checkExisted(imageProduct)==false){
                    imageUpload.uploadImage(imageProduct);
                }
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            product.setName(productDto.getName());
            product.setDescription(product.getDescription());
            product.setSalePrice(productDto.getSalePrice());
            product.setCostPrice(productDto.getCostPrice());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.setCategory(productDto.getCategory());
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void deleteById(Long id) {

        Product product = productRepository.getById(id);
        product.setIs_deleted(true);
        product.setIs_activated(false);
        productRepository.save(product);
    }

    @Override
    public void enableById(Long id) {

        Product product = productRepository.getById(id);
        product.setIs_activated(true);
        product.setIs_deleted(false);
        productRepository.save(product);
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.getById(id);
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCurrentQuantity(product.getCurrentQuantity());
        productDto.setCategory(product.getCategory());
        productDto.setSalePrice(product.getSalePrice());
        productDto.setCostPrice(product.getCostPrice());
        productDto.setImage(product.getImage());
        productDto.setActivated(product.isIs_activated());
        productDto.setDeleted(product.isIs_deleted());
        return productDto;
    }

    @Override
    public Page<Product> pageProduct(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo,5);
        Page<Product> productPages = productRepository.pageProduct(pageable);
        return productPages;
    }

    @Override
    public Page<Product> searchProducts(int pageNo,String keyword) {
        Pageable pageable = PageRequest.of(pageNo,5);
        Page<Product> products = productRepository.searchProducts(keyword,pageable);
        return products;
    }
}
