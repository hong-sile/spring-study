package Jpa_cascade.parent;

import Jpa_cascade.child.Child;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Parent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<Child> children = new ArrayList<>();

  public Parent(final String name) {
    this.name = name;
  }

  public void addChild(final Child child) {
    children.add(child);
    child.setParent(this);
  }
}
