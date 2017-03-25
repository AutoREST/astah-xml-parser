package astahxmlparser.xmlreader;

import astahxmlparser.umldatastructure;
import javax.xml.parsers;

public static Class XmlReader
{
  public static UmlModel ReadModel(String input)
  {
    File file = new File(input);
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = dbf.newDocumentBuilder();
    Document xmlDoc = builder.parse(file);
  }
}
