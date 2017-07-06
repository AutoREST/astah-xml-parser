package autorest.astahxmlparser.xmlreader;

import autorest.astahxmlparser.umldatastructure.*;
import autorest.astahxmlparser.CompilerDirectives;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
//import javax.xml.xpath.XPathConstants;
//import javax.xml.xpath.XPathExpression;
//import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

//THIS CODE IS WRITE-ONLY. DO NOT TRY TO GIVE IT MAINTENANCE, IT IS BAD.
public class ModelBuilder
{
  public UmlModel model;
  public Document doc;

  public ModelBuilder(UmlModel model, Document doc)
  {
    this.model = model;
    this.doc = doc;
  }

  public UmlModel Build()
  {
    //whole method inside try because fuck it that's why
    try
    {
      //trust me, it was much worse. you should be grateful I spared you
      fillPackages();
      fillStereotypes();
      fillTypes();
      fillClasses();
      //these methods HAVE TO BE IN ORDER. If you change the order it goes boom because of association end checking. model has to know something before it can match id.
      fillAssociativeClasses();
      fillAssociations();
      fillRealizations();
    }
    catch(Exception e)
    {
      System.out.println("ERROR: in reading the xml");
      e.printStackTrace();
    }

    return model;
  }

  public Set<String> GetPackages()
  {
    try
    {
      fillPackages();
    }
    catch(Exception e)
    {
      System.out.println("ERROR: in acquiring the packages");
      e.printStackTrace();
    }
    Set<String> result = new HashSet();
    for(UmlPackage p : this.model.packages.values())
    {
      result.add(p.name);
    }
    return result;
  }

  private UmlAccessibility accessLevelCheck(Element toCheck)
  {
    switch(toCheck.getAttribute("xmi.value"))
    {
      case "private":
        return UmlAccessibility.privateaccess;
      case "public":
        return UmlAccessibility.publicaccess;
      default:
        return UmlAccessibility.privateaccess;
    }
  }

