package data_cleaner;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseCleaner {

  private final List<String> tableNames = new ArrayList<>();

  @PersistenceContext
  private EntityManager entityManager;

  @PostConstruct
  @SuppressWarnings("unchecked")
  private void findDatabaseTableNames() {
    final List<Object[]> tableInfos = entityManager
        .createNativeQuery("SHOW TABLES")
        .getResultList();
    for (final Object[] tableInfo : tableInfos) {
      String tableName = (String) tableInfo[0];
      tableNames.add(tableName);
    }
  }

  private void truncate() {
    entityManager.createNativeQuery(String.format("SET FOREIGN_KEY_CHECKS %d", 0)).executeUpdate();
    for (String tableName : tableNames) {
      entityManager.createNativeQuery(String.format("TRUNCATE TABLE %s", tableName))
          .executeUpdate();
    }
    entityManager.createNativeQuery(String.format("SET FOREIGN_KEY_CHECKS %d", 1)).executeUpdate();
  }

  @Transactional
  public void clear() {
    entityManager.clear();
    truncate();
  }
}
