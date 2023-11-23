package com.example.springjpa.member;

import com.example.springjpa.team.Team;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "NAME")
  private String username;
  private Integer age;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn
  private Team team;

  public Member(final String userName, final Integer age) {
    this.username = userName;
    this.age = age;
  }

  public void printMemberAndTeam() {
    System.out.println(this);
    System.out.println(team);
  }

  public void setTeam(final Team team) {
    this.team = team;
  }

  public void setId(final Long id) {
    this.id = id;

  }
}
