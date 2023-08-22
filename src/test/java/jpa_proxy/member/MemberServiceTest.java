package jpa_proxy.member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa_proxy.team.Team;
import jpa_proxy.team.TeamRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberServiceTest {

  private static final Long MEMBER_PK = 1L;
  private static final Long TEAM_PK = 2L;

  @Autowired
  private MemberRepository memberRepository;
  @PersistenceContext
  private EntityManager em;
  @Autowired
  private TeamRepository teamRepository;

  @Transactional
  @Test
  @DisplayName("jpa lazy loading test 초기 조회 find로 하기")
  void jpaLazyLoadingFirstFind() {
    //이미 db에 id가 1인 member가 존재할 경우
    final Member firstFindMember = em.find(Member.class, MEMBER_PK);
    System.out.println("savedMember class : " + firstFindMember.getClass());
    System.out.println(firstFindMember.getUsername());

    final Member secondFindMember = em.getReference(Member.class, MEMBER_PK);
    System.out.println("refinded class : " + secondFindMember.getClass());
  }

  @Transactional
  @Test
  @DisplayName("jpa lazy loading test 초기 조회 getReference로 하기")
  void jpaLazyLoadingFirstGetReference() {
    //이미 db에 id가 1인 member가 존재할 경우
    final Member firstFindMember = em.getReference(Member.class, MEMBER_PK);
    System.out.println("savedMember class : " + firstFindMember.getClass());
    System.out.println(firstFindMember.getUsername());

    final Member secondFindMember = em.getReference(Member.class, MEMBER_PK);
    System.out.println("refinded class : " + secondFindMember.getClass());
    System.out.println(secondFindMember.getUsername());
  }

  @Transactional
  @Test
  @DisplayName("jpa lazy loading test 초기 조회 getReference로 추후 조회 find로")
  void jpaLazyLoadingFirstGetReferenceSecondFind() {
    //이미 db에 id가 1인 member가 존재할 경우
    final Member firstFindMember = em.getReference(Member.class, MEMBER_PK);
    System.out.println("savedMember class : " + firstFindMember.getClass());
    System.out.println(firstFindMember.getUsername());

    final Member secondFindMember = em.find(Member.class, MEMBER_PK);
    System.out.println("refinded class : " + secondFindMember.getClass());
    System.out.println(secondFindMember.getUsername());
  }

  @Transactional
  @Test
  @DisplayName("멤버 repository 써서, proxy 확인해보기")
  void lazyLoadingProxy() {
    final Member member = memberRepository.findById(MEMBER_PK).get();
    System.out.println(member.getClass());
    System.out.println(member.getTeam().getClass());
    final Team foundTeam = em.find(Team.class, TEAM_PK);
    System.out.println(foundTeam.getClass());
    System.out.println(foundTeam == member.getTeam());
  }
}
