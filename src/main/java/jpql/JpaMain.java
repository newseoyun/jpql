package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        
        //persistence.xml 에서 persistence-unit name 을 가져옴. 애플리케이션에서 오직 하나만 생성해서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); //쓰레드 간에 공유X. 사용하고 버려야 한다.

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {


            Member member = new Member();
            member.setUsername("A");
            em.persist(member);


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
