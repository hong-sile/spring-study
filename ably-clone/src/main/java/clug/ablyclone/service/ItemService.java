package clug.ablyclone.service;

import clug.ablyclone.domain.Item;
import clug.ablyclone.dto.FormattedItemDetailResponse.ItemDetailResponse;
import clug.ablyclone.dto.FormattedItemPreviewResponse.ItemPreviewResponse;
import clug.ablyclone.exception.ItemNotFoundException;
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

  public List<ItemPreviewResponse> findAll(final long lastItemId) {
    return itemRepository.findAllFetchSeller(lastItemId)
        .stream()
        .map(ItemPreviewResponse::from)
        .toList();
  }

  public ItemDetailResponse findById(final Long id) {
    final Item item = itemRepository.findByIdFetchSellerAndImageUrls(id)
        .orElseThrow(ItemNotFoundException::new);
    return ItemDetailResponse.from(item);
  }
}
