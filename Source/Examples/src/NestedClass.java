import ca.uqac.lif.spiegel.And;
import ca.uqac.lif.spiegel.BuildException;
import ca.uqac.lif.spiegel.Condition;
import ca.uqac.lif.spiegel.IndependentConditions;
import ca.uqac.lif.spiegel.IntegerValueInInterval;
import ca.uqac.lif.spiegel.IsInteger;
import ca.uqac.lif.spiegel.IsOfType;
import ca.uqac.lif.spiegel.ObjectMap;
import ca.uqac.lif.spiegel.ObjectMapException;

public class NestedClass extends TopLevelClass
{
	protected TopLevelClass innerObject;
	
	int foo;
	
	public NestedClass(TopLevelClass o, int foo)
	{
		super();
		this.foo = foo;
		innerObject = o;
	}
	
	public static NestedClass buildObject(ObjectMap map) throws BuildException
	{			
		if (!getBuildCondition().satisfies(map))
		{
			throw new BuildException("Invalid arguments to build this object");
		}
		try
		{
			int a = map.getInt("foo");
			TopLevelClass c = (TopLevelClass) map.get("innerObject");
			NestedClass new_object = new NestedClass(c, a);
			return new_object;
		}
		catch (ClassCastException e)
		{
			throw new BuildException(e);
		}
		catch (ObjectMapException e)
		{
			throw new BuildException(e);
		}
	}

	public static Condition getBuildCondition() 
	{
		IndependentConditions idc = new IndependentConditions();
		idc.setConditionFor("foo", new And(
				new IsInteger("foo"),
				new IntegerValueInInterval("foo", 0, 10, 2)));
		idc.setConditionFor("innerObject", new IsOfType("innerObject", TopLevelClass.class));
		return idc;
	}

}
