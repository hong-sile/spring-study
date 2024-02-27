package clug.ablyclone.controller;

import clug.ablyclone.dto.ItemPreviewResponse;
import clug.ablyclone.dto.ItemPreviewResponses;
import clug.ablyclone.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping("/items")
  public ResponseEntity<ItemPreviewResponses> findAllItems() {
    final List<ItemPreviewResponse> itemPreviewResponses = itemService.findAll();

    final ItemPreviewResponses actualResponse = new ItemPreviewResponses(itemPreviewResponses);

    return ResponseEntity.ok(actualResponse);
  }
}
