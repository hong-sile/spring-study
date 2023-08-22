package jpa_proxy.member;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa_proxy.team.Team;
import jpa_proxy.team.TeamRepository;
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

  @Transactional
  @Test
  @DisplayName("멤버 repository 써서, proxy 확인해보기")
  void lazyLoadingCase() {
    final Member member = memberRepository.findById(MEMBER_PK).get();

    System.out.println(member.getTeam().getClass());
    System.out.println(member.getTeam().getName());
  }

  @Transactional
  @Test
  @DisplayName("즉시로딩 사용 시 N+1 문제 발생")
  void eagerLoadingNPlus1() {
    //EAGER로 세팅한 경우 join을 사용하지 않고 쿼리가, 쪼개져서 나간다.
    //아래의 경우 member 조회 쿼리 한번, team 조회 쿼리 한번 나간다.
    //심지어, Team이 여러개인 경우(연관관계에 있는 값이 여러개인 경우에는, 그 수만큼 나간다.)
    //예를 들어 TeamA에 속한 멤버와 TeamB에 속한 멤버가 있으면 Team 조회 쿼릭 ㅏ두번 나간다.
    final List<Member> allMemberJPQL = memberRepository.findAllMemberJPQL();
  }
}
