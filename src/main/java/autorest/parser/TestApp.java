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
    try {
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
      System.out.print("PACKAGES: [");
      String control = "";
      for(String s : packages)
      {
        System.out.print(control + "\'" + s + "\'");
        control = ",";
      }
      System.out.println("]");
    }
    }
    catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
