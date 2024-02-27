package clug.ablyclone.support;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import clug.ablyclone.controller.ItemController;
import clug.ablyclone.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest({ItemController.class})
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public abstract class DocTest {

  protected static final FieldDescriptor 성공여부
      = fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("API 성공 여부");
  protected static final FieldDescriptor 메시지
      = fieldWithPath("msg").type(JsonFieldType.STRING).description("API 반환 값에 대한 메시지");

  @Autowired
  protected MockMvc mockMvc;

  @MockBean
  protected ItemService itemService;

  @BeforeEach
  void setUp(
      final WebApplicationContext applicationContext,
      final RestDocumentationContextProvider provider
  ) {
    mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
        .apply(documentationConfiguration(provider).operationPreprocessors()
            .withRequestDefaults(prettyPrint())
            .withResponseDefaults(prettyPrint()))
        .build();
  }
}
