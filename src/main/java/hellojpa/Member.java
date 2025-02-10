package hellojpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Member {

    @Id
    private int id;

    //객체는 username, DB명은 name
    @Column(name="name")
    private String username;

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
}
