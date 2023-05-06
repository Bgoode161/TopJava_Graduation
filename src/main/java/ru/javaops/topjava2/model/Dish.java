package ru.javaops.topjava2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id",
                                            "date"}, name = "dishes_unique_name_restaurant_date_idx")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Dish extends NamedEntity {
    @Column(name = "date", columnDefinition = "date default now()", nullable = false)
    @NotNull
    private LocalDate dateCreated;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 10, max = 10000)
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;

}
