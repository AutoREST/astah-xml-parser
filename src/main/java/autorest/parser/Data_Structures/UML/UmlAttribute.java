package autorest.astahxmlparser.umldatastructure;

public class UmlAttribute extends UmlElement
{
	public String type;
	public UmlMultiplicity multiplicity;

	public UmlAttribute()
	{
		super();
		this.type = "";
		this.multiplicity = new UmlMultiplicity();
	}
}
