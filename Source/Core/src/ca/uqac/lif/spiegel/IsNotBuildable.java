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
 * Condition expressing that a specific object cannot be built using
 * Spiegel. This generally occurs when a class does not implement the
 * {@link Buildable} interface.
 * 
 * @author Sylvain Hallé
 */
public class IsNotBuildable extends Condition
{
	/**
	 * A reference to the single instance of this class. Since this class
	 * has no state, all objects can safely refer to this single instance.
	 */
	public static final transient IsNotBuildable instance = new IsNotBuildable();
	
	protected IsNotBuildable()
	{
		super();
	}

	@Override
	public boolean satisfies(ObjectMap map)
	{
		return false;
	}

	@Override
	public Condition getTypeCondition() 
	{
		return null;
	}
}
