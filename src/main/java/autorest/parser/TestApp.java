import autorest.astahxmlparser.xmlreader.*;
import autorest.astahxmlparser.umldatastructure.*;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.io.File;
import java.util.Set;
import java.lang.System;

public class TestApp
{
  public static void main(String args[])
  {
    if(args.length > 2)
    {
      UmlModel model = XmlReader.ReadModel(args[0]);
      ModelWriter writer = new ModelWriter(model, args[1], args[2]);
      writer.Write();
    }
    else if(args.length > 1)
    {
      UmlModel model = XmlReader.ReadModel(args[0]);
      ModelWriter writer = new ModelWriter(model, args[1], "pack1");
      writer.Write();
    }
    else
    {
      Set<String> packages = XmlReader.AcquirePackages(args[0]);
      try
      {
        Writer outputStream = new FileWriter("packages.txt");
        for(String s : packages)
        {
          outputStream.write(s + System.lineSeparator());
        }
        outputStream.close();
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
