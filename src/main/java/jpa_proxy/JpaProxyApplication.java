package jpa_proxy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaProxyApplication {

  public static void main(String[] args) {
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-proxy");
    final EntityManager em = emf.createEntityManager();
    final EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }
  }
}
