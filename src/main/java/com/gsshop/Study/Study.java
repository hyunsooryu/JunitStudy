package com.gsshop.Study;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Study {
   private StudyStatus status;

   private Member owner;

   private int limit;

   private String name;

   public Study(int limit){
        this.limit = limit;
   }

   public Study(int limit, String name){
       if(limit < 0) throw new IllegalArgumentException(
               "limit은 0 보다 커야 합니다."
       );
       this.status = StudyStatus.DRAFT;
       this.limit = limit;
       this.name = name;
   }

   public StudyStatus getStatus(){
       return this.status;
   }

}
