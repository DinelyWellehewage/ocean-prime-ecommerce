package com.ecommerce.library.repository;

import com.ecommerce.library.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    /*Admin*/
    @Query("select p from Product p")
    Page<Product> pageProduct(Pageable pageable);

    @Query("select p from Product p where p.description like %?1% or p.name like %?1%")
    Page<Product> searchProducts(String keyword,Pageable pageable);

    @Query("select p from Product p where p.description like %?1% or p.name like %?1%")
    List<Product> searchProductsList(String keyword);

    /*Customer*/
    @Query("select p from Product p where p.is_activated = true and p.is_deleted=false")
    List<Product> getAllProducts();

    @Query(value = "select * from products p where p.is_deleted=false and p.is_activated=true order by rand() asc limit 4 ",nativeQuery = true)
    List<Product> listViewProducts();

    @Query(value = "SELECT p.product_id, p.name,p.cost_price,p.current_quantity,p.image,p.is_activated,p.is_deleted,p.sale_price, p.description, p.category_id as product_category_id, " +
            "c.category_id as category_id, c.name as category_name " +
            "FROM products p " +
            "INNER JOIN categories c ON c.category_id = p.category_id " +
            "WHERE p.category_id = ?1", nativeQuery = true)
    List<Product> getRelatedProducts(Long categoryId);

    @Query(value = "select p from Product p inner join Category c on c.id = p.category.id where c.id = ?1 and p.is_deleted = false and p.is_activated = true")
    List<Product> getProductsInCategory(Long categoryId);

    @Query("select p from Product p where p.is_activated = true and p.is_deleted = false order by p.costPrice desc")
    List<Product> filterHighPrice();

    @Query("select p from Product p where p.is_activated = true and p.is_deleted = false order by p.costPrice")
    List<Product> filterLowPrice();
}
