package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
     private double totalPrice;
     // region getters and setters
     public long getId() {
          return id;
     }

     public void setId(long id) {
          this.id = id;
     }

     public double getTotalPrice() {
          return totalPrice;
     }

     public void setTotalPrice(double totalPrice) {
          this.totalPrice = totalPrice;
     }
     // endregion getters and setters
     @Override
     public String toString() {
          return "Order{" + "id=" + id + ", totalPrice=" + totalPrice + '}';
     }
}
