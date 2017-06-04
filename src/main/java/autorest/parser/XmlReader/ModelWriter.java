package autorest.astahxmlparser.xmlreader;

import autorest.astahxmlparser.umldatastructure.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.lang.System;
import java.util.List;
import java.util.ArrayList;

// such comments, much wow
public class ModelWriter
{
  public UmlModel model;
  public String path;
  // used to decide whether or not to include an element
  public String packageName;
  public FileWriter outputStream;
  // used to mark tabulation point
  public String tabs;

  // holds the elements that are to be included
  public List<UmlClass> validClasses;
  public List<UmlAssociativeClass> validAssClasses;
  // auxiliary list to hold attributes that need to be printed in the dependencies block
  public List<UmlAttribute> nonIdAttributes;

  // initialize the ModelWriter
  public ModelWriter(UmlModel model, String path, String packageName)
  {
    try
    {
      this.model = model;
      this.path = path;
      this.packageName = packageName;
      // tabulation point begins at the left edge of line
      this.tabs = "";
      this.outputStream = new FileWriter(path);
      validClasses = new ArrayList();
      validAssClasses = new ArrayList();
      nonIdAttributes = new ArrayList();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  // herpaderp
  public void Write()
  {
    try
    {
      // obvious enough?
      selectElements();

      // marks the beginning of the definitions block
      outputStream.write(tabs + "{" + System.lineSeparator());
      tabs += "\t";

      outputStream.write(tabs + "\"definitions\": {" + System.lineSeparator());
      tabs += "\t";

      // control variable for first and last items in foreach.
      String control = "";

      for(UmlClass c : validClasses)
      {
        outputStream.write(control);
        // prints classes first
        writeClass(c);
        control = "}," + System.lineSeparator();
      }

      // just close everything up
      while(tabs.length() > 0)
      {
        outputStream.write(tabs + "}" + System.lineSeparator());
        tabs = tabs.substring(0, tabs.length() - 1);
      }

      outputStream.write("}");
      outputStream.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  // just checks which elements are inside the wanted package and adds them to the lists
  private void selectElements()
  {
    for (UmlClass c : model.classes.values())
    {
      if(c.parentPackage.name.equals(packageName))
      {
        validClasses.add(c);
        createId(c);
      }
    }

    for (UmlAssociativeClass ac : model.associativeClasses.values())
    {
      if(ac.parentPackage.name.equals(packageName))
      {
        validAssClasses.add(ac);
        createAssId(ac);
      }
    }
  }

  // this creates an ID attribute if none is present
  private void createId(UmlClass c)
  {
    for(UmlAttribute t : c.attributes)
    {
      for(UmlStereotype st : t.stereotypes)
      {
        if(st.name.equals("id"))
        {
          return;
        }
      }
    }

    UmlAttribute aux = new UmlAttribute();
    aux.name = c.name + "_created_identifier";
    aux.type = "int";
    UmlStereotype idAux = new UmlStereotype();
    idAux.name = "id";
    aux.stereotypes.add(idAux);
    c.attributes.add(aux);
  }

  private void createAssId(UmlAssociativeClass c)
  {

  }

  // this writes down a single class into the Schema. Associative Classes decorate this method.
  private void writeClass(UmlClass c)
  {
    try
    {
      // these open up the class with the basics
      outputStream.write(tabs + "\"" + c.name + "\": {" + System.lineSeparator());
      tabs += "\t";

      outputStream.write(tabs + "\"type\": \"object\"," + System.lineSeparator());
      outputStream.write(tabs + "\"additionalProperties\": false," + System.lineSeparator());
      outputStream.write(tabs + "\"properties\": {" + System.lineSeparator());
      tabs += "\t";
      // auxiliary variable, controls whether it is the first or last item added in a foreach loop
      String control = "";

      // prints each of the basic attribute definitions
      for(UmlAttribute t : c.attributes)
      {
        outputStream.write(control);
        writeAttribute(t);
        tabs = tabs.substring(0, tabs.length() - 1);
        control = tabs + "}," + System.lineSeparator();
      }

      // checks for associated attributes
      for(UmlAssociation ass : model.associations.values())
      {
        if(ass.end1.endElement.name.equals(c.name))
        {
          if(ass.end2.endAggregation == UmlAggregation.association && ass.end2.endNavigability == UmlNavigability.navigable)
          {
            outputStream.write(control);
            createAssociationAttribute(ass.end2);
          }
        }
        else if(ass.end2.endElement.name.equals(c.name))
        {
          if(ass.end1.endAggregation == UmlAggregation.association && ass.end1.endNavigability == UmlNavigability.navigable)
          {
            outputStream.write(control);
            createAssociationAttribute(ass.end1);
          }
        }
      }

      // close the properties block
      outputStream.write(tabs + "}" + System.lineSeparator());
      tabs = tabs.substring(0, tabs.length() - 1);
      outputStream.write(tabs + "}," + System.lineSeparator());

      // open the dependencies block
      outputStream.write(tabs + "\"dependencies\": {" + System.lineSeparator());
      tabs += "\t";
      control = "";
      // gets the string of the identifier, and also fills up the nonIdAttributes list
      String identifier = getIdentifier(c);

      // prints each dependency line
      for(UmlAttribute t : nonIdAttributes)
      {
        // no tabs here, it's a line closure
        outputStream.write(control);
        outputStream.write(tabs + "\"" + t.name + "\": [ " + identifier + "]");
        control = "," + System.lineSeparator();
      }

      // close the dependencies block. no tabs in the first, line closure.
      outputStream.write(System.lineSeparator());
      tabs = tabs.substring(0, tabs.length() - 1);
      outputStream.write(tabs + "}," + System.lineSeparator());

      // open the required block
      outputStream.write(tabs + "\"required\": {" + System.lineSeparator());
      // i was too lazy to add a tab for one line
      outputStream.write(tabs + "\t[ ");
      control = "";

      // check if each attribute is mandatory or optional
      for(UmlAttribute t : c.attributes)
      {
        if(t.multiplicity.minimum > 0 && (t.multiplicity.maximum > 0 || t.multiplicity.maximum == -1))
        {
          outputStream.write(control + "\"" + t.name + "\"");
          control = ", ";
        }
      }
      // close line
      outputStream.write(" ]" + System.lineSeparator());

      outputStream.write(tabs + "}" + System.lineSeparator());
      // removes the last tab to put it back in line with parent method
      tabs = tabs.substring(0, tabs.length() - 1);
      // clean things up for the next class
      nonIdAttributes.clear();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  private void createAssociationAttribute(UmlAssociationEnd ass)
  {
    try
    {
      // 0..1 or 1..1
      if((ass.endMultiplicity.minimum == 1 && ass.endMultiplicity.maximum == 1)
      || (ass.endMultiplicity.minimum == 0 && ass.endMultiplicity.maximum == 1))
      {
        for(UmlAttribute t : ass.endElement.attributes)
        {
          for(UmlStereotype st : t.stereotypes)
          {
            if(st.name.equals("id"))
            {
              // this just makes a dummy attribute with the FK for the required and dependencies blocks
              UmlAttribute aux = new UmlAttribute();
              aux.name = ass.endElement.name;
              aux.multiplicity = ass.endMultiplicity;
              nonIdAttributes.add(aux);

              outputStream.write(tabs + "\"" + ass.endElement.name + "\": {" + System.lineSeparator());
              outputStream.write(tabs + "\t" + "\"$ref\": \"#/definitions/" + ass.endElement.name + "/properties/" + t.name + "\"" + System.lineSeparator());
            }
          }
        }
      }
      // 1..*, * or 0..*
      else
      {
        for(UmlAttribute t : ass.endElement.attributes)
        {
          for(UmlStereotype st : t.stereotypes)
          {
            if(st.name.equals("id"))
            {
              // this just makes a dummy attribute with the FK for the required and dependencies blocks
              UmlAttribute aux = new UmlAttribute();
              aux.name = ass.endElement.name;
              aux.multiplicity = ass.endMultiplicity;
              nonIdAttributes.add(aux);

              outputStream.write(tabs + "\"" + ass.endElement.name + "s\": {" + System.lineSeparator());
              tabs += "\t";
              outputStream.write(tabs + "\"type\": \"array\"" + System.lineSeparator());
              outputStream.write(tabs + "\"items\": {" + System.lineSeparator());
              outputStream.write(tabs + "\t" + "\"$ref\": \"#/definitions/" + ass.endElement.name + "/properties/" + t.name + "\"" + System.lineSeparator());
              outputStream.write(tabs + "}" + System.lineSeparator());
            }
          }
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  // writes a single attribute. implicit attributes go elseweyr.
  private void writeAttribute(UmlAttribute t)
  {
    try
    {
      // this is to keep track of the array "items" closure
      boolean arrayMark = false;

      outputStream.write(tabs + "\"" + t.name + "\": {" + System.lineSeparator());
      tabs += "\t";

      // normal attribute
      if(t.multiplicity.maximum == 1 || t.multiplicity.maximum == 0)
      {
        outputStream.write(tabs + "\"type\": " + getType(t));
      }

      // array attribute
      if(t.multiplicity.maximum > 1 || t.multiplicity.maximum == -1)
      {
        outputStream.write(tabs + "\"type\": \"array\"" + System.lineSeparator());
        outputStream.write(tabs + "\"items\":{" + System.lineSeparator());
        tabs += "\t";
        // no line closure here so that it can add a comma if it's a special type
        outputStream.write(tabs + "\"type\": " + getType(t));
        arrayMark = true;
      }

      // special types of attributes
      if(t.type.equals("byte"))
      {
        outputStream.write("," + System.lineSeparator());
        outputStream.write(tabs + "\"minimum\": 0," + System.lineSeparator());
        outputStream.write(tabs + "\"maximum\": 255" + System.lineSeparator());
      }
      else if(t.type.equals("char"))
      {
        outputStream.write("," + System.lineSeparator());
        outputStream.write(tabs + "\"maxLength\": 1" + System.lineSeparator());
      }
      else if(t.type.equals("Date"))
      {
        outputStream.write("," + System.lineSeparator());
        outputStream.write(tabs + "\"format\": \"date-time\"" + System.lineSeparator());
      }
      else
      {
        // it's not a special type so just close the line
        outputStream.write(System.lineSeparator());
      }

      // if it's an array, close the items block here
      if(arrayMark)
      {
        arrayMark = false;
        tabs = tabs.substring(0, tabs.length() - 1);
        outputStream.write(tabs + "}" + System.lineSeparator());
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  // just shifts from Java type to JSON type
  private String getType(UmlAttribute t)
  {
    switch(t.type)
    {
      case "int":
        return "\"integer\"";
      case "boolean":
        return "\"boolean\"";
      case "byte":
        return "\"integer\"";
      case "char":
        return "\"string\"";
      case "double":
        return "\"number\"";
      case "float":
        return "\"number\"";
      case "long":
        return "\"integer\"";
      case "short":
        return "\"integer\"";
      case "Date":
        return "\"string\"";
      case "object":
        return "\"object\"";
      case "String":
        return "\"string\"";
      default:
        return "integer";
    }
  }

  // gets the id string for dependencies, and also fills up the nonIdAttributes list
  private String getIdentifier(UmlClass c)
  {
    String identifier = "";
    for(UmlAttribute t : c.attributes)
    {
      nonIdAttributes.add(t);
      // too lazy to implement the equals method
      for(UmlStereotype st : t.stereotypes)
      {
        if(st.name.equals("id"))
        {
          identifier += "\"" + t.name + "\", ";
          // it adds them all, then removes if it's an id.
          nonIdAttributes.remove(t);
        }
      }
    }
    // this just removes the comma at the end of the string
    identifier = identifier.substring(0, identifier.length() - 2);
    return identifier;
  }
}
