package oop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
public class Product {
  private String name;
  private double price;
  private int stock;

  public int isOrdered(int qty) {
    return this.stock - qty;
  }
}
