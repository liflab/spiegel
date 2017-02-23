import ca.uqac.lif.spiegel.And;
import ca.uqac.lif.spiegel.BuildException;
import ca.uqac.lif.spiegel.Condition;
import ca.uqac.lif.spiegel.IndependentConditions;
import ca.uqac.lif.spiegel.IsInteger;
import ca.uqac.lif.spiegel.IsText;
import ca.uqac.lif.spiegel.ObjectMap;
import ca.uqac.lif.spiegel.ObjectMapException;
import ca.uqac.lif.spiegel.ValueInSet;

public class SimpleClass extends TopLevelClass
{
	int a;
	String b;
	
	public SimpleClass(int a, String b) 
	{
		super();
		this.a = a;
		this.b = b;
	}

	public static SimpleClass buildObject(ObjectMap map) throws BuildException
	{			
		if (!getBuildCondition().satisfies(map))
		{
			throw new BuildException("Invalid arguments to build this object");
		}
		try
		{
			int a = map.getInt("a");
			String b = map.getString("b");
			SimpleClass new_object = new SimpleClass(a, b);
			return new_object;
		}
		catch (ObjectMapException e)
		{
			throw new BuildException(e);
		}
	}

	public static Condition getBuildCondition() 
	{
		IndependentConditions idc = new IndependentConditions();
		idc.setConditionFor("a", new And(
				new IsInteger("a"),
				new ValueInSet("a", 0, 1, 2)));
		idc.setConditionFor("b", new IsText("b"));
		return idc;
	}
	
}
