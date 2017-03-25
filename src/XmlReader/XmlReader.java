package astahxmlparser.xmlreader;

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
