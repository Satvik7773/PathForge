package com.example.pathforge.run;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        String title,
        LocalDateTime starttime,
        LocalDateTime endtime,
        Integer miles,
        Location location
   ) {

   public Integer getId() {
      return id;
   }

   public Integer getMiles(){
      return miles;
   }

   public LocalDateTime getTimeStarted(){
      return starttime;
   }

   public LocalDateTime getTimeEnded(){
      return endtime;
   }

   public Location getLocation(){
      return location;
   }

   public String getName(){
      return title;
   }
}
