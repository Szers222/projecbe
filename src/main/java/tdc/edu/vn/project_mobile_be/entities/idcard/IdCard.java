package tdc.edu.vn.project_mobile_be.entities.idcard;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import tdc.edu.vn.project_mobile_be.entities.user.User;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "idCard")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class IdCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "card_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID cardId;

    @Column(name = "card_number", length = 15, nullable = false)
    private String idCardNumber;

    @Column(name = "card_image_front_path", length = 255)
    private String imageFrontPath;

    @Column(name = "card_back_path", length = 255)
    private String imageBackPath;

    @Column(name = "card_date", length = 10)
    private String idCardDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;

    @OneToOne(mappedBy = "iCard")
    private User user;
}
