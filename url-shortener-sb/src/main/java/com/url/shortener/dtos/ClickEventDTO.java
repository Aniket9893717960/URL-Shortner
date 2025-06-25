package com.url.shortener.dtos;


import java.time.LocalDateTime;
import java.util.Objects;

public class ClickEventDTO {
   private LocalDateTime clickDate;
   private Long count;

   public LocalDateTime getClickDate() {
      return clickDate;
   }

   public void setClickDate(LocalDateTime clickDate) {
      this.clickDate = clickDate;
   }

   public Long getCount() {
      return count;
   }

   public void setCount(Long count) {
      this.count = count;
   }

   @Override
   public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      ClickEventDTO that = (ClickEventDTO) o;
      return Objects.equals(clickDate, that.clickDate) && Objects.equals(count, that.count);
   }

   @Override
   public int hashCode() {
      return Objects.hash(clickDate, count);
   }

   @Override
   public String toString() {
      return "ClickEventDTO{" +
              "clickDate=" + clickDate +
              ", count=" + count +
              '}';
   }
}
