package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
class MemberOfTeam2{

   @Id @GeneratedValue
   private Long id;

   @Column(name = "USER_NAME")
   private String name;
   private int age;

   //@Column(name = "TEAM_ID")
   //private Long teamId;

   @ManyToOne //MemberOfTeam입장 To Team입장
   @JoinColumn(name = "TEAM_ID")  //Join하는 컬럼이 TEAM_ID이다. 이 TEAM_ID는 DB에 존재하는 컬럼.
   private Team2 team;

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

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public Team2 getTeam() {
      return team;
   }

   public void setTeam(Team2 team) {
      this.team = team;
   }

   
}