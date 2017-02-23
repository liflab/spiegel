package ca.uqac.lif.spiegel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Set of conditions where each element of the argument map is
 * independent from the others. To be satisfied by an object map, each
 * condition on each argument must be satisfied.
 * @author Sylvain Hallé
 */
public class IndependentConditions extends Condition 
{
	/**
	 * The conditions for each element of the map
	 */
	protected Map<String,Condition> m_conditions;
	
	public IndependentConditions()
	{
		super();
		m_conditions = new HashMap<String,Condition>();
	}
	
	/**
	 * Gets the set of all entries in this map
	 * @return The entries
	 */
	public Set<Map.Entry<String,Condition>> entrySet()
	{
		return m_conditions.entrySet();
	}
	
	/**
	 * Sets the condition that applies to a specific element of an object
	 * map.
	 * @param argument The name of the element
	 * @param condition The condition. Can be null; interpreted as no
	 * condition.
	 */
	public void setConditionFor(String argument, Condition condition)
	{
		m_conditions.put(argument, condition);
	}
	
	/**
	 * Gets the condition that applies to a specific element of an object
	 * map. This method always returns a condition, even if the element
	 * does not exist; in such a case, it returns {@link TrueCondition}.
	 * @param argument The name of the element
	 * @return The condition
	 */
	public Condition getConditionFor(String argument)
	{
		if (!m_conditions.containsKey(argument))
		{
			return TrueCondition.instance;
		}
		return m_conditions.get(argument);
	}

	@Override
	public boolean satisfies(ObjectMap map)
	{
		for (Condition c : m_conditions.values())
		{
			if (!c.satisfies(map))
				return false;
		}
		return true;
	}
	
	@Override
	public IsOfType getTypeCondition()
	{
		return null;
	}
}
