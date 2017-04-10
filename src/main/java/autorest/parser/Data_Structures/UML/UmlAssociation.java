package autorest.astahxmlparser.umldatastructure;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.lang.UnsupportedOperationException;
import autorest.Preprocessor;
import autorest.util.DeepCopy;

/**
 * Class representing an association between two UML elements.
 * <p>
 * An <code>UmlAssociation</code> is composed of the data regarding the two ends of the association, referred to as <code>end1</code> and <code>end2</code>. Though it extends {@link #autorest.astahxmlparser.umldatastructure.UmlElement}, it will typically not have a {@link #autorest.astahxmlparser.umldatastructure.UmlElement.name}.
 * <p>
 * DEVNOTES - Methods must be implemented, architecture is incomplete.
 *
 * @author 			Marcelo Schmitt Laser
 * @since				0.1.1a
 * @see					autorest.astahxmlparser.umldatastructure.UmlElement
 * @see					autorest.Preprocessor.FULLACCESS
 */
public class UmlAssociation extends UmlElement implements Serializable
{
	// FIELDS
	/**
	 * A reference to the {@link #autorest.astahxmlparser.umldatastructure.UmlElement} on <code>end1</code>, typically a {@link #autorest.astahxmlparser.umldatastructure.UmlClass}.
	 */
	private UmlElement end1Element;
	/**
	 * A reference to the {@link #autorest.astahxmlparser.umldatastructure.UmlElement} on <code>end2</code>, typically a {@link #autorest.astahxmlparser.umldatastructure.UmlClass}.
	 */
	private UmlElement end2Element;
	/**
	 * The multiplicity of <code>end1</code>.
	 */
	private UmlMultiplicity end1Multiplicity;
	/**
	 * The multiplicity of <code>end2</code>.
	 */
	private UmlMultiplicity end2Multiplicity;
	/**
	 * The aggregation type of <code>end1</code>.
	 */
	private UmlAggregation end1Aggregation;
	/**
	 * The aggregation type of <code>end2</code>.
	 */
	private UmlAggregation end2Aggregation;
	/**
	 * The navigability of <code>end1</code>.
	 */
	 private UmlNavigability end1Navigability;
	 /**
 	 * The navigability of <code>end2</code>.
 	 */
 	 private UmlNavigability end2Navigability;

	// DEFAULT CONSTRUCTORS
	/**
	 * Default constructor of <code>UmlAssociation</code>.
	 * <p>
	 * This constructor should be used in the majority of situations.
	 *
	 * @since 	0.1.2a
	 * @see 		autorest.astahxmlparser.umldatastructure.UmlElement
	 * @see			autorest.astahxmlparser.umldatastructure.UmlMultiplicity
	 * @see			autorest.astahxmlparser.umldatastructure.UmlAggregation
	 * @see			autorest.astahxmlparser.umldatastructure.UmlNavigability
	 * @param		id Id of this <code>association</code>
	 * @param		end1Element <code>end1</code> element, typically a {@link #autorest.astahxmlparser.umldatastructure.UmlClass}
	 * @param		end2Element <code>end2</code> element, typically a {@link #autorest.astahxmlparser.umldatastructure.UmlClass}
	 * @param		end1Multiplicity {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object, referrent to <code>end1</code>
	 * @param		end2Multiplicity {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object, referrent to <code>end2</code>
	 * @param		end1Aggregation {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object, referrent to <code>end1</code>
	 * @param		end2Aggregation {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object, referrent to <code>end2</code>
	 * @param		end1Navigability {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object, referrent to <code>end1</code>
	 * @param		end2Navigability {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object, referrent to <code>end2</code>
	 */
	public UmlAssociation(String id, UmlElement end1Element, UmlElement end2Element, UmlMultiplicity end1Multiplicity, UmlMultiplicity end2Multiplicity, UmlAggregation end1Aggregation, UmlAggregation end2Aggregation, UmlNavigability end1Navigability, UmlNavigability end2Navigability)
	{
		BuildIt(id, end1Element, end2Element, end1Multiplicity, end2Multiplicity, end1Aggregation, end2Aggregation, end1Navigability, end2Navigability);
	}
	/**
	 * Default constructor of <code>UmlAssociation</code>.
	 * <p>
	 * This constructor sets both <code>end</code> multiplicities to <code>1..1</code>, aggregations to <code>association</code> and navigabilities to <code>navigable</code>.
	 *
	 * @since 	0.1.2a
	 * @see 		autorest.astahxmlparser.umldatastructure.UmlElement
	 * @see			autorest.astahxmlparser.umldatastructure.UmlMultiplicity
	 * @see			autorest.astahxmlparser.umldatastructure.UmlAggregation
	 * @see			autorest.astahxmlparser.umldatastructure.UmlNavigability
	 * @param		id Id of this <code>association</code>
	 * @param		end1Element <code>end1</code> element, typically a {@link #autorest.astahxmlparser.umldatastructure.UmlClass}
	 * @param		end2Element <code>end2</code> element, typically a {@link #autorest.astahxmlparser.umldatastructure.UmlClass}
	 */
	 public UmlAssociation(String id, UmlElement end1Element, UmlElement end2Element)
	 {
		 BuildIt(id, end1Element, end2Element, new UmlMultiplicity(1,1), new UmlMultiplicity(1,1), UmlAggregation.association, UmlAggregation.association, UmlNavigability.navigable, UmlNavigability.navigable);
	 }

