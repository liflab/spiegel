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
 * Condition on the value of an element of the object map
 * @author Sylvain Hallé
 */
public abstract class ValueCondition extends ArgumentCondition 
{
	/**
	 * Creates a new condition
	 * @param argument The name of the element in the object map
	 * this condition refers to
	 */
	public ValueCondition(String argument)
	{
		super(argument);
	}

	@Override
	public boolean satisfies(ObjectMap map) 
	{
		if (map == null || !map.containsKey(m_argument))
		{
			return false;
		}
		return checkCondition(map.get(m_argument));
	}
	
	protected abstract boolean checkCondition(Object o);

}
