package hellojpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private int id;

    //객체는 username, DB명은 name
    @Column(name="USERNAME")
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    //Integer와 가장 비슷한 타입이 DB에서 선택 됨
    private Integer age;

    /*
    자바에는 Enum이 있는데 DB에는 Enum이 없다
    이때 추가하는 어노테이션이 Enumerated
     */
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /*
    Temporal 형태 - DATE(날짜), TIME(시간), TIMESTAMP(날짜와 시간) 3가지가 있다
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifeieDate;

    /*
    varchar보다 더 큰 범위의 타입 : Lob
     */
    @Lob
    private String description;

    /* db와 관계없이 필드로만 사용하고 싶디 */
    @Transient
    private int tems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifeieDate() {
        return lastModifeieDate;
    }

    public void setLastModifeieDate(Date lastModifeieDate) {
        this.lastModifeieDate = lastModifeieDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
