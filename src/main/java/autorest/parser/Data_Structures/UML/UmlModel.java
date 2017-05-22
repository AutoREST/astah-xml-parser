package autorest.astahxmlparser.umldatastructure;

import java.util.Map;
import java.util.HashMap;

public class UmlModel
{
	public Map<String, UmlClass> classes;
	public Map<String, UmlAssociation> associations;
	public Map<String, UmlAssociativeClass> associativeClasses;
	public Map<String, UmlStereotype> stereotypes;
	public Map<String, UmlType> types;
	public Map<String, UmlPackage> packages;

	public UmlModel()
	{
		this.classes = new HashMap<String, UmlClass>();
		this.associations = new HashMap<String, UmlAssociation>();
		this.associativeClasses = new HashMap<String, UmlAssociativeClass>();
		this.stereotypes = new HashMap<String, UmlStereotype>();
		this.types = new HashMap<String, UmlType>();
		this.packages = new HashMap<String, UmlPackage>();
	}
}
