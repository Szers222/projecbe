package tdc.edu.vn.project_mobile_be.dtos.responses;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import tdc.edu.vn.project_mobile_be.commond.customexception.UnsupportedOperationException;
import tdc.edu.vn.project_mobile_be.entities.category.Category;
import tdc.edu.vn.project_mobile_be.interfaces.IDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO implements IDto<Category> {
    @JsonProperty("categoryId")
    private UUID categoryId;
    @JsonProperty("categoryName")
    private String categoryName;

    @JsonProperty("categoryRelease")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Timestamp categoryRelease;

    @JsonProperty("categoryParent")
    private UUID parentId = null;
    @JsonProperty("categoryLevel")
    private int level;
    @JsonProperty("categoryStatus")
    private CategoryStatusResponseDTO categoryStatus;

    @JsonProperty("categoryChildren")
    private List<CategoryResponseDTO> children;

    @JsonProperty("categoryImgPath")
    private String categoryImgPath;

    @JsonIgnore
    private Timestamp createdAt;

    @JsonIgnore
    private Timestamp updatedAt;


    @Override
    public Category toEntity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void toDto(Category entity) {
        BeanUtils.copyProperties(entity, this, "createdAt", "updatedAt");

        if (entity.getCategoryStatus() != null) {
            this.categoryStatus = new CategoryStatusResponseDTO();
            this.categoryStatus.toDto(entity.getCategoryStatus());
        }
        this.children = entity.getChildren().stream()
                .map(child -> {
                    CategoryResponseDTO childDto = new CategoryResponseDTO();
                    childDto.toDto(child);
                    return childDto;
                })
                .collect(Collectors.toList());
        this.parentId = entity.getParent() != null ? entity.getParent().getCategoryId() : null;
        this.level = setLevel(entity);

    }

    public void toDto(Category entity,int role) {
        BeanUtils.copyProperties(entity, this, "createdAt", "updatedAt");

        if (entity.getCategoryStatus() != null) {
            this.categoryStatus = new CategoryStatusResponseDTO();
            this.categoryStatus.toDto(entity.getCategoryStatus());
        }

        this.parentId = entity.getParent() != null ? entity.getParent().getCategoryId() : null;
        this.level = setLevel(entity);
    }

    public int setLevel(Category parentEntity) {
        if (parentEntity.getParent() == null) {
            return 0;
        } else {
            return setLevel(parentEntity.getParent()) + 1;
        }
    }
}
