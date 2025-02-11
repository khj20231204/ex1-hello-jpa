package hellojpa;

import jakarta.persistence.*;

@Entity
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member_pk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //AUTO : DB방언에 맞춰 자동으로 생성, 자동으로 PK를 만들기는 하는데 시퀀스처럼 값을 일정하게 증가 시키지는 않는다.
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //IDENTITY : 기본키 생성을 데이터베이스에 위임, 시퀀스처럼 자동 증가가 된다.
    //@GeneratedValue(strategy = GenerationType.TABLE)
    //TABLE :
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    public Member_pk() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
