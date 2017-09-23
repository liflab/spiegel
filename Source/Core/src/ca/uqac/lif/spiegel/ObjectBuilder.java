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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Builds a new instance of a class, using data provided by an
 * {@link ObjectMap}.
 * @author Sylvain Hallé
 *
 * @param <T> The type of the object this builder creates
 */
public class ObjectBuilder<T> 
{
	/**
	 * The name of the method that can be used to build an object
	 * from an {@link ObjectMap}. This refers to
	 * {@link Buildable#buildObject(ObjectMap)}.
	 */
	public static final String s_buildMethodName = "buildObject";
	
	/**
	 * The name of the method that can be used to query an object for
	 * its build conditions. This refers to
	 * {@link Buildable#getBuildCondition()}.
	 */
	public static final String s_buildConditionsMethodName = "getBuildCondition";
	
	/**
	 * The class the objects built by this builder belong to
	 */
	protected final Class<?> m_class;
	
	/**
	 * Creates a new object builder
	 * @param clazz The class the objects built by this builder belong to.
	 * This must match the type {@code T} used when instantiating the
	 * builder.
	 */
	public ObjectBuilder(Class<T> clazz)
	{
		super();
		m_class = clazz;
	}
	
	/**
	 * Tells if the objects built by this builder are buildable. If
	 * they are not, a call to {@link #buildObject(ObjectMap)} will result
	 * in an error.
	 * @see #isBuildable(Class)
	 * @return {@code true} if the obejcts are buildable, {@code false}
	 * otherwise
	 */
	public boolean isBuildable()
	{
		return isBuildable(m_class);
	}
	
	/**
	 * Gets the build condition associated to objects of type {@code T}
	 * @return The build condition
	 */
	public Condition getBuildCondition()
	{
		return getBuildCondition(m_class);
	}
	
	/**
	 * Tells if the objects of the given class are buildable. An object
	 * is buildable if it declares a method of name {@link #s_buildMethodName}.
	 * @param clazz The type of the objects to check
	 * @return {@code true} if the object is buildable, {@code false}
	 * otherwise
	 */
	public static boolean isBuildable(Class<?> clazz)
	{
		return getBuildMethod(clazz) != null;
	}
	
	/**
	 * Gets the build condition associated to objects of a given type
	 * @param clazz The type of the objects to check
	 * @return The build condition
	 */
	public static Condition getBuildCondition(Class<?> clazz)
	{
		Method m = getBuildConditionsMethod(clazz);
		if (m == null)
		{
			return TrueCondition.instance;
		}
		try 
		{
			Object o = m.invoke(null, new Object[0]);
			if (!(o instanceof Condition))
			{
				return TrueCondition.instance;
			}
			return (Condition) o;
		}
		catch (IllegalAccessException e) 
		{
			return TrueCondition.instance;
		}
		catch (IllegalArgumentException e) 
		{
			return TrueCondition.instance;
		}
		catch (InvocationTargetException e) 
		{
			return TrueCondition.instance;
		}
	}
	
	/**
	 * Attempts to build an object from an object map
	 * @param map The object map
	 * @return An instance of the object, built according to the contents
	 *   of the object map
	 * @throws BuildException Thrown if the build process could not be
	 *   completed for some reason
	 */
	@SuppressWarnings("unchecked")
	public T buildObject(ObjectMap map) throws BuildException
	{
		Object o = buildObject(m_class, map);
		if (!(m_class.isAssignableFrom(o.getClass())))
		{
			throw new BuildException("Object created by the class has the wrong type");
		}
		return (T) o;
	}
	
	/**
	 * Attempts to build an object of a given type from an object map
	 * @param clazz The type of the object that should be built
	 * @param map The object map
	 * @return An instance of the object, built according to the contents
	 *   of the object map
	 * @throws BuildException Thrown if the build process could not be
	 *   completed for some reason
	 */
	public static Object buildObject(Class<?> clazz, ObjectMap map) throws BuildException
	{
		Method m = getBuildMethod(clazz);
		if (m == null)
		{
			throw new BuildException("This class is not buildable");
		}
		try 
		{
			Object o = m.invoke(null, map);
			return o;
		}
		catch (IllegalAccessException e) 
		{
			throw new BuildException(e);
		} 
		catch (IllegalArgumentException e) 
		{
			throw new BuildException(e);
		}
		catch (InvocationTargetException e) 
		{
			throw new BuildException(e);
		}
	}
	
	/**
	 * Attempts to retrieve the build method of a class.
	 * @param clazz The class
	 * @see #getBuildMethod()
	 * @return An instance of a {@code Method} object corresponding
	 * to the build method, or {@code null} if no such method could be found
	 */
	protected static Method getBuildMethod(Class<?> clazz)
	{
		try 
		{
			Method m = clazz.getMethod(s_buildMethodName, new Class[]{ObjectMap.class});
			return m;
		}
		catch (NoSuchMethodException e)
		{
			return null;
		}
		catch (SecurityException e) 
		{
			return null;
		}
	}
	
	/**
	 * Attempts to retrieve the build condition method of a class.
	 * @param clazz The class
	 * @see #getBuildCondition()
	 * @return An instance of a {@code Method} object corresponding
	 * to the build condition method, or {@code null} if no such method could be found
	 */

	protected static Method getBuildConditionsMethod(Class<?> clazz)
	{
		try 
		{
			Method m = clazz.getMethod(s_buildConditionsMethodName, new Class[0]);
			return m;
		}
		catch (NoSuchMethodException e)
		{
			return null;
		}
		catch (SecurityException e) 
		{
			return null;
		}
	}

}
