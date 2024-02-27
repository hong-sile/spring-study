package clug.ablyclone.support;

import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//TODO : 추후 트랜잭션이 아닌 다른 방식으로 디비 초기화
@Transactional
public abstract class ServiceTest {

}
