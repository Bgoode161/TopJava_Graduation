package ru.javaops.topjava2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id",
                                            "date"}, name = "dishes_unique_name_restaurant_date_idx")})
@Getter
@Setter
@NoArgsConstructor
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Restaurant restaurant;

    public Dish(Integer id, String name, LocalDate dateCreated, Long price, Restaurant restaurant) {
        super(id, name);
        this.dateCreated = dateCreated;
        this.price = price;
        this.restaurant = restaurant;
    }
}
