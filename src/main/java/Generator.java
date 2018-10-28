import generators.ReportGenerator;
import parser.ParserFactory;
import parser.TsvParser;
import parser.XmlParser;

/**
 * @author Hagel.Y  28.10.2018
 */
public class Generator {
  private static final String XML = "xml";
  private static final String TSV = "tsv";
  /*Запуск*/
  public static void main(String[] args) {

    XmlParser xmlParser = (XmlParser) ParserFactory.getParser(XML);
    xmlParser.parse(args[0]);

    TsvParser tsvParser = (TsvParser) ParserFactory.getParser(TSV);
    tsvParser.parse(args[1]);

    ReportGenerator reportGenerator = new ReportGenerator();
    reportGenerator.generate(args[2]);
  }
}