	// FULLACCESS CONSTRUCTORS
	//#FULLACCESS
	/**
	 * <code>FULLACCESS</code> constructor of <code>UmlAssociation</code>.
	 * <p>
	 * Initializes all fields as null.
	 *
	 * @since 	0.1.2a
	 */
	public UmlAssociation()
	{
		BuildIt(null, null, null, null, null, null, null, null, null);
	}
	/**
	 * <code>FULLACCESS</code> Constructor of <code>UmlAssociation</code> based on ID.
	 * <p>
	 * This constructor is useful for when an <code>association</code> is initialized before its <code>end</code> elements. New {@link #autorest.astahxmlparser.umldatastructure.UmlClass} objects are created containing default names, and these can later be replaced as the IDs are resolved. This constructor does NOT resolve the <code>end</code> IDs automatically.
	 *
	 * @since 	0.1.2a
	 * @see 		autorest.astahxmlparser.umldatastructure.UmlElement
	 * @see			autorest.astahxmlparser.umldatastructure.UmlMultiplicity
	 * @see			autorest.astahxmlparser.umldatastructure.UmlAggregation
	 * @see			autorest.astahxmlparser.umldatastructure.UmlNavigability
	 * @param		id Id of this <code>association</code>
	 * @param		end1Id Id of the <code>end1</code> element
	 * @param		end2Id Id of the <code>end2</code> element
	 * @param		end1Multiplicity {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object, referrent to <code>end1</code>
	 * @param		end2Multiplicity {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object, referrent to <code>end2</code>
	 * @param		end1Aggregation {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object, referrent to <code>end1</code>
	 * @param		end2Aggregation {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object, referrent to <code>end2</code>
	 * @param		end1Navigability {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object, referrent to <code>end1</code>
	 * @param		end2Navigability {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object, referrent to <code>end2</code>
	 */
	public UmlAssociation(String id, String end1Id, String end2Id, UmlMultiplicity end1Multiplicity, UmlMultiplicity end2Multiplicity, UmlAggregation end1Aggregation, UmlAggregation end2Aggregation, UmlNavigability end1Navigability, UmlNavigability end2Navigability)
	{
		BuildIt(id, new UmlElement(end1Id), new UmlElement(end2Id), end1Multiplicity, end2Multiplicity, end1Aggregation, end2Aggregation, end1Navigability, end2Navigability);
	}
	/**
	 * <code>FULLACCESS</code> Constructor of <code>UmlAssociation</code> based on ID.
	 * <p>
	 * This constructor is useful for when an <code>association</code> is initialized before its <code>end</code> elements. New {@link #autorest.astahxmlparser.umldatastructure.UmlClass} objects are created containing default names, and these can later be replaced as the IDs are resolved. This constructor does NOT resolve the <code>end</code> IDs automatically. This constructor sets both <code>end</code> multiplicities to <code>1..1</code>, aggregations to <code>association</code> and navigabilities to <code>navigable</code>.
	 *
	 * @since 	0.1.2a
	 * @see 		autorest.astahxmlparser.umldatastructure.UmlElement
	 * @see			autorest.astahxmlparser.umldatastructure.UmlMultiplicity
	 * @see			autorest.astahxmlparser.umldatastructure.UmlAggregation
	 * @see			autorest.astahxmlparser.umldatastructure.UmlNavigability
	 * @param		id Id of this <code>association</code>
	 * @param		end1Id Id of the <code>end1</code> element
	 * @param		end2Id Id of the <code>end2</code> element
	 */
	public UmlAssociation(String id, String end1Id, String end2Id)
	{
		BuildIt(id, new UmlElement(end1Id), new UmlElement(end2Id), new UmlMultiplicity(1,1), new UmlMultiplicity(1,1), UmlAggregation.association, UmlAggregation.association, UmlNavigability.navigable, UmlNavigability.navigable);
	}
	//#END
	/**
	 * Method used internally by the constructors to build an object instance.
	 *
	 * @since		0.1.2a
	 * @see 		autorest.astahxmlparser.umldatastructure.UmlElement
	 * @see			autorest.astahxmlparser.umldatastructure.UmlMultiplicity
	 * @see			autorest.astahxmlparser.umldatastructure.UmlAggregation
	 * @see			autorest.astahxmlparser.umldatastructure.UmlNavigability
	 * @param		id Id of this <code>association</code>
	 * @param		end1Element <code>end1</code> element, typically a {@link #autorest.astahxmlparser.umldatastructure.UmlClass}
	 * @param		end2Element <code>end2</code> element, typically a {@link #autorest.astahxmlparser.umldatastructure.UmlClass}
	 * @param		end1Multiplicity {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object, referrent to <code>end1</code>
	 * @param		end2Multiplicity {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object, referrent to <code>end2</code>
	 * @param		end1Aggregation {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object, referrent to <code>end1</code>
	 * @param		end2Aggregation {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object, referrent to <code>end2</code>
	 * @param		end1Navigability {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object, referrent to <code>end1</code>
	 * @param		end2Navigability {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object, referrent to <code>end2</code>
	 */
	private void BuildIt(String id, UmlElement end1Element, UmlElement end2Element, UmlMultiplicity end1Multiplicity, UmlMultiplicity end2Multiplicity, UmlAggregation end1Aggregation, UmlAggregation end2Aggregation, UmlNavigability end1Navigability, UmlNavigability end2Navigability)
	{
		this.id = id;
		this.end1Element = end1Element;
		this.end2Element = end2Element;
		this.end1Multiplicity = end1Multiplicity;
		this.end2Multiplicity = end2Multiplicity;
		this.end1Aggregation = end1Aggregation;
		this.end2Aggregation = end2Aggregation;
		this.end1Navigability = end1Navigability;
		this.end2Navigability = end2Navigability;
	}

