package autorest.astahxmlparser.umldatastructure;

import autorest.util.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.UnsupportedOperationException;
import autorest.Preprocessor;

public class UmlModel
{
	// ATTRIBUTES
	private List<UmlClass> classes;
	private List<UmlAssociation> associations;
	private List<UmlAssociativeClass> associativeClasses;
	private List<UmlStereotype> stereotypes;

	// CONSTRUCTOR
	public UmlModel()
	{
		classes = new ArrayList<UmlClass>();
		associations = new ArrayList<UmlAssociation>();
		associativeClasses = new ArrayList<UmlAssociativeClass>();
		stereotypes = new ArrayList<UmlStereotype>();
	}
	public UmlModel(UmlModel model)
	{
		throw new UnsupportedOperationException("TODO");
	}

	// INTERNAL ACCESS GET METHODS
	public ArrayList<UmlClass> getClassList()
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public ArrayList<UmlAssociation> getAssociationList()
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public ArrayList<UmlAssociativeClass> getAssociativeClassList()
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public ArrayList<UmlStereotype> getStereotype()
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public UmlClass getClassByName(String name)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public UmlClass getClassById(String id)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public UmlAssociation getAssociationById(String id)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public UmlAssociation getAssociationByEnd(UmlElement end)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public UmlAssociation getAssociationByEnd(String id)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public UmlAssociativeClass getAssociativeClassByName(String name)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public UmlAssociativeClass getAssociativeClassById(String id)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public UmlAssociativeClass getAssociativeClassByEnd(UmlElement end)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public UmlAssociativeClass getAssociativeClassByEnd(String id)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public UmlStereotype getStereotypeByName(String name)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public UmlStereotype getStereotypeById(String id)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}

	// ADD METHODS
	public boolean addClass(UmlClass classy)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public boolean addClass(String id, String name, UmlAccessibility accessibility)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public boolean addAssociation(UmlAssociation association)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public boolean addAssociation(String id, UmlElement end1, UmlElement end2)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public boolean addAssociation(String id, String end1Id, String end2Id)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public boolean addAssociativeClass(UmlAssociativeClass associativeClass)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public boolean addAssociativeClass(String id, String name, UmlAccessibility accessibility, UmlElement end1, UmlElement end2)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public boolean addAssociativeClass(String id, String name, UmlAccessibility accessibility, String end1Id, String end2Id)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public boolean addStereotype(UmlStereotype stereotype)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}
	public boolean addStereotype(String id, String name)
	{
		if(Preprocessor.FULLACCESS)
		{
			throw new UnsupportedOperationException("TODO");
		}
		else
		{
			throw new UnsupportedOperationException("TODO");
		}
	}

	// REMOVE METHODS
	//TODO

	// FULL ACCESS GET METHODS

}
