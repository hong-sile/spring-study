package clug.ablyclone.service;

import clug.ablyclone.dto.ItemPreviewResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  public List<ItemPreviewResponse> findAll() {
    return List.of(
        new ItemPreviewResponse("29%", "clug_1", "나시 티셔츠", "이미지", 23500L)
    );
  }
}
