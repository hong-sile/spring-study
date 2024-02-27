package clug.ablyclone.support;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import clug.ablyclone.repository.ItemRepository;
import clug.ablyclone.repository.SellerRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@SuppressWarnings("NonAsciiCharacters")
public abstract class AcceptanceTest {

  @Autowired
  protected ItemRepository itemRepository;
  @Autowired
  protected SellerRepository sellerRepository;

  @BeforeEach
  void assuredTestSetUp(@LocalServerPort final int port) {
    RestAssured.port = port;
  }
}
