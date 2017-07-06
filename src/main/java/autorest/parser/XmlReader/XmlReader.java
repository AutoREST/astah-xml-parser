package autorest.astahxmlparser.xmlreader;

import autorest.astahxmlparser.umldatastructure.*;
import java.io.File;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class XmlReader
{
  public static UmlModel ReadModel(String input) throws Exception
  {
    Document doc = AcquireDocument(input);

    UmlModel model = new UmlModel();
    ModelBuilder mb = new ModelBuilder(model, doc);
    return mb.Build();
  }

  public static Set<String> AcquirePackages(String input) throws Exception
  {
    Document doc = AcquireDocument(input);

    UmlModel model = new UmlModel();
    ModelBuilder mb = new ModelBuilder(model, doc);
    return mb.GetPackages();
  }

  private static Document AcquireDocument(String input) throws Exception
  {
    File file = new File(input);
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setNamespaceAware(true);
    DocumentBuilder db = dbf.newDocumentBuilder();
    return db.parse(file);
  }
}
