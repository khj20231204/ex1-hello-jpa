package hellojpa;

import java.util.List;

import hellojpa.domain.Movie;
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

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
           
            MemberOfTeam member = new MemberOfTeam();

            member.setName("member1");
            
            //mot.setTeamId(team.getId()); //위에 persist를 하면 영속상태가 되고 자동으로 team에 id값이 입력된다. 
            
            //이제 getId로 team의 pk를 넣는게 아니라 team클래스 자체를 넣는다
            member.setTeam(team);
            
            em.persist(member);

            /* 
            persist를 하면 영속성 컨텍스트의 캐쉬에 값이 저장된다 밑에 find로 찾은 값은 영속성 컨텍스트의 캐쉬에 있는 값이다.
            영속성 컨텍스트 말고 DB에서 바로 값을 불러오고 싶으면 다음과 같이 하면 된다
            */
            em.flush(); //sql문을 날려서 DB에 강제 저장
            em.clear(); //영속성 컨텍스 값을 초기화. 이렇게하면 영속성 컨텍스트의 캐쉬에 값이 없기 때문에 DB에서 찾아서 바로 가져온다

            MemberOfTeam findmember = em.find(MemberOfTeam.class, member.getId());
            
            //Team findteam = em.find(Team.class, findmember.getTeamId());
            //이제 findmember인 찾은 멤버에서 바로 team을 불러온다
            Team findteam = findmember.getTeam();

            System.out.println("-----------------------------------------"+findteam.getName());

            /*
            수정 - DB에 10번 팀이 있다고 가정하고 팀을 10번 팀으로 바꾸는 경우
            Team newTeam = em.find(Team.class, 10L);
            findmember.setTeam(newTeam); //setTeam으로 그냥 team을 입력하면 끝
            */

            /*
            현재 연관관계 주인은 N:1 관계의 N(1을 포함하고 있다)이다. 따라서 member가 연관관계의 주인이다
            team2에 member2를 삽입했을 때 정상적으로 값이 입력되지 않는다
            
            MemberOfTeam2 member2 = new MemberOfTeam2();
            member2.setName("member2");
            em.persist(member2);

            Team2 team2 = new Team2();
            team2.setName("TeamB");
            team2.getMembers2().add(member2); //그런데 team2에 member2를 삽입했다
            em.persist(team2);

            결과 : 정상 삽입이 되지 않는다
            */

            /*
            연관관계의 주인인 member에 team을 삽입한다
            */
            Team2 team2 = new Team2();
            team2.setName("TeamB");
            em.persist(team2);

            MemberOfTeam2 member2 = new MemberOfTeam2();
            member2.setName("member2");
            member2.setTeam(team2);  //연관관계의 주인인 member에 team을 삽입
            em.persist(member2);

            Team_ex team_ex = new Team_ex();
            team_ex.setName("team");
            em.persist(team_ex);

            
            Member_ex member_ex = new Member_ex();
            member_ex.setName("member");
            
            //member_ex.setTeam_ex(team_ex);
            //연관관계 편의 메서드 생성
            member_ex.changeTeam(team_ex);
            
            em.persist(member_ex);
            
            em.flush();
            em.clear();

            //team_ex.getMembers().add(member_ex);
            //연관관계 편의 메서드 생성
            //team_ex.addMember(member_ex);

            Team_ex teamlist = em.find(Team_ex.class, team_ex.getId());
            List<Member_ex> memberlist = teamlist.getMembers();
            for(Member_ex m : memberlist){
                System.out.println("-------------------"+m.getName());
            }

            MemberOfTeam2 findMember2 = em.find(MemberOfTeam2.class, member2.getId());
            List<MemberOfTeam2> member2_list = findMember2.getTeam().getMembers2();

            for(MemberOfTeam2 t2 : member2_list){
                System.out.println(t2.getTeam().getName());
            }

            System.out.println("======================");
            System.out.println(team_ex);
            System.out.println("======================");
            
            Movie movie = new Movie();
            movie.setDirector("director");
            movie.setActor("actor");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(10000);
            
            em.persist(movie);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println(findMovie);

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
        //persistence.xml파일의 <persistence-unit name="hello"> unit name과 일치해야 한다

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