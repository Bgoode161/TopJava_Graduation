package ru.javaops.topjava2.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Vote")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Vote extends BaseEntity {

    @Column(name = "date_time", columnDefinition = "date default now()")
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

}
