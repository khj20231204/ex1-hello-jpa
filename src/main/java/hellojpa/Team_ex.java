package hellojpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Team_ex {
   
   @Id @GeneratedValue
   private Long id;

   private String name;

   @OneToMany(mappedBy="team_ex")
   private List<Member_ex> members = new ArrayList<>();

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Member_ex> getMembers() {
      return members;
   }

   public void setMembers(List<Member_ex> members) {
      this.members = members;
   }
}