	// GET METHODS
	/**
	 * @since 	0.1.1a
	 //#FULLACCESS
	 * @return	Deep copy of <code>end1Element</code>
	 //#END
	 //#!FULLACCESS
	 * @return	Reference of <code>end1Element</code>
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlElement
	 */
	public UmlElement getEnd1()
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			return DeepCopy.copy(this.end1Element);
		}
	}
	/**
	 * @since 	0.1.1a
	 //#FULLACCESS
	 * @return	Deep copy of <code>end2Element</code>
	 //#END
	 //#!FULLACCESS
	 * @return	Reference of <code>end2Element</code>
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlElement
	 */
	public UmlElement getEnd2()
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			return DeepCopy.copy(this.end2Element);
		}
	}
	/**
	 * @since 	0.1.1a
	 //#FULLACCESS
	 * @return	Deep copy of both <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlElement} objects, in order
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to both code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlElement} objects, in order
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlElement
	 */
	public List<UmlElement> getEnds()
	{
		List<UmlElement> ret = new ArrayList<UmlElement>;
		ret.add(getEnd1());
		ret.add(getEnd2());
		return ret;
	}
	/**
	 * @since 	0.1.1a
	 * @param 	id <code>ID</code> of the desired <code>end</code>
	 //#FULLACCESS
	 * @return	Deep copy of corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlElement} object, if present; null otherwise
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlElement} object, if present; null otherwise
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlElement
	 */
	public UmlElement getEndById(String id)
	{
		if(this.end1Element.getId().equals(id))
		{
			return getEnd1();
		}
		if(this.end2Element.getId().equals(id))
		{
			return getEnd2();
		}
		return null;
	}
	/**
	 * @since 	0.1.1a
	 * @param 	name <code>Name</code> of the desired <code>end</code>
	 //#FULLACCESS
	 * @return	Deep copy of corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlElement} object, if present; null otherwise
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlElement} object, if present; null otherwise
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlElement
	 */
	public UmlElement getEndByName(String name)
	{
		if(this.end1Element.getName().equals(name))
		{
			return getEnd1();
		}
		if(this.end2Element.getName().equals(name))
		{
			return getEnd2();
		}
		return null;
	}
	/**
	 * @since 	0.1.1a
	 //#FULLACCESS
	 * @return	Deep copy of <code>end1</code> {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the <code>end1</code> {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlMultiplicity
	 */
	public UmlMultiplicity getMultiplicityEnd1()
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			return DeepCopy.copy(this.end1Multiplicity);
		}
	}
	/**
	 * @since 	0.1.1a
	 //#FULLACCESS
	 * @return	Deep copy of <code>end2</code> {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the <code>end2</code> {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlMultiplicity
	 */
	public UmlMultiplicity getMultiplicityEnd2()
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			return DeepCopy.copy(this.end2Multiplicity);
		}
	}
	/**
	 * @since 	0.1.1a
	 //#FULLACCESS
	 * @return	Deep copy of both <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} objects, in order
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to both <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} objects, in order
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlMultiplicity
	 */
	public List<UmlMultiplicity> getMulticities()
	{
		List<UmlMultiplicity> ret = new ArrayList<UmlMultiplicity>;
		ret.add(getMultiplicityEnd1());
		ret.add(getMultiplicityEnd2());
		return ret;
	}
	/**
	 * @since 	0.1.1a
	 * @param 	id <code>ID</code> of the desired <code>end</code>
	 //#FULLACCESS
	 * @return	Deep copy of corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object, if present; null otherwise
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object, if present; null otherwise
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlMultiplicity
	 */
	public UmlMultiplicity getMultiplicityByEndId(String id)
	{
		if(this.end1Element.getId().equals(id))
		{
			return getMultiplicityEnd1();
		}
		if(this.end2Element.getId().equals(id))
		{
			return getMultiplicityEnd2();
		}
		return null;
	}
	/**
	 * @since 	0.1.1a
	 * @param 	name <code>Name</code> of the desired <code>end</code>
	 //#FULLACCESS
	 * @return	Deep copy of corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object, if present; null otherwise
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlMultiplicity} object, if present; null otherwise
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlMultiplicity
	 */
	public UmlMultiplicity getMultiplicityByEndName(String name)
	{
		if(this.end1Element.getName().equals(name))
		{
			return getMultiplicityEnd1();
		}
		if(this.end2Element.getName().equals(name))
		{
			return getMultiplicityEnd2();
		}
		return null;
	}
	/**
	 * @since 	0.1.1a
	 //#FULLACCESS
	 * @return	Deep copy of <code>end1</code> {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the <code>end1</code> {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlAggregation
	 */
	public UmlAggregation getAggregationEnd1()
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			return DeepCopy.copy(this.end1Aggregation);
		}
	}
	/**
	 * @since 	0.1.1a
	 //#FULLACCESS
	 * @return	Deep copy of <code>end2</code> {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the <code>end2</code> {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlAggregation
	 */
	public UmlAggregation getAggregationEnd2()
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			return DeepCopy.copy(this.end2Aggregation);
		}
	}
	/**
	 * @since 	0.1.1a
	 //#FULLACCESS
	 * @return	Deep copy of both <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} objects, in order
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to both <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} objects, in order
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlAggregation
	 */
	public List<UmlAggregation> getAggregations()
	{
		List<UmlAggregation> ret = new ArrayList<UmlAggregation>;
		ret.add(getAggregationEnd1());
		ret.add(getAggregationEnd2());
		return ret;
	}
	/**
	 * @since 	0.1.1a
	 * @param 	id <code>ID</code> of the desired <code>end</code>
	 //#FULLACCESS
	 * @return	Deep copy of corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object, if present; null otherwise
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object, if present; null otherwise
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlAggregation
	 */
	public UmlAggregation getAggregationByEndId(String id)
	{
		if(this.end1Element.getId().equals(id))
		{
			return getAggregationEnd1();
		}
		if(this.end2Element.getId().equals(id))
		{
			return getAggregationEnd2();
		}
		return null;
	}
	/**
	 * @since 	0.1.1a
	 * @param 	name <code>Name</code> of the desired <code>end</code>
	 //#FULLACCESS
	 * @return	Deep copy of corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object, if present; null otherwise
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlAggregation} object, if present; null otherwise
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlAggregation
	 */
	public UmlAggregation getAggregationByEndName(String name)
	{
		if(this.end1Element.getName().equals(name))
		{
			return getAggregationEnd1();
		}
		if(this.end2Element.getName().equals(name))
		{
			return getAggregationEnd2();
		}
		return null;
	}
	/**
	 * @since 	0.1.1a
	 //#FULLACCESS
	 * @return	Deep copy of <code>end1</code> {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the <code>end1</code> {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlNavigability
	 */
	public UmlNavigability getNavigabilityEnd1()
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			return DeepCopy.copy(this.end1Navigability);
		}
	}
	/**
	 * @since 	0.1.1a
	 //#FULLACCESS
	 * @return	Deep copy of <code>end2</code> {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the <code>end2</code> {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlNavigability
	 */
	public UmlNavigability getNavigabilityEnd2()
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			return DeepCopy.copy(this.end2Navigability);
		}
	}
	/**
	 * @since 	0.1.1a
	 //#FULLACCESS
	 * @return	Deep copy of both <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} objects, in order
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to both <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} objects, in order
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlNavigability
	 */
	public List<UmlNavigability> getNavigabilities()
	{
		List<UmlNavigability> ret = new ArrayList<UmlNavigability>;
		ret.add(getNavigabilityEnd1());
		ret.add(getNavigabilityEnd2());
		return ret;
	}
	/**
	 * @since 	0.1.1a
	 * @param 	id <code>ID</code> of the desired <code>end</code>
	 //#FULLACCESS
	 * @return	Deep copy of corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object, if present; null otherwise
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object, if present; null otherwise
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlNavigability
	 */
	public UmlNavigability getNavigabilityByEndId(String id)
	{
		if(this.end1Element.getId().equals(id))
		{
			return getNavigabilityEnd1();
		}
		if(this.end2Element.getId().equals(id))
		{
			return getNavigabilityEnd2();
		}
		return null;
	}
	/**
	 * @since 	0.1.1a
	 * @param 	name <code>Name</code> of the desired <code>end</code>
	 //#FULLACCESS
	 * @return	Deep copy of corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object, if present; null otherwise
	 //#END
	 //#!FULLACCESS
	 * @return	Reference to the corresponding <code>end</code> {@link #autorest.astahxmlparser.umldatastructure.UmlNavigability} object, if present; null otherwise
	 //#END
	 * @see			autorest.astahxmlparser.umldatastructure.UmlNavigability
	 */
	public UmlNavigability getNavigabilityByEndName(String name)
	{
		if(this.end1Element.getName().equals(name))
		{
			return getNavigabilityEnd1();
		}
		if(this.end2Element.getName().equals(name))
		{
			return getNavigabilityEnd2();
		}
		return null;
	}
}
