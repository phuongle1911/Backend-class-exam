package oop;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
public class Order {
  private String code;
  ArrayList<Purchase> purchases;
  private double totalPrice;
  private LocalDateTime timestamp;

  public void setTotalPrice() {
    for (Purchase purchase:purchases) {
      this.totalPrice += purchase.getPrice() * purchase.getQty();
    }
  } 

}
