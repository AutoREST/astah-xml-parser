package autorest.astahxmlparser.umldatastructure;

import java.util.List;
import java.util.ArrayList;
import java.lang.UnsupportedOperationException;

public class UmlAssociation extends UmlElement
{
	// ATTRIBUTES
	private UmlElement end1Element;
	private UmlElement end2Element;
	private UmlMultiplicity end1Multiplicity;
	private UmlMultiplicity end2Multiplicity;
	private UmlAggregation end1Aggregation;
	private UmlAggregation end2Aggregation;

	// CONSTRUCTOR
	public UmlAssociation()
	{
		throw new UnsupportedOperationException("TODO");
	}

	// GET METHODS
	public UmlElement getEnd1()
	{
		throw new UnsupportedOperationException("TODO");
	}
	public UmlElement getEnd2()
	{
		throw new UnsupportedOperationException("TODO");
	}
	public List<UmlElement> getEnds()
	{
		throw new UnsupportedOperationException("TODO");
	}
	public UmlElement getEndById(String id)
	{
		throw new UnsupportedOperationException("TODO");
	}
	public UmlElement getEndByName(String name)
	{
		throw new UnsupportedOperationException("TODO");
	}
	public UmlMultiplicity getMultiplicityEnd1()
	{
		throw new UnsupportedOperationException("TODO");
	}
	public UmlMultiplicity getMultiplicityEnd2()
	{
		throw new UnsupportedOperationException("TODO");
	}
	public List<UmlMultiplicity> getMulticities()
	{
		throw new UnsupportedOperationException("TODO");
	}
	public UmlMultiplicity getMultiplicityByEndId(String id)
	{
		throw new UnsupportedOperationException("TODO");
	}
	public UmlMultiplicity getMultiplicityByEndName(String id)
	{
		throw new UnsupportedOperationException("TODO");
	}
	public UmlAggregation getAggregationEnd1()
	{
		throw new UnsupportedOperationException("TODO");
	}
	public UmlAggregation getAggregationEnd2()
	{
		throw new UnsupportedOperationException("TODO");
	}
	public UmlAggregation getAggregationByEndId(String id)
	{
		throw new UnsupportedOperationException("TODO");
	}
	public UmlAggregation getAggregationByEndName(String name)
	{
		throw new UnsupportedOperationException("TODO");
	}
	public List<UmlAggregation> getAggregations()
	{
		throw new UnsupportedOperationException("TODO");
	}
}
