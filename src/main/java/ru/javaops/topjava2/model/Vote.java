package ru.javaops.topjava2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.javaops.topjava2.HasId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Vote")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Vote extends BaseEntity {

    @Column(name = "date", nullable = false, columnDefinition = "date default now()")
    @NotNull
    private LocalDate localDate;

    @Column(name = "time", nullable = false, columnDefinition = "time default now()")
    @NotNull
    private LocalTime localTime;

    @Column(name = "user_id", nullable = false)
    @NotNull
    private Integer userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    @NotNull
    public Restaurant restaurant;

}
