package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //basic_execute();
        //columnMapping();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{

            Member_pk mp = new Member_pk();
            //mp.setId("ID_A");
            mp.setName("C");
            em.persist(mp);
            mp.setName("DA");
            em.persist(mp);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }finally {
            em.close();
        }
        emf.close();

    }

    public static void columnMapping(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            //Member member = new Member();
            //member.setId(2);
            Member member = em.find(Member.class, 2);
            member.setUsername("변경 했쬬");

            //GUEST가 왜 에러나는지 모르겠다
            member.setRoleType(RoleType.GUEST);

            em.persist(member);

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    public static void basic_execute(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            /* 입력 */
            Member_ex member = new Member_ex();
            member.setId(3);
            member.setName("Josep3");

            em.persist(member);

            /* 수정
             * find => java의 collection처럼 객체를 저장해 주는 기능
             * */
            Member_ex findMember = em.find(Member_ex.class, 1);
            System.out.println(findMember.getName());
            findMember.setName("HelloJPA44");
            //em.persist(findMember);

            /* 삭제
            em.remove(findMember);
            System.out.println(findMember);
            */

            em.createQuery("select m from Member m");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}