package jpa_proxy.member;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

  @Query("select m From Member m")
  List<Member> findAllMemberJPQL();
}
