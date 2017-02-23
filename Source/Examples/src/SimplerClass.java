import ca.uqac.lif.spiegel.BuildException;
import ca.uqac.lif.spiegel.Condition;
import ca.uqac.lif.spiegel.IndependentConditions;
import ca.uqac.lif.spiegel.IsInteger;
import ca.uqac.lif.spiegel.IsText;
import ca.uqac.lif.spiegel.ObjectMap;
import ca.uqac.lif.spiegel.ObjectMapException;

public class SimplerClass extends TopLevelClass
{
	int a;
	String b;
	
	public SimplerClass(int a, String b)
	{
		super();
		this.a = a;
		this.b = b;
	}

	public static SimplerClass buildObject(ObjectMap map) throws BuildException
	{			
		if (!getBuildCondition().satisfies(map))
		{
			throw new BuildException("Invalid arguments to build this object");
		}
		try
		{
			int a = map.getInt("a");
			String b = map.getString("b");
			SimplerClass new_object = new SimplerClass(a, b);
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
		idc.setConditionFor("a", new IsInteger("a"));
		idc.setConditionFor("b", new IsText("b"));
		return idc;
	}
	
}
