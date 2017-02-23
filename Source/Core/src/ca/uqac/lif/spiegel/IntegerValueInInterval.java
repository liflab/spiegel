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

import java.util.ArrayList;
import java.util.Collection;

/**
 * Stipulates that an element of the object map must have a value
 * in a set of integers, specified by its minimum and maximum bounds 
 * and increment step.
 * @author Sylvain Hallé
 */
public class IntegerValueInInterval extends ValueInSet
{
	public IntegerValueInInterval(String argument, int min, int max, int step)
	{
		super(argument, createCollection(min, max, step));
	}
	
	protected static Collection<Object> createCollection(int min, int max, int step)
	{
		Collection<Object> col = new ArrayList<Object>((max - min / step + 1));
		for (int n = min; n <= max; n += step)
		{
			col.add(n);
		}
		return col;
	}
}
