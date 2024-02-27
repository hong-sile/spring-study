package clug.ablyclone.document;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import clug.ablyclone.dto.FormattedItemPreviewResponse.ItemPreviewResponse;
import clug.ablyclone.support.DocTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

public class ItemDocTest extends DocTest {

  @Test
  void 아이템_목록_조회() throws Exception {
    final List<ItemPreviewResponse> itemPreviewResponses = List.of(
        new ItemPreviewResponse(1L, "29%", "clug_1", "나시 티셔츠", "이미지", 23500L)
    );

    when(itemService.findAll()).thenReturn(itemPreviewResponses);

    mockMvc.perform(get("/items"))
        .andExpect(status().isOk())
        .andDo(document("find-all-items",
            responseFields(
                성공여부,
                메시지,
                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("상품 id"),
                fieldWithPath("data[].discountPercentage").type(JsonFieldType.STRING)
                    .description("할인율"),
                fieldWithPath("data[].sellerName").type(JsonFieldType.STRING).description("판매자 이름"),
                fieldWithPath("data[].itemName").type(JsonFieldType.STRING).description("상품 이름"),
                fieldWithPath("data[].imageUrl").type(JsonFieldType.STRING)
                    .description("상품 이미지 url"),
                fieldWithPath("data[].discountedPrice").type(JsonFieldType.NUMBER)
                    .description("상품 가격")
            )
        ));
  }
}
