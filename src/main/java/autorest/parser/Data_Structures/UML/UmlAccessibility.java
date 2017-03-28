package autorest.astahxmlparser.umldatastructure;

/**
 * Enumerator representing whether a UML element is of <code>public</code> or <code>private</code> access.
 * <p>
 * Other access levels, such as <code>internal</code>, are not represented in the basic version of this structure due to not being used in the AutoREST instance developed by Dumer and Laser.
 * <p>
 * DEVNOTES - Code complete.
 *
 * @author 			Marcelo Schmitt Laser
 * @since				0.1.1a
 */
public enum UmlAccessibility
{
	publicaccess,
	privateaccess,
}
