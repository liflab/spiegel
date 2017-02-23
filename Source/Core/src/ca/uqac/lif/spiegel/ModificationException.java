/*
    Spiegel, a library for manipulating objects with objects
    Copyright (C) 2017 Sylvain Hall�

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
 * Exception thrown when an object cannot be modified
 * @author Sylvain Hall�
 */
public class ModificationException extends Exception
{
	/**
	 * Dummy UID
	 */
	private static final long serialVersionUID = 1L;
	
	public ModificationException(Throwable t)
	{
		super(t);
	}
	
	public ModificationException(String message)
	{
		super(message);
	}
}
