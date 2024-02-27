package clug.ablyclone.controller;

import clug.ablyclone.dto.FormattedItemDetailResponse;
import clug.ablyclone.dto.FormattedItemDetailResponse.ItemDetailResponse;
import clug.ablyclone.dto.FormattedItemPreviewResponse;
import clug.ablyclone.dto.FormattedItemPreviewResponse.ItemPreviewResponse;
import clug.ablyclone.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping("/items")
  public ResponseEntity<FormattedItemPreviewResponse> findAllItems() {
    final List<ItemPreviewResponse> itemPreviewResponses = itemService.findAll();

    final FormattedItemPreviewResponse actualResponse
        = new FormattedItemPreviewResponse(itemPreviewResponses);

    return ResponseEntity.ok(actualResponse);
  }

  @GetMapping("/items/{id}")
  public ResponseEntity<FormattedItemDetailResponse> findItem(@PathVariable final Long id) {
    final ItemDetailResponse itemDetailResponse = itemService.findById(id);

    final FormattedItemDetailResponse actualResponse
        = new FormattedItemDetailResponse(itemDetailResponse);

    return ResponseEntity.ok(actualResponse);
  }
}
