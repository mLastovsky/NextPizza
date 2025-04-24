package com.nextpizza.model;

import com.nextpizza.service.ProductService;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> product;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private OffsetDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private OffsetDateTime updatedAt;

}
