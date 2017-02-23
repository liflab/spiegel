package ca.uqac.lif.spiegel;

/**
 * Condition that returns always true. It is used to say that
 * no constraint applies on an element of an object map.
 * @author Sylvain Hallé
 */
public class TrueCondition extends Condition 
{
	public static final TrueCondition instance = new TrueCondition();
	
	TrueCondition()
	{
		super();
	}

	@Override
	public boolean satisfies(ObjectMap map)
	{
		return true;
	}

	@Override
	public String toString()
	{
		return "true";
	}
	
	@Override
	public IsOfType getTypeCondition()
	{
		return null;
	}
}
