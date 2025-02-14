package hellojpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Team2 {
   
   @Id @GeneratedValue
   private Long id;
   private String name;
   
   @OneToMany(mappedBy= "team") //1:N 연결에서 Team2가 어디랑 연결되어 있는지 알려주는 명령어 mappedBy
   private List<MemberOfTeam2> members2 = new ArrayList<>();

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
   public List<MemberOfTeam2> getMembers2() {
      return members2;
   }
   public void setMembers2(List<MemberOfTeam2> members2) {
      this.members2 = members2;
   }

   
}
