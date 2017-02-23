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
 * Condition that specifies that an element does not exist in the object
 * map. The element is considered not to exist if either:
 * <ul>
 * <li>The map is null</li>
 * <li>The map does not contain an argument with that name</li>
 * <li>The value of the argument in the map is {@code null}</li>
 * </ul>
 * This condition is useful for specifying that an element is optional.
 * One can say that it either does not exist, OR satisfies 
 * some other condition.
 * @author Sylvain Hallé
 */
public class DoesNotExist extends ArgumentCondition
{
	public DoesNotExist(String argument) 
	{
		super(argument);
	}

	@Override
	public boolean satisfies(ObjectMap map) 
	{
		return map == null || !map.containsKey(m_argument) || map.get(m_argument) == null;
	}
	
	@Override
	public String toString()
	{
		return m_argument + " is undefined";
	}

	@Override
	public Condition getTypeCondition()
	{
		return null;
	}
}
