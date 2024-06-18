package dev.jaffer.productService.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel {


    private String name;
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = {CascadeType.REMOVE})
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 1)
    @JsonIgnore
    private List<Product> products;
}
