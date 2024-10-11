package tdc.edu.vn.project_mobile_be.interfaces.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tdc.edu.vn.project_mobile_be.entities.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
    List<Product> findAll();

    Optional<Product> findById(UUID id);

    Page<Product> findAll(Pageable pageable);

    Product save(Product product);


    @Query("SELECT p FROM Product p JOIN FETCH p.categories c JOIN FETCH c.products WHERE c.id = :categoryId")
    Page<Product> findAllByCategory(@Param("categoryId") UUID category, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> findProductsPriceRange(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN FETCH  p.sizes s JOIN FETCH s.products WHERE s.id IN:sizeIds")
    Page<Product> findBySizes(@Param("sizeIds") List<UUID> sizeIds, Pageable pageable);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.images where p.id = :productId")
    Optional<Product> findByIdWithImages(@Param("productId") UUID productId);

    @Query("select p from Product p left join fetch  p.suplier where p.suplier.id = :suplierId")
    Page<Product> findByIdSuplier(@Param("suplierId") UUID suplierId, Pageable pageable);
}
