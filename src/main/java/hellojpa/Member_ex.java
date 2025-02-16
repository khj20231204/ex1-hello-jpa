package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Member_ex {

    @Id @GeneratedValue
    private int id;

    @Column(name="USER_NAME")
    private String name;

    private int age;

    @ManyToOne 
    @JoinColumn(name = "TEAM_ID")
    private Team_ex team_ex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Team_ex getTeam_ex() {
        return team_ex;
    }

    public void setTeam_ex(Team_ex team_ex) {
        this.team_ex = team_ex;
    }

    
}
