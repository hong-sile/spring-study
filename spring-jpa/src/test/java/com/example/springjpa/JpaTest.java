package com.example.springjpa;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springjpa.member.Member;
import com.example.springjpa.member.MemberRepository;
import com.example.springjpa.support.DataCleanerExtension;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest
@ExtendWith(DataCleanerExtension.class)
class JpaTest {

  @Autowired
  private MemberRepository memberRepository;
  @PersistenceContext
  private EntityManager em;
  @Autowired
  private TransactionTemplate txTemplate;

  @Test
  void JPQL을_사용하여_Member_삽입_조회하기() {
    //given
    final Member hongSile = 멤버를_저장하고_반환한다();

    //when
    final String jpql = "select m from Member m";
    final List<Member> members = em.createQuery(jpql, Member.class)
        .getResultList();

    //then
    assertThat(members)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrder(hongSile);
  }

  private Member 멤버를_저장하고_반환한다() {
    return memberRepository.save(new Member("홍실", 24));
  }

  /**
   * Criteria 는 거의 안 쓴다 만세!!
   */
  @Test
  void Criteria를_사용하여_조회하기() {
    //given
    final Member member = 멤버를_저장하고_반환한다();

    //when
    final CriteriaBuilder cb = em.getCriteriaBuilder();
    final CriteriaQuery<Member> query = cb.createQuery(Member.class);

    final Root<Member> m = query.from(Member.class);
    final CriteriaQuery<Member> cq = query.select(m)
        .where(cb.equal(m.get("username"), member.getUsername()));

    final List<Member> members = em.createQuery(cq).getResultList();

    //then
    assertThat(members)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrder(member);
  }

  @Test
  void QueryDSL을_이용해보기() {
    //TODO: QueryDsl 학습후 확인하기

  }

  @Test
  void NativeQuery을_이용해보기() {
    //given
    final Member member = 멤버를_저장하고_반환한다();

    //when
    final String sql = "select * from member";
    final List<Member> members = em.createNativeQuery(sql, Member.class).getResultList();

    //then
    assertThat(members)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrder(member);
  }
}
