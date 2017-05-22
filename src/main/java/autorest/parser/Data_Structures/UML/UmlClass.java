package autorest.astahxmlparser.umldatastructure;

import java.util.List;
import java.util.ArrayList;

public class UmlClass extends UmlElement
{
	public List<UmlAttribute> attributes;
	public UmlAccessibility accessibility;

	public UmlClass()
	{
		super();
		this.attributes = new ArrayList<UmlAttribute>();
		this.accessibility = UmlAccessibility.publicaccess;
	}
}
