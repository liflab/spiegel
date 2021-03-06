/*
    Spiegel, a library for manipulating objects with objects
    Copyright (C) 2017 Sylvain Hall�

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
 * Negation of a condition
 * @author Sylvain Hall�
 */
public class Not extends Condition
{
	/**
	 * The condition to negate
	 */
	protected Condition m_condition;
	
	/**
	 * Creates a new negation
	 * @param condition The condition to negate
	 */
	public Not(Condition condition)
	{
		super();
		m_condition = condition;
	}
	
	/**
	 * Gets the condition that is negated by this object
	 * @return The condition
	 */
	public Condition getCondition()
	{
		return m_condition;
	}

	@Override
	public boolean satisfies(ObjectMap map) 
	{
		return !m_condition.satisfies(map);
	}

	@Override
	public String toString()
	{
		return "not (" + m_condition + ")";
	}
	
	@Override
	public Condition getTypeCondition()
	{
		return m_condition.getTypeCondition();
	}
}
