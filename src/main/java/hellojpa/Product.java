package hellojpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
   
   @Id @GeneratedValue
   @Column(name = "PRODUCT_ID")
   private Long id;

   private String name;

   @OneToMany(mappedBy = "product")
   private List<Order> orders = new ArrayList<>();
}
