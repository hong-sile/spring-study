package clug.ablyclone.support;

import clug.ablyclone.config.DatabaseClearExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(DatabaseClearExtension.class)
public abstract class ServiceTest {

}
