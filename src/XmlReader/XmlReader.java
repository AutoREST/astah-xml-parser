package astahxmlparser.xmlreader;

import astahxmlparser.umldatastructure;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlReader
{
  public static UmlModel ReadModel(String input)
  {
    Document doc = AcquireDocument(input);
  }

  private Document AcquireDocument(String input)
  {
    DocumentBuilderFactory dbf = null;
    DocumentBuilder builder = null;
    Document doc = null;

    try
    {
      factory = DocumentBuilderFactory.newInstance();
      builder = factory.newDocumentBuilder();
    }
    catch (ParserConfigurationException e)
    {
      e.printStackTrace();
    }

    try
    {
      doc = builder.parse(new InputSource(input));
    }
    catch (SAXException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    return doc;
  }
}
