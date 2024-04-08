package com.files.library.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private LocalDate leaseDate;
    private LocalDate returnDate;
    private Double totalCost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private LibraryUser user;

    @ManyToOne
    @JoinColumn(name = "stock_type_id")
    private StockType stockType;
}
