package autorest.astahxmlparser.umldatastructure;

import java.util.List;
import java.util.ArrayList;

public class UmlElement
{
	public String id;
	public String name;
	public UmlPackage parentPackage;
	public List<UmlStereotype> stereotypes;
	public List<UmlTag> tags;
	public UmlElement parent;

	public UmlElement()
	{
		this.id = "";
		this.name = "";
		this.parentPackage = new UmlPackage();
		this.stereotypes = new ArrayList<UmlStereotype>();
		this.tags = new ArrayList<UmlTag>();
		parent = null;
	}
}
