package autorest.astahxmlparser.xmlreader;

import autorest.astahxmlparser.umldatastructure.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class XmlReader
{
  public static UmlModel ReadModel(String input)
  {
    Document doc = AcquireDocument(input);

    UmlModel model = new UmlModel();
    ModelBuilder mb = new ModelBuilder(model, doc);
    return mb.Build();
  }

  private static Document AcquireDocument(String input)
  {
    try
    {
      File file = new File(input);
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      return db.parse(file);
    }
    catch (Exception e)
    {
	     e.printStackTrace();
    }

    return null;
  }
}
