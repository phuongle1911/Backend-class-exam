package oop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Warehouse {
  Map<String,Product> productInventory = new HashMap<>();
  List<ProdTrans> inventoryList = new LinkedList<>();


  int orderNum = 0;


  public Product findProduct(String name) {
    Product targetProduct = productInventory.get(name);
    if (targetProduct == null) {
      System.out.println("product not found!");
      return null;
    }
    return targetProduct;
  }
  
  public boolean addProduct(String name, double price) {
    if (price <= 0 || name.isBlank()) {
      return false;
    }
    if (findProduct(name) == null) {
      return false;
    } 

    Product newProduct = new Product(name, price, 0);
    productInventory.put(newProduct.getName(), newProduct);
    System.out.println("product added!");
    return true;
  }

  public boolean updateStock(String name, int stock) {
    Product targetProduct = findProduct(name);
    if (findProduct(name) == null) {
      return false;
    } 

    int currentStock = targetProduct.getStock();
    if (currentStock + stock < 0 || stock == 0) {
      return false;
    } 

    ActionEnums action;
    if (stock > 0) {
      action = ActionEnums.STOCK_IN;
    } else if (stock < 0) {
      action = ActionEnums.STOCK_OUT;
    }
    targetProduct.setStock(currentStock + stock);

    ProdTrans transaction = new ProdTrans(targetProduct.getName(), action, "", stock, LocalDateTime.now());
    inventoryList.add(transaction);
    System.out.println("stock updated!");
    return true;

  }



  public boolean buy(ArrayList<Purchase> newPurchases) {

    for (Purchase purchase:newPurchases) {
      String item = purchase.getItemName();
      if (findProduct(item) == null || findProduct(item).getStock() - purchase.getQty() < 0) {
        System.out.println("order rejected!");
        return false;
      }
    }
    orderNum += 1;
    String orderCode = "ORDER-" + String.format("%3d", orderNum);

    Order newOrder = new Order(orderCode, newPurchases,0,LocalDateTime.now());
    newOrder.setTotalPrice();
    System.out.println("Order: \n ---------------
                        Order Code: " + orderCode + "\n" + 
                        "Items: \n ");
    for (Purchase purchase:newOrder) {
      ProdTrans transaction = new ProdTrans(purchase.getItemName(),ActionEnums.SALE_ORDER.name(),orderCode,purchase.getQty(),newOrder.getTimestamp());
      inventoryList.add(transaction);
      System.out.println(purchase.getItemName() + "    x" + purchase.getQty() + "    $" + purchase.getPrice());
    }
    return true;
    
  }


  public inventoryHistory(String productName) {
    String formatQty;
    for (ProdTrans transaction:inventoryList) {
      String product = transaction.getProdName();

      if (product.equals(productName)) {
        if (transaction.getQty() > 0) {
          formatQty = "+"+transaction.getQty();
        } else {
          formatQty = transaction.getQty();
        }
        
        System.out.println(product + "\n" + formatQty + transaction.getAction() +  "     " + transaction.getCode() + "     " + transaction.getTimestamp());
      }
    }
  }

}
