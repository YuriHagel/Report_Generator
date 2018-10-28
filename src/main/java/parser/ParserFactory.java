package parser;

import lombok.Data;

@Data
public class ParserFactory {

  public static Parser getParser(String extension){
    if (extension == null) {
      return null;
    } else if (extension.equals("tsv")) {
      return new TsvParser();
    } else if (extension.equals("xml")) {
      return new XmlParser();
    }
    return null;
  }
}
