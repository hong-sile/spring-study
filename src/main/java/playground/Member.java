package playground;

public class Member {

    private final String name;
    private final Integer age;

    public Member(final String name, final Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
