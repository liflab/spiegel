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
 * Interface allowing an object to be modified by passing a map
 * of arguments
 * @author Sylvain Hallé
 */

public interface Modifiable 
{
	/**
	 * Modifies the state of an existing object, given
	 * the provided arguments 
	 * @param map An object map, whose data is used to build the new object
	 * @throws ModificationException If the modification cannot proceed
	 */
	public void modify(ObjectMap map) throws ModificationException;
}
