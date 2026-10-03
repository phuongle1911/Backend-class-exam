package oop;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
public class ProdTrans {
  private String prodName;
  private ActionEnums action;
  private String code;
  private int qty;
  private LocalDateTime timestamp;


}
