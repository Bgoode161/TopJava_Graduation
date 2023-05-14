package ru.javaops.topjava2.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Value
public class VoteTo extends BaseTo {

    @NotNull
    LocalDate dateCreated;

    @NotNull
    int userId;

    @NotNull
    int restaurantId;

   public VoteTo(int id, LocalDate dateCreated, int userId, int restaurantId) {
       super(id);
       this.dateCreated = dateCreated;
       this.restaurantId = restaurantId;
       this.userId = userId;
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VoteTo voteTo = (VoteTo) o;
        return userId == voteTo.userId && restaurantId == voteTo.restaurantId && Objects.equals(dateCreated, voteTo.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateCreated, userId, restaurantId);
    }
}
