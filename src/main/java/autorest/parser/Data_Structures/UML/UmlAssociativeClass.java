package autorest.astahxmlparser.umldatastructure;

import java.util.List;
import java.util.ArrayList;

public class UmlAssociativeClass extends UmlElement
{
	public UmlAssociationEnd end1;
	public UmlAssociationEnd end2;
	public List<UmlAttribute> attributes;
	public UmlAccessibility accessibility;

	public UmlAssociativeClass()
	{
		super();
		this.end1 = new UmlAssociationEnd();
		this.end2 = new UmlAssociationEnd();
		this.attributes = new ArrayList<UmlAttribute>();
		this.accessibility = UmlAccessibility.publicaccess;
	}
}
