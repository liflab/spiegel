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
 * Interface allowing an object to be instantiated by passing a map
 * of arguments
 * @author Sylvain Hallé
 *
 * @param <T> The type of the objects built
 */
public interface Buildable<T> 
{
	/**
	 * Creates a new instance of an object of class T, given
	 * the provided arguments 
	 * @param map An object map, whose data is used to build the new object
	 * @return The object
	 * @throws BuildException If the object cannot be built
	 */
	public T buildObject(ObjectMap map) throws BuildException;
	
	/**
	 * Gets the condition on the object map that can be used to build
	 * an object of this type
	 * @return The condition
	 */
	public Condition getBuildCondition();
}
