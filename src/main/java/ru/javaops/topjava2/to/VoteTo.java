package ru.javaops.topjava2.to;

import java.time.LocalDate;
import java.time.LocalTime;

public class VoteTo extends BaseTo {

   private LocalDate dateCreated;

   private LocalTime timeCreated;

   private int restaurantId;

   public VoteTo(int id, LocalDate dateCreated, LocalTime timeCreated, int restaurantId) {
       super(id);
       this.dateCreated = dateCreated;
       this.timeCreated = timeCreated;
       this.restaurantId = restaurantId;
   }

}
