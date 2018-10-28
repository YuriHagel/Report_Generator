package generators;

import lombok.Data;
import settings.Settings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Data
public class ReportGenerator {
  private static final Logger log = Logger.getLogger(ReportGenerator.class.getName());
  private static final String NEW_LINE = "\r\n";

  private Settings settings;
  private List<String[]> inputData;
  private List<String> resultReport = new ArrayList<>();


  public void generate(String msg) {
    PageGenerator pageGenerator = new PageGenerator();
    resultReport = pageGenerator.generate(inputData);
    try {

      File fileDir = new File("report.txt");

      Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF-16"));

      for (String line : resultReport) {
        out.append(line).append(NEW_LINE);
      }
      out.write(msg, 0, msg.length());
      out.flush();
      out.close();

    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}






