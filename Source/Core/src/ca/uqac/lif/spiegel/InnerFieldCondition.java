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
 * Condition that stipulates that an inner field of a given class
 * must be created inside an object.
 * @author Sylvain Hallé
 */
public class InnerFieldCondition extends Condition
{
	/**
	 * The condition on the inner field
	 */
	protected Condition m_fieldCondition;
	
	/**
	 * The name of the inner field
	 */
	protected String m_fieldName;
	
	/**
	 * The class (or type) of the inner field
	 */
	protected Class<?> m_fieldClass;
	
	/**
	 * The type condition associated to this condition. Since the
	 * inner field is of a fixed type, the condition is necessarily
	 * an {@link IsOfType} object. It is instantiated once, and
	 * referred to upon every call to {@link #getTypeCondition()}.
	 */
	protected final transient IsOfType m_typeCondition;
	
	/**
	 * Creates a new inner field condition.
	 * @param name The name of the inner field
	 * @param clazz The class (or type) of the inner field
	 * @param condition The condition on the inner field
	 */
	public InnerFieldCondition(String name, Class<?> clazz, Condition condition)
	{
		super();
		m_fieldName = name;
		m_fieldClass = clazz;
		m_fieldCondition = condition;
		m_typeCondition = new IsOfType(m_fieldName, m_fieldClass);
	}

	@Override
	public boolean satisfies(ObjectMap map) 
	{
		return m_fieldCondition.satisfies(map);
	}

	@Override
	public Condition getTypeCondition() 
	{
		return m_typeCondition;
	}

}
