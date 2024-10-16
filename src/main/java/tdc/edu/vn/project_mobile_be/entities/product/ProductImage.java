package tdc.edu.vn.project_mobile_be.entities.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "product_images")
@Data
@DynamicUpdate
@DynamicInsert
//@EqualsAndHashCode(exclude = "product")
public class ProductImage {

    @Id
    @Column(name = "product_image_id", nullable = false, columnDefinition = "BINARY(16)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productImageId;


    @Column(name = "product_image_path", nullable = false)
    private String productImagePath;
    @Column(name = "product_image_alt", nullable = false)
    private String productImageAlt;

    @Column(name = "product_image_index")
    private int productImageIndex;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "product_id", nullable = false, columnDefinition = "BINARY(16)")
    private Product product;

}
