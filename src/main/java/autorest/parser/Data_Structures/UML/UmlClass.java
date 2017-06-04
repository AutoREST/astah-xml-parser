package autorest.astahxmlparser.umldatastructure;

import java.util.List;
import java.util.ArrayList;

public class UmlClass extends UmlElement
{
	public UmlAccessibility accessibility;

	public UmlClass()
	{
		super();
		this.accessibility = UmlAccessibility.publicaccess;
	}
}
