package clug.ablyclone.service;

import clug.ablyclone.dto.FormattedItemPreviewResponse.ItemPreviewResponse;
import clug.ablyclone.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

  private final ItemRepository itemRepository;

  public List<ItemPreviewResponse> findAll() {
    return itemRepository.findAllFetchSeller()
        .stream()
        .map(ItemPreviewResponse::from)
        .toList();
  }
}
