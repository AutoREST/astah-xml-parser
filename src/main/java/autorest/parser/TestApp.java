import autorest.astahxmlparser.xmlreader.*;
import autorest.astahxmlparser.umldatastructure.*;

public class TestApp
{
  public static void main(String args[])
  {
    UmlModel model = XmlReader.ReadModel(args[0]);
    ModelWriter writer = new ModelWriter(model, args[1], "pack1");
    writer.Write();
  }
}
