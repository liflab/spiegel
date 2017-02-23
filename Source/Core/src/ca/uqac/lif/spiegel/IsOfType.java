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

/**
 * Conditions that stipulates the type of an argument.
 * To satisfy this condition, an object map must have an argument
 * of given name, whose type is a descendent of the specified type.
 * @author Sylvain Hallé
 */
public class IsOfType extends ArgumentCondition 
{
	/**
	 * The type
	 */
	protected final Class<?> m_type;
	
	/**
	 * Creates a condition that stipulates the type of an argument.
	 * @param argument The name of the argument
	 * @param type The type
	 */
	public IsOfType(String argument, Class<?> type)
	{
		super(argument);
		m_type = type;
	}
	
	/**
	 * Gets the type associated to this condition
	 * @return The type
	 */
	public Class<?> getType()
	{
		return m_type;
	}

	@Override
	public boolean satisfies(ObjectMap map) 
	{
		if (map == null || !map.containsKey(m_argument))
		{
			return false;
		}
		Object o = map.get(m_argument);
		if (o == null)
		{
			return false;
		}
		return m_type.isAssignableFrom(o.getClass());
	}

	@Override
	public String toString()
	{
		return m_argument + " is a " + m_type.getSimpleName();
	}
	
	@Override
	public IsOfType getTypeCondition()
	{
		return this;
	}
}
