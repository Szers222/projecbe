package tdc.edu.vn.project_mobile_be.entities.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import tdc.edu.vn.project_mobile_be.entities.category.Category;
import tdc.edu.vn.project_mobile_be.entities.coupon.Coupon;
import tdc.edu.vn.project_mobile_be.entities.post.Post;
import tdc.edu.vn.project_mobile_be.entities.shipment.Shipment;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@Data
@Table(name = "products")
@Entity
@DynamicInsert
@DynamicUpdate
public class Product {
    @Id
    @Column(name = "product_id", nullable = false,columnDefinition = "BINARY(16)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "product_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "product_price", nullable = false)
    private double price;

    @Column(name = "product_quantity", nullable = false, columnDefinition = "int default 0")
    private int quantity;

    @Column(name = "product_views", columnDefinition = "int default 0")
    private int views;

    @Column(name = "product_rating", columnDefinition = "double default 0")
    private double rating;

    @Column(name = "product_year_of_manufacture", columnDefinition = "int default 2000")
    private int year_of_manufacture;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_suplier_id", nullable = false)
    private ProductSuplier suplier;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "categories_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShipmentProduct> shipmentProducts;

}
