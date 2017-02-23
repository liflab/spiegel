import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

import ca.uqac.lif.spiegel.BuildException;
import ca.uqac.lif.spiegel.Condition;
import ca.uqac.lif.spiegel.IndependentConditions;
import ca.uqac.lif.spiegel.IsOfType;
import ca.uqac.lif.spiegel.ObjectBuilder;
import ca.uqac.lif.spiegel.ObjectMap;

/**
 * This example shows how new instances of an object can be built
 * interactively, without prior knowledge of the class, its constructors or
 * its member fields.
 * @author Sylvain Hallé
 */
public class InteractiveConstructor
{

	public static void main(String[] args) throws IOException, BuildException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Object o = buildObjectInteractively(reader, "");
		// And here, we could do something with the instance we created
	}
	
	public static Object buildObjectInteractively(BufferedReader reader, String indent) throws IOException, BuildException
	{
		boolean exit = false;
		while (!exit)
		{
			System.out.print(indent + "Enter the name of a class: ");
			String class_name = reader.readLine();
			Class<?> clazz = null;
			try 
			{
				clazz = Class.forName(class_name);
			}
			catch (ClassNotFoundException e) 
			{
				System.out.println(indent + "This class does not seem to exist. Try something else.");
				continue;
			}
			if (!ObjectBuilder.isBuildable(clazz))
			{
				System.out.println(indent + "This class does not appear to be buildable. Try something else.");
				continue;
			}
			// We are here: class is buildable
			Condition c = ObjectBuilder.getBuildCondition(clazz);
			if (c instanceof IndependentConditions)
			{
				Set<Map.Entry<String,Condition>> entries = ((IndependentConditions) c).entrySet();
				ObjectMap map = new ObjectMap();
				System.out.println(indent + "To build an object of this class, you need " + entries.size() + " arguments.");
				for (Map.Entry<String,Condition> entry : entries)
				{
					String arg_name = entry.getKey();
					Condition cond = entry.getValue();
					
					System.out.println(indent + "Argument '" + arg_name + "' is such that...");
					System.out.println(indent + cond);
					Condition type_cond = cond.getTypeCondition();
					// Is there a clear condition regarding the type of this argument?
					if (type_cond instanceof IsOfType)
					{
						Class<?> arg_clazz = ((IsOfType) type_cond).getType();
						if (Integer.class.isAssignableFrom(arg_clazz))
						{
							// Argument is an integer; cast it from the string
							System.out.println(indent + "Type in something for this argument: ");
							String line = reader.readLine();
							int i = Integer.parseInt(line.trim());
							map.put(arg_name, i);
						}
						else if (String.class.isAssignableFrom(arg_clazz))
						{
							// Argument is a string; put it directly
							System.out.println(indent + "Type in something for this argument: ");
							String line = reader.readLine();
							map.put(arg_name, line.trim());
						}
						else
						{
							// Don't know how to build this argument; try recursively
							Object o = buildObjectInteractively(reader, indent + "  ");
							map.put(arg_name, o);
						}
					}
				}
				System.out.println(indent + "We are done collecting the arguments for this object.");
				if (!c.satisfies(map))
				{
					System.out.println("Oh no! The arguments you typed in do not satisfy the conditions");
					System.out.println("to build a new instance of " + class_name + ". Let's try again.");
				}
				Object new_instance = ObjectBuilder.buildObject(clazz, map);
				return new_instance;
			}
		}
		return null;
	}

}
