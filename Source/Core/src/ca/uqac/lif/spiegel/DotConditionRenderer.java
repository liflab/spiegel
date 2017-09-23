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

import java.util.Map;

/**
 * Draws a nested structure of conditions as a tree.
 * @author Sylvain Hallé
 */
public class DotConditionRenderer 
{
	/**
	 * Variable kept to give unique IDs to each node in the tree.
	 * Is reset to 0 upon each new call to {@link #toDot(Condition) toDot()}.
	 */
	protected int m_idCounter = 0;
	
	/**
	 * Renders a nested structure of conditions as a tree.
	 * @param c The condition object corresponding to the root of the tree
	 * @return A string drawing a tree in DOT format
	 */
	public String toDot(Condition c)
	{
		m_idCounter = 1;
		StringBuilder out = new StringBuilder();
		out.append("digraph G {\n");
		out.append("0 [label=\"\",shape=none];\n");
		out.append(toDot(c, 0));
		out.append("}");
		return out.toString();
	}
	
	/**
	 * Renders a nested structure of conditions as a tree.
	 * @param c The condition object corresponding to the current node
	 *   in the tree being processed
	 * @param parent_id The ID of the parent node to the current node
	 *   in the tree
	 * @return A string drawing a subtree in DOT format
	 */
	protected String toDot(Condition c, int parent_id)
	{
		StringBuilder out = new StringBuilder();
		if (c instanceof IndependentConditions)
		{
			IndependentConditions ic = (IndependentConditions) c;
			int and_id = m_idCounter++;
			out.append(and_id).append(" [label=\"AND\"];\n");
			out.append(parent_id).append(" -> ").append(and_id).append(";\n");
			for (Map.Entry<String,Condition> entry : ic.entrySet())
			{
				int field_id = m_idCounter++;
				out.append(field_id).append(" [label=\"").append(entry.getKey()).append("\"];\n");
				out.append(and_id).append(" -> ").append(field_id).append(";\n");
				out.append(toDot(entry.getValue(), field_id));
			}
		}
		else if (c instanceof And)
		{
			And a = (And) c;
			int and_id = m_idCounter++;
			out.append(and_id).append(" [label=\"AND\"];\n");
			out.append(parent_id).append(" -> ").append(and_id).append(";\n");
			for (Condition in_c : a.getOperands())
			{
				out.append(toDot(in_c, and_id));
			}
		}
		else if (c instanceof Or)
		{
			Or a = (Or) c;
			int and_id = m_idCounter++;
			out.append(and_id).append(" [label=\"OR\"];\n");
			out.append(parent_id).append(" -> ").append(and_id).append(";\n");
			for (Condition in_c : a.getOperands())
			{
				out.append(toDot(in_c, and_id));
			}
		}
		else if (c instanceof InnerFieldCondition)
		{
			InnerFieldCondition ifc = (InnerFieldCondition) c;
			int and_id = m_idCounter++;
			out.append(and_id).append(" [label=\"").append(ifc.m_fieldClass.getName()).append("\"];\n");
			out.append(parent_id).append(" -> ").append(and_id).append(";\n");
			out.append(toDot(ifc.m_fieldCondition, and_id));
		}
		else if (c instanceof Not)
		{
			int and_id = m_idCounter++;
			out.append(and_id).append(" [label=\"NOT\"];\n");
			out.append(parent_id).append(" -> ").append(and_id).append(";\n");
			out.append(toDot(((Not)c).getCondition(), and_id));
		}
		else if (c instanceof IsNumeric)
		{
			int and_id = m_idCounter++;
			out.append(and_id).append(" [label=\"Number\"];\n");
			out.append(parent_id).append(" -> ").append(and_id).append(";\n");
		}
		else if (c instanceof IsText)
		{
			int and_id = m_idCounter++;
			out.append(and_id).append(" [label=\"Text\"];\n");
			out.append(parent_id).append(" -> ").append(and_id).append(";\n");
		}
		else if (c instanceof IntegerValueInInterval)
		{
			IntegerValueInInterval ivii = (IntegerValueInInterval) c;
			int and_id = m_idCounter++;
			out.append(and_id).append(" [label=\"In ").append(ivii.getValues()).append("\"];\n");
			out.append(parent_id).append(" -> ").append(and_id).append(";\n");
		}
		else
		{
			int and_id = m_idCounter++;
			out.append(and_id).append(" [shape=rect,label=\"Not buildable\"];\n");
			out.append(parent_id).append(" -> ").append(and_id).append(";\n");
		}
		return out.toString();
	}
}
