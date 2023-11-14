package com.example.tddstudy;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class DataCleanerExtension implements BeforeEachCallback {

  @Override
  public void beforeEach(ExtensionContext context) {
    final DatabaseCleaner databaseCleaner = getDataCleaner(context);
    databaseCleaner.clear();
  }

  private DatabaseCleaner getDataCleaner(ExtensionContext extensionContext) {
    return SpringExtension.getApplicationContext(extensionContext)
        .getBean(DatabaseCleaner.class);
  }
}
