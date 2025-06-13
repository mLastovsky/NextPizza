package com.nextpizza.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoughType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @OneToMany(mappedBy = "doughType", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductItem> productItems;

}
