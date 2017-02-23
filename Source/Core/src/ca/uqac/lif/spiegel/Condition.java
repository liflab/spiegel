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
 * Describes a condition on the possible values and type of an argument
 * in an object map
 * @author Sylvain Hallé
 */
public abstract class Condition
{
	public Condition()
	{
		super();
	}
	
	/**
	 * Determines if an object map satisfies the condition
	 * @param map The map
	 * @return
	 */
	public abstract boolean satisfies(ObjectMap map);
	
	public abstract Condition getTypeCondition();
}
