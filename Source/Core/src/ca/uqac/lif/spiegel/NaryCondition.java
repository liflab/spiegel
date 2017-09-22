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

import java.util.ArrayList;
import java.util.List;

public abstract class NaryCondition extends Condition
{
	protected List<Condition> m_conditions;
	
	protected String m_symbol;
	
	public NaryCondition(String symbol, Condition ... conditions)
	{
		super();
		m_symbol = symbol;
		m_conditions = new ArrayList<Condition>(conditions.length);
		for (Condition c : conditions)
		{
			m_conditions.add(c);
		}
	}
	
	public void addCondition(Condition c)
	{
		m_conditions.add(c);
	}
	
	/**
	 * Gets the list of conditions inside this n-ary condition
	 * @return The list of conditions
	 */
	public List<Condition> getConditions()
	{
		return m_conditions;
	}
	
	@Override
	public Condition getTypeCondition()
	{
		for (Condition c : m_conditions)
		{
			Condition tc = c.getTypeCondition();
			if (tc != null)
			{
				return tc;
			}
		}
		return null;
	}
	
	@Override
	public String toString()
	{
		StringBuilder out = new StringBuilder();
		boolean first = true;
		for (Condition c : m_conditions)
		{
			if (first)
				first = false;
			else
				out.append(" ").append(m_symbol).append(" ");
			out.append("(").append(c).append(")");
		}
		return out.toString();
	}

}
