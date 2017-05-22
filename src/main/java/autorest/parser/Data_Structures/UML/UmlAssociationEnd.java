package autorest.astahxmlparser.umldatastructure;

//this class is nifty.
public class UmlAssociationEnd
{
  public UmlElement endElement;
	public UmlMultiplicity endMultiplicity;
	public UmlAggregation endAggregation;
	public UmlNavigability endNavigability;

  public UmlAssociationEnd()
  {
    this.endElement = new UmlElement();
    this.endMultiplicity = new UmlMultiplicity();
    this.endAggregation = UmlAggregation.association;
    this.endNavigability = UmlNavigability.undefined;
  }
}
