package autorest.astahxmlparser.umldatastructure;

import java.util.List;
import java.util.ArrayList;

public class UmlAssociativeClass extends UmlElement
{
	public UmlAssociationEnd end1;
	public UmlAssociationEnd end2;
	public UmlAccessibility accessibility;

	public UmlAssociativeClass()
	{
		super();
		this.end1 = new UmlAssociationEnd();
		this.end2 = new UmlAssociationEnd();
		this.accessibility = UmlAccessibility.publicaccess;
	}
}
