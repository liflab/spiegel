/*
    Spiegel, a library for manipulating objects with objects
    Copyright (C) 2017 Sylvain Hallé

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
	
	/**
	 * Creates a new empty set of conditions
	 */
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
