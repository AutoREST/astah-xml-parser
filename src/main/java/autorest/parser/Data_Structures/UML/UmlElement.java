package autorest.astahxmlparser.umldatastructure;

import java.util.List;

/**
 * Class representing an UML element.
 * <p>
 //#FULLACCESS
 * An <code>UmlElement</code> is an abstract entity that represents the basic UML building blocks shared between {@link #autorest.astahxmlparser.umldatastructure.UmlClass}, {@link #autorest.astahxmlparser.umldatastructure.UmlAssociation}, {@link #autorest.astahxmlparser.umldatastructure.UmlAssociativeClass} and {@link #autorest.astahxmlparser.umldatastructure.UmlAttribute}.
 //#END
 //#!FULLACCESS
* An <code>UmlElement</code> is an abstract entity that represents the basic UML building blocks shared between {@link #autorest.astahxmlparser.umldatastructure.UmlClass}, {@link #autorest.astahxmlparser.umldatastructure.UmlAssociation}, {@link #autorest.astahxmlparser.umldatastructure.UmlAssociativeClass} and {@link #autorest.astahxmlparser.umldatastructure.UmlAttribute}. This class may only be accessed from inside the {@link #autorest.astahxmlparser.umldatastructure} package.
 //#END
 * <p>
 * DEVNOTES - Methods must be implemented, architecture is incomplete.
 *
 * @author 			Marcelo Schmitt Laser
 * @since				0.1.1a
 * @see					autorest.astahxmlparser.umldatastructure.UmlClass
 * @see					autorest.astahxmlparser.umldatastructure.UmlAssociation
 * @see					autorest.astahxmlparser.umldatastructure.UmlAssociativeClass
 * @see					autorest.astahxmlparser.umldatastructure.UmlAttribute
 * @see					autorest.Preprocessor.FULLACCESS
 */
//#FULLACCESS
public
//#END
abstract class UmlElement implements Serializable
{
	//FIELDS
	/**
	 * The id of this {@link #autorest.astahxmlparser.umldatastructure.UmlElement}.
	 */
	private String id;
	/**
	 * The name of this {@link #autorest.astahxmlparser.umldatastructure.UmlElement}.
	 */
	private String name;
	/**
	 * A list of {@link #autorest.astahxmlparser.umldatastructure.UmlStereotype} objects, representing the <code>stereotypes</code> of this {@link #autorest.astahxmlparser.umldatastructure.UmlElement}.
	 */
	private List<UmlStereotype> stereotypes;
	/**
	 * A list of {@link #autorest.astahxmlparser.umldatastructure.UmlTag} objects, representing the <code>tags</code> of this {@link #autorest.astahxmlparser.umldatastructure.UmlElement}.
	 */
	private List<UmlTag> tags;
}
