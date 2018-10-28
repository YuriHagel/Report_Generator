package parser;

import lombok.Data;
import settings.Settings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * XML parser.
 */
@Data
public class XmlParser implements Parser {
  private static final Logger log = Logger.getLogger(XmlParser.class.getName());
  private Settings settings;

  @Override
  public void parse(String fileName) {
    try {

      JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);
      XMLStreamReader xmlStreamReader = XMLInputFactory.newFactory().createXMLStreamReader(new FileInputStream(fileName));
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      xmlStreamReader.nextTag();
      settings = (Settings) unmarshaller.unmarshal(xmlStreamReader);

    }catch (JAXBException e) {
      Logger.getLogger(XmlParser.class.getName()).log(new LogRecord(Level.WARNING, "Exception parsing elements of xml fileName!"));
    }catch (FileNotFoundException ex) {
      log.info("xml fileName not found");
      System.out.println("Oops, FileNotFoundException caught");
    }catch (XMLStreamException e) {
      log.info("Exception reading xml fileName");

    }
  }
}
