package jpa_cascade;

import Jpa_cascade.child.Child;
import Jpa_cascade.child.ChildRepository;
import Jpa_cascade.parent.Parent;
import Jpa_cascade.parent.ParentRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class JpaCascadeTest {

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private ParentRepository parentRepository;

  @Autowired
  private ChildRepository childRepository;

  @Transactional
  @Test
  @DisplayName("cascade 설정 안하고, parent와 child를 저장하기")
  void saveParentChildNonCascade() {
    final Child child1 = new Child("child1");
    final Child child2 = new Child("child2");
    final Parent parent = new Parent("parent1");
    parent.addChild(child1);
    parent.addChild(child2);

    childRepository.save(child1);
    childRepository.save(child2);
    parentRepository.save(parent);
  }

  @Transactional
  @Test
  @DisplayName("cascade 설정 하고, parent와 child를 저장하기")
  void saveParentChildSetCascadeMerge() {
    //같은 트랜잭션 범위 내에서 저장한 친구를 또 다시 save하려고 할 때, 그 땐 merge로 해야한다.
    final Parent parent = new Parent("parent1");
    parentRepository.save(parent);

    final Child child1 = new Child("child1");
    final Child child2 = new Child("child2");
    parent.addChild(child1);
    parent.addChild(child2);

    parentRepository.save(parent);
  }

  @Transactional
  @Test
  @DisplayName("cascade 설정 하고, parent와 child를 저장하기")
  void saveParentChildSetCascadePersist() {
    final Parent parent = new Parent("parent1");
    final Child child1 = new Child("child1");
    final Child child2 = new Child("child2");
    parent.addChild(child1);
    parent.addChild(child2);
    parentRepository.save(parent);
  }

  @Transactional
  @Test
  @DisplayName("고아객체 테스트")
  void parentOrphan() {
    final Parent parent = new Parent("parent1");
    final Child child1 = new Child("child1");
    final Child child2 = new Child("child2");
    parent.addChild(child1);
    parent.addChild(child2);
    final Parent savedParent = parentRepository.saveAndFlush(parent);
    em.clear();

    final Parent refinedParent = parentRepository.findById(savedParent.getId()).get();
    refinedParent.getChildren().remove(0);
    em.flush();
  }
}
