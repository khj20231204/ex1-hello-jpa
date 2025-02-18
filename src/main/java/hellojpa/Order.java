package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Order {
   
   @Id @GeneratedValue
   @Column(name = "ORDER_ID")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "MEMBER_ID")
   private Member member;

   @ManyToOne
   @JoinColumn(name = "PRODUCT_ID")
   private Product product;
}
