package com.example.springjpa;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springjpa.member.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest
@AutoConfigureTestDatabase
class JpaTest {

  @PersistenceContext
  private EntityManager em;
  @Autowired
  private TransactionTemplate txTemplate;

  @Test
  void JPQL을_사용하여_Member_삽입_조회하기() {
    //given
    final Member hongSile = new Member("홍실", 21);
    txTemplate.executeWithoutResult((txStatus) -> em.persist(hongSile));

    //when
    final String jpql = "select m from Member m";
    final List<Member> members = em.createQuery(jpql, Member.class)
        .getResultList();

    //then
    assertThat(members)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
        .containsExactlyInAnyOrder(hongSile);
  }
}
