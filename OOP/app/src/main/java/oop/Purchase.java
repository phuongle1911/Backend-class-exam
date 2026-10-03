package oop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
public class Purchase {
  private String itemName;
  private int qty;
  private double price;

}
