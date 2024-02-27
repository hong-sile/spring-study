package clug.ablyclone.fixture.domain;

import clug.ablyclone.domain.ImageUrl;
import java.util.List;

public class ImageUrlFixture {

  public static List<ImageUrl> IMAGES() {
    return List.of(new ImageUrl("image"));
  }
}
