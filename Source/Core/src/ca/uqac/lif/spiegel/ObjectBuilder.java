package ca.uqac.lif.spiegel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectBuilder<T> 
{
	public static final String s_buildMethodName = "buildObject";
	
	public static final String s_buildConditionsMethodName = "getBuildCondition";
	
	protected final Class<?> m_class;
	
	public ObjectBuilder(Class<?> clazz)
	{
		super();
		m_class = clazz;
	}
	
	public boolean isBuildable()
	{
		return isBuildable(m_class);
	}
	
	public Condition getBuildCondition()
	{
		return getBuildCondition(m_class);
	}
	
	public static boolean isBuildable(Class<?> clazz)
	{
		return getBuildMethod(clazz) != null;
	}
	
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