  private void fillPackages()
  {
    if(CompilerDirectives.DEBUG)
    {
      System.out.println("------------------");
      System.out.println("PACKAGES:");
      System.out.println("------------------");
      System.out.println(" ");
    }

    NodeList result = doc.getElementsByTagName("UML:Package");
    for(int i = 0; i < result.getLength(); i++)
    {
      Element resultNode = (Element)result.item(i);
      if(resultNode.hasAttribute("xmi.id"))
      {
        UmlPackage packageCreation = new UmlPackage();
        packageCreation.id = resultNode.getAttribute("xmi.id");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("ID: " + packageCreation.id);
        }
        packageCreation.name = resultNode.getAttribute("name");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("Name: " + packageCreation.name);
          System.out.println(" ");
        }
        this.model.packages.put(packageCreation.id, packageCreation);
      }
    }

    //ughhhhh need to add the model package for our stupid example to work, ballsack
    result = doc.getElementsByTagName("UML:Model");
    for(int i = 0; i < result.getLength(); i++)
    {
      Element resultNode = (Element)result.item(i);
      if(resultNode.hasAttribute("xmi.id"))
      {
        UmlPackage packageCreation = new UmlPackage();
        packageCreation.id = resultNode.getAttribute("xmi.id");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("ID: " + packageCreation.id);
        }
        packageCreation.name = resultNode.getAttribute("name");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("Name: " + packageCreation.name);
          System.out.println(" ");
        }
        this.model.packages.put(packageCreation.id, packageCreation);
      }
    }
  }

  private void fillStereotypes()
  {
    if(CompilerDirectives.DEBUG)
    {
      System.out.println("------------------");
      System.out.println("STEREOTYPES:");
      System.out.println("------------------");
      System.out.println(" ");
    }

    NodeList result = doc.getElementsByTagName("UML:Stereotype");
    for(int i = 0; i < result.getLength(); i++)
    {
      Element resultNode = (Element)result.item(i);
      if(resultNode.hasAttribute("xmi.id"))
      {
        UmlStereotype stereotypeCreation = new UmlStereotype();
        stereotypeCreation.id = resultNode.getAttribute("xmi.id");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("ID: " + stereotypeCreation.id);
        }
        stereotypeCreation.name = resultNode.getAttribute("name");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("Name: " + stereotypeCreation.name);
          System.out.println(" ");
        }
        this.model.stereotypes.put(stereotypeCreation.id, stereotypeCreation);
      }
    }
  }

  private void fillTypes()
  {
    if(CompilerDirectives.DEBUG)
    {
      System.out.println("------------------");
      System.out.println("TYPES:");
      System.out.println("------------------");
      System.out.println(" ");
    }

    NodeList result = doc.getElementsByTagName("UML:Primitive");
    for(int i = 0; i < result.getLength(); i++)
    {
      Element resultNode = (Element)result.item(i);
      UmlType typeCreation = new UmlType();
      typeCreation.id = resultNode.getAttribute("xmi.id");
      if(CompilerDirectives.DEBUG)
      {
        System.out.println("ID: " + typeCreation.id);
      }
      typeCreation.name = resultNode.getAttribute("name");
      if(CompilerDirectives.DEBUG)
      {
        System.out.println("Name: " + typeCreation.name);
        System.out.println(" ");
      }
      this.model.types.put(typeCreation.id, typeCreation);
    }
    result = doc.getElementsByTagName("UML:Class");
    for(int i = 0; i < result.getLength(); i++)
    {
      Element resultNode = (Element)result.item(i);
      if(resultNode.getAttribute("name").equals("Date"))
      {
        UmlType typeCreation = new UmlType();
        typeCreation.id = resultNode.getAttribute("xmi.id");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("ID: " + typeCreation.id);
        }
        typeCreation.name = resultNode.getAttribute("name");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("Name: " + typeCreation.name);
          System.out.println(" ");
        }
        this.model.types.put(typeCreation.id, typeCreation);
      }
      if(resultNode.getAttribute("name").equals("String"))
      {
        UmlType typeCreation = new UmlType();
        typeCreation.id = resultNode.getAttribute("xmi.id");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("ID: " + typeCreation.id);
        }
        typeCreation.name = resultNode.getAttribute("name");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("Name: " + typeCreation.name);
          System.out.println(" ");
        }
        this.model.types.put(typeCreation.id, typeCreation);
      }
    }
  }

  private void fillClasses()
  {
    if(CompilerDirectives.DEBUG)
    {
      System.out.println("------------------");
      System.out.println("CLASSES:");
      System.out.println("------------------");
      System.out.println(" ");
    }

    NodeList result = doc.getElementsByTagName("UML:Class");
    for(int i = 0; i < result.getLength(); i++)
    {
      String namespaceId = getNamespace(result.item(i));
      //checking if the class being looked at exists in a known package, i.e. if it matters. if not, fuck that class, nobody likes her anyway.
      if(model.packages.get(namespaceId) != null)
      {
        Element resultNode = (Element)result.item(i);
        UmlClass classCreation = new UmlClass();
        classCreation.id = resultNode.getAttribute("xmi.id");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("ID: " + classCreation.id);
        }
        classCreation.name = resultNode.getAttribute("name");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("Name: " + classCreation.name);
        }
        //obvs the classes inside the whole "java" package will be here, but hey, this code is shit so i don't check for it. i just wanna make sure classes in the root aren't caught, because those assholes would cause me exceptions. ain't nobody got time for exception handling. i mean did you SEE the try-catch blocks in the main method of this class?
        classCreation.parentPackage = model.packages.get(namespaceId);
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("Package: " + classCreation.parentPackage.name);
        }
        NodeList resultChildren = result.item(i).getChildNodes();
        //this part is still checking class stuff, so it stays here. yeah yeah, looping fors in the same method, yada yada. tough.
        for(int j = 0; j < resultChildren.getLength(); j++)
        {
          if(resultChildren.item(j).getNodeType() == Node.ELEMENT_NODE)
          {
            Element resultChild = (Element)resultChildren.item(j);
            if(resultChild.getTagName().equals("UML:ModelElement.visibility"))
            {
              //holy balls i had forgotten i made this method. so classy.
              classCreation.accessibility = accessLevelCheck(resultChild);
            }
            if(resultChild.getTagName().equals("UML:Classifier.feature"))
            {
              //best variable name ever. ihavenoideawhatimdoing.gif
              NodeList resultChildren2 = resultChildren.item(j).getChildNodes();
              for(int k = 0; k < resultChildren2.getLength(); k++)
              {
                if(resultChildren2.item(k).getNodeType() == Node.ELEMENT_NODE)
                {
                  Element resultChild2 = (Element)resultChildren2.item(k);
                  if(resultChild2.getTagName().equals("UML:Attribute"))
                  {
                    //OMG ENCAPSULATION. i debated making another three loops in here, just for teh lulz. but there are lines even a tired, pissed off genius will not cross. *cougharrogantfuckingprogrammercough*
                    classCreation.attributes.add(createAttribute(resultChildren2.item(k)));
                  }
                }
              }
            }
          }
        }
        if(CompilerDirectives.DEBUG)
        {
          System.out.println(" ");
        }
        this.model.classes.put(classCreation.id, classCreation);
      }
    }
  }

  //son of a ballsmith, the fact this method exists is sinful. half of this is copypasta of fillClasses but damned if i'm gonna fix it.
  private void fillAssociativeClasses() throws Exception
  {
    if(CompilerDirectives.DEBUG)
    {
      System.out.println("------------------");
      System.out.println("ASSOCIATIVE CLASSES:");
      System.out.println("------------------");
      System.out.println(" ");
    }

    NodeList result = doc.getElementsByTagName("UML:AssociationClass");
    for(int i = 0; i < result.getLength(); i++)
    {
      String namespaceId = getNamespace(result.item(i));
      //checking if the class being looked at exists in a known package, i.e. if it matters. if not, fuck that class, nobody likes her anyway.
      //yes i meant it when i said copypasta, sue me.
      if(model.packages.get(namespaceId) != null)
      {
        Element resultNode = (Element)result.item(i);
        //didn't even change the variable name. makes sense? nope. but easier, and i'm lazy.
        UmlAssociativeClass classCreation = new UmlAssociativeClass();
        classCreation.id = resultNode.getAttribute("xmi.id");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("ID: " + classCreation.id);
        }
        classCreation.name = resultNode.getAttribute("name");
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("Name: " + classCreation.name);
        }
        //obvs the classes inside the whole "java" package will be here, but hey, this code is shit so i don't check for it. i just wanna make sure classes in the root aren't caught, because those assholes would cause me exceptions. ain't nobody got time for exception handling. i mean did you SEE the try-catch blocks in the main method of this class?
        classCreation.parentPackage = model.packages.get(namespaceId);
        if(CompilerDirectives.DEBUG)
        {
          System.out.println("Package: " + classCreation.parentPackage.name);
        }
        NodeList resultChildren = result.item(i).getChildNodes();
        //this part is still checking class stuff, so it stays here. yeah yeah, looping fors in the same method, yada yada. tough.
        for(int j = 0; j < resultChildren.getLength(); j++)
        {
          if(resultChildren.item(j).getNodeType() == Node.ELEMENT_NODE)
          {
            Element resultChild = (Element)resultChildren.item(j);
            if(resultChild.getTagName().equals("UML:ModelElement.visibility"))
            {
              //holy balls i had forgotten i made this method. so classy.
              //now that I think about it, i don't even know if we use this in the second pass. ah well.
              classCreation.accessibility = accessLevelCheck(resultChild);
            }
            if(resultChild.getTagName().equals("UML:Classifier.feature"))
            {
              //best variable name ever. ihavenoideawhatimdoing.gif
              NodeList resultChildren2 = resultChildren.item(j).getChildNodes();
              for(int k = 0; k < resultChildren2.getLength(); k++)
              {
                if(resultChildren2.item(k).getNodeType() == Node.ELEMENT_NODE)
                {
                  Element resultChild2 = (Element)resultChildren2.item(k);
                  if(resultChild2.getTagName().equals("UML:Attribute"))
                  {
                    //OMG ENCAPSULATION. i debated making another three loops in here, just for teh lulz. but there are lines even a tired, pissed off genius will not cross. *cougharrogantfuckingprogrammercough*
                    classCreation.attributes.add(createAttribute(resultChildren2.item(k)));
                  }
                }
              }
            }

            //--------------------------
            //UP TO HERE, IT'S BASICALLY COPYPASTA
            //--------------------------

            if(resultChild.getTagName().equals("UML:Association.connection"))
            {
              //this reads an "association end". it's probably one of the best things in this entire code. ps.: OR WOULD BE IF THE DOM LIBRARY WASN'T A PIECE OF SHIT.
              NodeList listness = resultChildren.item(j).getChildNodes();
              int turn = 1;
              for(int k = 0; k < listness.getLength(); k++)
              {
                if(listness.item(k).getNodeType() == Node.ELEMENT_NODE)
                {
                  if(turn == 1)
                  {
                    if(CompilerDirectives.DEBUG)
                    {
                      System.out.println("END1: ");
                    }
                    classCreation.end1 = readAssociationEnd(listness.item(k));
                    turn++;
                  }
                  else
                  {
                    if(CompilerDirectives.DEBUG)
                    {
                      System.out.println("END2: ");
                    }
                    classCreation.end2 = readAssociationEnd(listness.item(k));
                  }
                }
              }
            }
          }
        }
        this.model.associativeClasses.put(classCreation.id, classCreation);
        UmlAssociation assClassClone = new UmlAssociation();
        assClassClone.end1 = classCreation.end1;
        assClassClone.end2 = classCreation.end2;
        this.model.associations.put(classCreation.id, assClassClone);
        if(CompilerDirectives.DEBUG)
        {
          System.out.println(" ");
        }
      }
    }
  }

  //this one is kinda iffy 'cause association definitions are all over the place, but i'll try to grab them from the primary definitions
  private void fillAssociations() throws Exception
  {
    if(CompilerDirectives.DEBUG)
    {
      System.out.println("------------------");
      System.out.println("ASSOCIATIONS:");
      System.out.println("------------------");
      System.out.println(" ");
    }

    NodeList result = doc.getElementsByTagName("UML:Association");
    for(int i = 0; i < result.getLength(); i++)
    {
      if(result.item(i).getNodeType() == Node.ELEMENT_NODE)
      {
        Element resultElement = (Element)result.item(i);
        if(resultElement.hasAttribute("xmi.id"))
        {
          UmlAssociation associationCreation = new UmlAssociation();
          associationCreation.id = resultElement.getAttribute("xmi.id");
          if(CompilerDirectives.DEBUG)
          {
            System.out.println("ID: " + associationCreation.id);
          }
          NodeList resultChildren = result.item(i).getChildNodes();
          for(int j = 0; j < resultChildren.getLength(); j++)
          {
            if(resultChildren.item(j).getNodeType() == Node.ELEMENT_NODE)
            {
              // this variable is irrelevant, it's just used to step inside. ps.: half the bloody variables are useless because THE DOM LIBRARY ISN'T A PROPER BLOODY TREE.
              Element thingamabob = (Element)resultChildren.item(j);
              if(thingamabob.getTagName().equals("UML:Association.connection"))
              {
                //------------------------
                //COPYPASTA FROM ASSOCIATIVECLASSES
                //------------------------
                //this reads an "association end". it's probably one of the best things in this entire code. ps.: OR WOULD BE IF THE DOM LIBRARY WASN'T A PIECE OF SHIT.
                NodeList listness = resultChildren.item(j).getChildNodes();
                int turn = 1;
                for(int k = 0; k < listness.getLength(); k++)
                {
                  if(listness.item(k).getNodeType() == Node.ELEMENT_NODE)
                  {
                    if(turn == 1)
                    {
                      if(CompilerDirectives.DEBUG)
                      {
                        System.out.println("END1: ");
                      }
                      associationCreation.end1 = readAssociationEnd(listness.item(k));
                      turn++;
                    }
                    else
                    {
                      if(CompilerDirectives.DEBUG)
                      {
                        System.out.println("END2: ");
                      }
                      associationCreation.end2 = readAssociationEnd(listness.item(k));
                    }
                  }
                }
              }
            }
          }
          this.model.associations.put(associationCreation.id, associationCreation);
          if(CompilerDirectives.DEBUG)
          {
            System.out.println(" ");
          }
        }
      }
    }
  }

  private void fillRealizations()
  {
    if(CompilerDirectives.DEBUG)
    {
      System.out.println("------------------");
      System.out.println("REALIZATIONS:");
      System.out.println("------------------");
      System.out.println(" ");
    }

    NodeList result = doc.getElementsByTagName("UML:Generalization");
    UmlElement parent = null;
    UmlElement child = null;
    for(int i = 0; i < result.getLength(); i++)
    {
      if(result.item(i).getNodeType() == Node.ELEMENT_NODE)
      {
        Element resultElement = (Element)result.item(i);
        if(resultElement.hasAttribute("xmi.id"))
        {
          NodeList resultChildren = result.item(i).getChildNodes();
          for(int j = 0; j < resultChildren.getLength(); j++)
          {
            if(resultChildren.item(j).getNodeType() == Node.ELEMENT_NODE)
            {
              Element resultElement2 = (Element)resultChildren.item(j);

              if(resultElement2.getTagName().equals("UML:Generalization.child"))
              {
                NodeList listzer = resultChildren.item(j).getChildNodes();
                for(int k = 0; k < listzer.getLength(); k++)
                {
                  if(listzer.item(k).getNodeType() == Node.ELEMENT_NODE)
                  {
                    Element whatIActuallyWantGoddamnit = (Element)listzer.item(k);
                    child = model.classes.get(whatIActuallyWantGoddamnit.getAttribute("xmi.idref"));
                    if(child == null)
                    {
                      child = model.associativeClasses.get(whatIActuallyWantGoddamnit.getAttribute("xmi.idref"));
                    }
                  }
                }
              }

              if(resultElement2.getTagName().equals("UML:Generalization.parent"))
              {
                NodeList listzer = resultChildren.item(j).getChildNodes();
                for(int k = 0; k < listzer.getLength(); k++)
                {
                  if(listzer.item(k).getNodeType() == Node.ELEMENT_NODE)
                  {
                    Element whatIActuallyWantGoddamnit = (Element)listzer.item(k);
                    parent = model.classes.get(whatIActuallyWantGoddamnit.getAttribute("xmi.idref"));
                    if(parent == null)
                    {
                      parent = model.associativeClasses.get(whatIActuallyWantGoddamnit.getAttribute("xmi.idref"));
                    }
                  }
                }
              }
            }
          }
          child.parent = parent;
          if(CompilerDirectives.DEBUG)
          {
            System.out.println("Parent: " + parent.name);
            System.out.println("Child: " + child.name);
            System.out.println(" ");
          }
        }
      }
    }
  }

  private UmlAssociationEnd readAssociationEnd(Node nodesy) throws Exception
  {
    NodeList resultChildren = nodesy.getChildNodes();
    UmlAssociationEnd endsy = new UmlAssociationEnd();
    Element elementNodesy = (Element)nodesy;

    // checking if this is an actual End, or a reference to an End
    if(elementNodesy.hasAttribute("xmi.idref"))
    {
      //XPathFactory xpf = XPathFactory.newInstance();
      //XPath xpath = xpf.newXPath();
      //String assPath = "//UML:AssociationEnd[@xmi.id=\"" + elementNodesy.getAttribute("xmi.idref") + "\"]";
      //XPathExpression expr = xpath.compile(assPath);

      //Object myObj = expr.evaluate(doc, XPathConstants.NODESET);
      //NodeList thingamajig = (NodeList)myObj;
      //System.out.println("testing " + thingamajig.getLength());
      //nodesy = thingamajig.item(0);
      //elementNodesy = (Element)nodesy;
      //resultChildren = nodesy.getChildNodes();

      NodeList thingamajang = doc.getElementsByTagName("UML:AssociationEnd");
      for(int i = 0; i < thingamajang.getLength(); i++)
      {
        Node noder = thingamajang.item(i);
        Element noderElement = (Element)noder;
        if(noderElement.getAttribute("xmi.id").equals(elementNodesy.getAttribute("xmi.idref")))
        {
          nodesy = noder;
          elementNodesy = noderElement;
          resultChildren = nodesy.getChildNodes();
          break;
        }
      }
    }

    switch(elementNodesy.getAttribute("aggregation"))
    {
      case "aggregate":
        endsy.endAggregation = UmlAggregation.aggregation;
        break;
      case "composite":
        endsy.endAggregation = UmlAggregation.composition;
        break;
      case "none":
        endsy.endAggregation = UmlAggregation.association;
        break;
    }
    switch(elementNodesy.getAttribute("navigableType"))
    {
      case "unspecified navigable":
        endsy.endNavigability = UmlNavigability.undefined;
        break;
      case "navigable":
        endsy.endNavigability = UmlNavigability.navigable;
        break;
      case "non navigable":
        endsy.endNavigability = UmlNavigability.nonNavigable;
        break;
    }

    for(int i = 0; i < resultChildren.getLength(); i++)
    {
      if(resultChildren.item(i).getNodeType() == Node.ELEMENT_NODE)
      {
        Element resultChild = (Element)resultChildren.item(i);
        //checking the endElement
        if(resultChild.getTagName().equals("UML:AssociationEnd.participant"))
        {
          NodeList listriggle = resultChildren.item(i).getChildNodes();
          for(int j = 0; j < listriggle.getLength(); j++)
          {
            if(listriggle.item(j).getNodeType() == Node.ELEMENT_NODE)
            {
              Element endHolder = (Element)listriggle.item(j);
              UmlElement endElement;
              if((endElement = this.model.classes.get(endHolder.getAttribute("xmi.idref"))) != null)
              {
                endsy.endElement = endElement;
                if(CompilerDirectives.DEBUG)
                {
                  System.out.println("\tName: " + endsy.endElement.name);
                }
              }
              else
              {
                //now this is tricky. if the element is NOT known to the model at this point, the model's most likely fubar, so might as well set it on fire. so i'mma set it on fire.
                if(CompilerDirectives.DEBUG)
                {
                  System.out.println("ASSOCIATION END DOESN'T EXIST");
                }
                throw new Exception("ASSOCIATION END DOESN'T EXIST");
              }
            }
          }
        }
        //copypasta from createAttribute. this code could be so much prettier if i just had more time :(
        if(resultChild.getTagName().equals("UML:StructuralFeature.multiplicity"))
        {
          //this bib is an abomination and an affront to mankind.
          NodeList listsy1 = resultChildren.item(i).getChildNodes();
          for(int j = 0; j < listsy1.getLength(); j++)
          {
            if(listsy1.item(j).getNodeType() == Node.ELEMENT_NODE)
            {
              Element thingsy1 = (Element)listsy1.item(j);
              if(thingsy1.getTagName().equals("UML:Multiplicity"))
              {
                NodeList listsy2 = listsy1.item(j).getChildNodes();
                for(int k = 0; k < listsy2.getLength(); k++)
                {
                  if(listsy2.item(k).getNodeType() == Node.ELEMENT_NODE)
                  {
                    Element thingsy2 = (Element)listsy2.item(k);
                    if(thingsy2.getTagName().equals("UML:Multiplicity.range"))
                    {
                      NodeList listsy3 = listsy2.item(k).getChildNodes();
                      //ohgodwhy
                      for(int l = 0; l < listsy3.getLength(); l++)
                      {
                        if(listsy3.item(l).getNodeType() == Node.ELEMENT_NODE)
                        {
                          Element multiplicityElement = (Element)listsy3.item(l);
                          if(multiplicityElement.getTagName().equals("UML:MultiplicityRange"))
                          {
                            //i was gonna whine about them using -1 for * but that's actually pretty clever, i'mma do it too.
                            if(multiplicityElement.hasAttribute("lower"))
                            {
                              endsy.endMultiplicity.minimum = Integer.parseInt(multiplicityElement.getAttribute("lower"));
                              if(CompilerDirectives.DEBUG)
                              {
                                System.out.println("\tMinimum: " + endsy.endMultiplicity.minimum);
                              }
                            }
                            if(multiplicityElement.hasAttribute("upper"))
                            {
                              endsy.endMultiplicity.maximum = Integer.parseInt(multiplicityElement.getAttribute("upper"));
                              if(CompilerDirectives.DEBUG)
                              {
                                System.out.println("\tMaximum: " + endsy.endMultiplicity.maximum);
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    return endsy;
  }

  private UmlAttribute createAttribute(Node parent)
  {
    if(CompilerDirectives.DEBUG)
    {
      System.out.println("\tATTRIBUTE: ");
    }
    Element attributeElement = (Element)parent;
    UmlAttribute attributeCreation = new UmlAttribute();
    attributeCreation.id = attributeElement.getAttribute("xmi.id");
    if(CompilerDirectives.DEBUG)
    {
      System.out.println("\tID: " + attributeCreation.id);
    }
    attributeCreation.name = attributeElement.getAttribute("name");
    if(CompilerDirectives.DEBUG)
    {
      System.out.println("\tName: " + attributeCreation.name);
    }

    //HERE WE GO AGAIN
    NodeList attributeChildren = parent.getChildNodes();
    for(int i = 0; i < attributeChildren.getLength(); i++)
    {
      if(attributeChildren.item(i).getNodeType() == Node.ELEMENT_NODE)
      {
        //bam, more amazing names in endless loops
        Element attributeChild = (Element)attributeChildren.item(i);
        //----------------------
        //STEREOTYPE BLOCK
        //----------------------

        //cause this got confusing
        if(attributeChild.getTagName().equals("UML:ModelElement.stereotype"))
        {
          NodeList attributeStereotypes = attributeChildren.item(i).getChildNodes();
          UmlStereotype attributeStereotype;
          for(int j = 0; j < attributeStereotypes.getLength(); j++)
          {
            if(attributeStereotypes.item(j).getNodeType() == Node.ELEMENT_NODE)
            {
              Element attributeStereotypeElement = (Element)attributeStereotypes.item(j);
              //this one is cool af
              if((attributeStereotype = model.stereotypes.get(attributeStereotypeElement.getAttribute("xmi.idref"))) != null)
              {
                attributeCreation.stereotypes.add(attributeStereotype);
                if(CompilerDirectives.DEBUG)
                {
                  System.out.println("\tStereotype: " + attributeStereotype.name);
                }
              }
            }
          }
        }

        if(attributeChild.getTagName().equals("UML:StructuralFeature.multiplicity"))
        {
          //this bib is an abomination and an affront to mankind.
          NodeList listsy1 = attributeChildren.item(i).getChildNodes();
          for(int j = 0; j < listsy1.getLength(); j++)
          {
            if(listsy1.item(j).getNodeType() == Node.ELEMENT_NODE)
            {
              Element thingsy1 = (Element)listsy1.item(j);
              if(thingsy1.getTagName().equals("UML:Multiplicity"))
              {
                NodeList listsy2 = listsy1.item(j).getChildNodes();
                for(int k = 0; k < listsy2.getLength(); k++)
                {
                  if(listsy2.item(k).getNodeType() == Node.ELEMENT_NODE)
                  {
                    Element thingsy2 = (Element)listsy2.item(k);
                    if(thingsy2.getTagName().equals("UML:Multiplicity.range"))
                    {
                      NodeList listsy3 = listsy2.item(k).getChildNodes();
                      //ohgodwhy
                      for(int l = 0; l < listsy3.getLength(); l++)
                      {
                        if(listsy3.item(l).getNodeType() == Node.ELEMENT_NODE)
                        {
                          Element multiplicityElement = (Element)listsy3.item(l);
                          if(multiplicityElement.getTagName().equals("UML:MultiplicityRange"))
                          {
                            //i was gonna whine about them using -1 for * but that's actually pretty clever, i'mma do it too.
                            attributeCreation.multiplicity.minimum = Integer.parseInt(multiplicityElement.getAttribute("lower"));
                            if(CompilerDirectives.DEBUG)
                            {
                              System.out.println("\tMinimum: " + attributeCreation.multiplicity.minimum);
                            }
                            attributeCreation.multiplicity.maximum = Integer.parseInt(multiplicityElement.getAttribute("upper"));
                            if(CompilerDirectives.DEBUG)
                            {
                              System.out.println("\tMaximum: " + attributeCreation.multiplicity.maximum);
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }

        if(attributeChild.getTagName().equals("UML:StructuralFeature.type"))
        {
          NodeList attributeTypeCandidates = attributeChildren.item(i).getChildNodes();
          for(int j = 0; j < attributeTypeCandidates.getLength(); j++)
          {
            if(attributeTypeCandidates.item(j).getNodeType() == Node.ELEMENT_NODE)
            {
              Element typeElement = (Element)attributeTypeCandidates.item(j);
              //another cool one, this assignment within condition thing is nifty
              UmlType attributeType;
              if((attributeType = model.types.get(typeElement.getAttribute("xmi.idref"))) != null)
              {
                attributeCreation.type = attributeType.name;
              }
              //o.m.f.g. AN ELSE CLAUSE!!!! HOLY BALLS!!!!!!
              else
              {
                attributeCreation.type = "object";
              }
              if(CompilerDirectives.DEBUG)
              {
                System.out.println("\tType: " + attributeCreation.type);
              }
            }
          }
        }
      }
    }

    //can't believe this method is finally done. my eyes hurt. my brain hurts. my soul hurts. i'm going to hell for this code.
    return attributeCreation;
  }

  //i couldn't find a decent tree-walk in the API so I made one. ps.: the api for dom is awful. whoever wrote that should be fired. in a fire. pps: not that this method is any good, but goddamn man how can they mess up a tree. they should call greenpeace on Oracle, those fuckers are evil.
  private String getNamespace(Node nodesy)
  {
    NodeList result = nodesy.getChildNodes();
    for(int i = 0; i < result.getLength(); i++)
    {
      //This library is horrible. HORRIBLE. Whoever documented it needs to be killed. It is necessary. Humanity cannot continue to breathe easy so long as this monster lives.
      if(result.item(i).getNodeType() == Node.ELEMENT_NODE)
      {
        Element resultChild = (Element)result.item(i);
        if(resultChild.getTagName().equals("UML:ModelElement.namespace"))
        {
          NodeList namespaceCandidates = result.item(i).getChildNodes();
          for(int j = 0; j < namespaceCandidates.getLength(); j++)
          {
            if(namespaceCandidates.item(j).getNodeType() == Node.ELEMENT_NODE)
            {
              Element namespace = (Element)namespaceCandidates.item(j);
              return namespace.getAttribute("xmi.idref");
            }
          }
        }
      }
    }
    //if none is found, the result doesn't matter anyway, so whatevs
    return "sbangles";
  }
}
