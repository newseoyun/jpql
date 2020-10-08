package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
            Team team = new Team();
            team.setName("teamA");
            */

            /*
            Team team4 = em.find(Team.class, 4L);
            Member member1 = em.find(Member.class, 1L);
            member1.setTeam(team4);
            */

            //em.persist(member1);

            //String query = "select t from Member m inner join m.team t where t.name = :teamName";
            String query = "select m from Member m left join m.team t";

            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getUsername() = " + member.getUsername());
                System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
            }
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();

    }
}
