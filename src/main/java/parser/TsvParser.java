package parser;

import com.univocity.parsers.tsv.TsvParserSettings;
import lombok.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Logger;

@Data
public class TsvParser implements Parser {
  private final static String TAB_DELIMITER = "\\t";
  private static final Logger log = Logger.getLogger(TsvParser.class.getName());
  private List<String[]> data;


  public void parse(String fileName) {
    TsvParserSettings settings = new TsvParserSettings();
    com.univocity.parsers.tsv.TsvParser parser = new com.univocity.parsers.tsv.TsvParser(settings);
    FileInputStream fileInputStream;
    try {
      fileInputStream = new FileInputStream(fileName);
      data = parser.parseAll(new InputStreamReader(fileInputStream, "UTF-16"));
    } catch (FileNotFoundException ex) {
      log.info("File Not Found");
    } catch (UnsupportedEncodingException e) {
      log.info(fileName + ": UTF-16 is not supported");
    }


  }
}

