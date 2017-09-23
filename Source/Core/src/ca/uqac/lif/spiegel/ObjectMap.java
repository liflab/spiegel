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

import java.util.HashMap;

/**
 * Nested data structure that mirrors the internal structure and contents
 * of an object. An object map associates keys, corresponding to the names
 * of member fields inside an object, to values, which can be arbitrary
 * objects --including a nested object map.
 * <p>
 * An object that implements the {@link Buildable} interface provides a
 * method called {@link Buildable#buildObject(ObjectMap) build()}, that
 * can take such a map, and "fill" an empty instance of a given class with the
 * specified contents. This object also provides a method called 
 * {@link Buildable#getBuildCondition() getBuildCondition()}, that returns a
 * {@link Condition} object.
 * <p>
 * The {@link ObjectBuilder} can also be used to build objects from an
 * object map.
 * 
 * @author Sylvain Hallé
 */
public class ObjectMap extends HashMap<String,Object> 
{
	/**
	 * Dummy UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Attempts to cast an element of the map as an integer
	 * @param argument The argument
	 * @return The value
	 * @throws ObjectMapException If the element does not exist, or cannot be
	 * cast to the desired type
	 */
	public int getInt(String argument) throws ObjectMapException
	{
		if (!containsKey(argument))
		{
			throw new ArgumentDoesNotExistException(argument); 
		}
		Object o = get(argument);
		if (o == null)
		{
			throw new ArgumentIsNullException(argument);
		}
		try
		{
			return (int) o;
		}
		catch (ClassCastException e)
		{
			throw new ObjectMapException(e);
		}
	}
	
	/**
	 * Attempts to cast an element of the map as a string
	 * @param argument The argument
	 * @return The value
	 * @throws ObjectMapException If the element does not exist, or cannot be
	 * cast to the desired type
	 */
	public String getString(String argument) throws ObjectMapException
	{
		if (!containsKey(argument))
		{
			throw new ArgumentDoesNotExistException(argument); 
		}
		Object o = get(argument);
		if (o == null)
		{
			throw new ArgumentIsNullException(argument);
		}
		try
		{
			return (String) o;
		}
		catch (ClassCastException e)
		{
			throw new ObjectMapException(e);
		}
	}

	
	public static class ArgumentDoesNotExistException extends ObjectMapException
	{
		/**
		 * Dummy UID
		 */
		private static final long serialVersionUID = 1L;

		public ArgumentDoesNotExistException(String argument)
		{
			super("The argument " + argument + " does not exist");
		}		
	}
	
	public static class ArgumentIsNullException extends ObjectMapException
	{
		/**
		 * Dummy UID
		 */
		private static final long serialVersionUID = 1L;

		public ArgumentIsNullException(String argument)
		{
			super("The argument " + argument + " is null");
		}		
	}


}
