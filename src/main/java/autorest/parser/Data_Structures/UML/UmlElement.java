package autorest.astahxmlparser.umldatastructure;

import java.util.List;


public abstract class UmlElement implements Serializable
{
	private String id;
	private String name;
	private List<UmlStereotype> stereotypes;
	private List<UmlTag> tags;
}
