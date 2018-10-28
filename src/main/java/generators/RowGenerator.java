package generators;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import settings.Column;
import settings.Settings;

import java.util.List;

/**
 * Генератор строк.
 */
@Data
public class RowGenerator {


  private static final char WORD_DELIMITER = ' ';
  private static final char COLUMN_DELIMITER = '|';
  private static final char ROW_DELIMITER = '-';
  private String headRow;
  private String separatorRow;
  private Settings settings;
  /*Создаем колонки*/
  public String generate(String[] data) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(COLUMN_DELIMITER);
    for (int i = 0; i < settings.getColumns().size(); i++) {
      stringBuilder.append(generateColumn(data[i], settings.getColumns().get(i).getWidth()));
    }
    return stringBuilder.toString();
  }
  /*Получаем разделитель строк с настройками из файла xml*/
  public void generateRowDelimiter() {
    separatorRow = StringUtils.repeat(ROW_DELIMITER, settings.getPage().getWidth());
  }

  public void generateHead(List<Column> columns) {
    if (columns == null) {
      headRow = "";
      return;
    }
    String[] columnsTitlesStringArray = new String[columns.size()];
    int i = 0;
    for (Column column : columns) {
      columnsTitlesStringArray[i] = column.getTitle();
      i++;
    }
    headRow = generate(columnsTitlesStringArray);
  }

  private StringBuilder generateColumn(String s, int columnWidth) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(WORD_DELIMITER);
    stringBuilder.append(s);
    int diffSize = columnWidth - s.length() ;
    if(diffSize > 0) {
      stringBuilder.append(StringUtils.repeat(" ", diffSize));
    }
    stringBuilder.append(WORD_DELIMITER).append(COLUMN_DELIMITER);
    return stringBuilder;
  }
}
