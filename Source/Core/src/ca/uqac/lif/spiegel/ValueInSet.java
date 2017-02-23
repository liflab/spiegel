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

import java.util.Collection;
import java.util.HashSet;

public class ValueInSet extends ValueCondition 
{
	protected Collection<Object> m_values;
	
	public ValueInSet(String argument, Collection<Object> values)
	{
		super(argument);
		m_values = values;
	}
	
	public ValueInSet(String argument, Object ... values)
	{
		super(argument);
		m_values = new HashSet<Object>();
		for (Object o : values)
		{
			m_values.add(o);
		}
	}

	@Override
	protected boolean checkCondition(Object o) 
	{
		return m_values.contains(o);
	}

	@Override
	public String toString()
	{
		return m_argument + " is in " + m_values;
	}
	
	@Override
	public IsOfType getTypeCondition()
	{
		return null;
	}
}
