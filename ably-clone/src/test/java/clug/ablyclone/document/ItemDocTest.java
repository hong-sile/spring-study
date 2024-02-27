package clug.ablyclone.document;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import clug.ablyclone.dto.FormattedItemDetailResponse.ItemDetailResponse;
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

    when(itemService.findAll(0)).thenReturn(itemPreviewResponses);

    mockMvc.perform(get("/items"))
        .andExpect(status().isOk())
        .andDo(document("find-all-items",
            queryParameters(
                parameterWithName("cursor").description("(Optional) 이전 페이지 마지막 게시글 식별자값(id)")
                    .optional()
            ),
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

  @Test
  void 아이템_단건_조회() throws Exception {
    final Long id = 1L;
    final ItemDetailResponse detailResponse = new ItemDetailResponse(
        List.of("image"), "나시 티셔츠", 29900L, 21229L, 10L, "Clug_1", "클러그 프로필 이미지 url"
    );

    when(itemService.findById(id)).thenReturn(detailResponse);

    mockMvc.perform(get("/items/" + id))
        .andExpect(status().isOk())
        .andDo(document("find-item",
            responseFields(
                성공여부,
                메시지,
                fieldWithPath("data.imageUrls[]").type(JsonFieldType.ARRAY).description("이미지 url들"),
                fieldWithPath("data.name").type(JsonFieldType.STRING).description("상품 이름"),
                fieldWithPath("data.likeCount").type(JsonFieldType.NUMBER).description("좋아요 수"),
                fieldWithPath("data.originPrice").type(JsonFieldType.NUMBER)
                    .description("상품 원래 가격"),
                fieldWithPath("data.discountedPrice").type(JsonFieldType.NUMBER)
                    .description("상품 할인 가격"),
                fieldWithPath("data.sellerName").type(JsonFieldType.STRING).description("판매자 이름"),
                fieldWithPath("data.sellerProfileImageUrl").type(JsonFieldType.STRING)
                    .description("판매자 프로필 이미지 url")
            )
        ));
  }
}
