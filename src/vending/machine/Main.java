package src.vending.machine;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

enum ProductType {
  DRINK,
  SNACK
}


class Product {
  int id;

  String name;

  BigDecimal price;

  ProductType type;


  public Product deepClone(Product product) {
    this.id = product.id;
    this.name = product.name;
    this.price = product.price;
    this.type = product.type;
    return this;
  }
}

class ProductManager {
  private static Map<Integer, Product> products; // get the Product

  private static Map<Integer, Integer> inventoryManager; //key is id product, value is the current quantity product

  private static ProductManager instance;

  private ProductManager() {
    products = new HashMap<>();
    inventoryManager = new HashMap<>();
  }

  public static ProductManager getInstance() {
    if (instance == null) {
      instance = new ProductManager();
    }
    return instance;
  }

  public void decrementProduct(int productId) throws Exception {
    if (inventoryManager.get(productId) == 0) {
      throw new Exception("Invalid Amount");
    }
    inventoryManager.put(productId, inventoryManager.get(productId) - 1);
  }


}

class Slot {

  int id;

  int row;

  int col;

  Product product;

  int quantity;
}

class SlotManager {
  private static Map<Integer, Slot> slot;

  private static SlotManager instance;

  private SlotManager() {
    slot = new HashMap<>();
  }

  public static SlotManager getInstance() {
    if (instance == null) {
      instance = new SlotManager();
    }
    return instance;
  }


  public Slot getSlotById(int slotId) {
    return slot.getOrDefault(slotId, null);
  }


}

interface PaymentMethod {
  boolean pay(int amount);
}

class CashPayment implements PaymentMethod {

  @Override
  public boolean pay(int amount) {
    return false;
  }
}

class CardPayment implements PaymentMethod {

  @Override
  public boolean pay(int amount) {
    return false;
  }
}

class MobilePayment implements PaymentMethod {

  @Override
  public boolean pay(int amount) {
    return false;
  }
}


class VendingMachine {
  ProductManager productManager = ProductManager.getInstance();

  SlotManager slotManager = SlotManager.getInstance();

  private BigDecimal balance = BigDecimal.ZERO;

  private Product productSelected = new Product();

  private Slot slotSelected = new Slot();

  public BigDecimal selectProduct(int slotId) throws Exception {
    Slot slot = slotManager.getSlotById(slotId);
    if (Objects.isNull(slot)) {
      throw new Exception("Invalid chosen slot");
    }
    slotSelected = slot;
    productSelected = productSelected.deepClone(slot.product);
    return productSelected.price;
  }

  public Product purchase(BigDecimal inputMoney) throws Exception {
    balance = balance.add(inputMoney);
    if (balance.compareTo(productSelected.price) < 0) {
      throw new Exception("Not enoughMoney");
    }
    balance = balance.subtract(productSelected.price); //return the money
    slotSelected.quantity--;
    productManager.decrementProduct(productSelected.id);
    slotSelected.quantity++;
    return productSelected;
  }

  public boolean purchase(PaymentMethod paymentMethod) throws Exception {
    if (!paymentMethod.pay(productSelected.price.intValue())) {
      throw new Exception("Payment failed!");
    }
    slotSelected.quantity--;
    productManager.decrementProduct(productSelected.id);
    return true;
  }

  public BigDecimal returnTheRemaining() {
    BigDecimal temp = BigDecimal.valueOf(balance.doubleValue());
    resetTheValue();
    return temp;
  }

  private void resetTheValue() {
    balance = BigDecimal.ZERO; /// reset the balance
    productSelected = null;
    slotSelected = null;
  }
}

public class Main {


}
