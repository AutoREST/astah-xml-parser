package autorest.astahxmlparser.umldatastructure;

import java.util.List;
import java.util.ArrayList;

public class UmlAssociation extends UmlElement
{
	public UmlAssociationEnd end1;
	public UmlAssociationEnd end2;

	public UmlAssociation()
	{
		super();
		this.end1 = new UmlAssociationEnd();
		this.end2 = new UmlAssociationEnd();
	}
}
