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
import java.util.Set;

public class ConditionBuilder
{
	public Condition getCondition(Class<?> clazz)
	{
		if (Integer.class.isAssignableFrom(clazz))
		{
			return IsPrimitive.instance;
		}
		if (!ObjectBuilder.isBuildable(clazz))
		{
			return IsNotBuildable.instance;
		}
		Condition c = ObjectBuilder.getBuildCondition(clazz);
		return expandCondition(c);
	}
	
	protected Condition expandCondition(Condition c)
	{
		if (c instanceof IndependentConditions)
		{
			IndependentConditions new_c = new IndependentConditions();
			Set<Map.Entry<String,Condition>> entries = ((IndependentConditions) c).entrySet();
			for (Map.Entry<String,Condition> entry : entries)
			{
				String member_field = entry.getKey();
				Condition member_condition = entry.getValue();
				Condition type_cond = member_condition.getTypeCondition();
				if (type_cond instanceof IsOfType)
				{
					Class<?> member_class = ((IsOfType) type_cond).getType();
					Condition new_member_condition = new InnerFieldCondition(member_field, member_class, expandCondition(member_condition));
					new_c.setConditionFor(member_field, new_member_condition);
				}				
			}
			return new_c;
		}
		else if (c instanceof And)
		{
			And new_c = new And();
			for (Condition child : ((And) c).getOperands())
			{
				Condition new_child = expandCondition(child);
				if (new_child != null)
					new_c.addOperand(expandCondition(child));
			}
			if (new_c.getOperands().isEmpty())
				return null;
			if (new_c.getOperands().size() == 1)
			{
				return new_c.getOperands().get(0);
			}
			return new_c;
		}
		else if (c instanceof Or)
		{
			Or new_c = new Or();
			for (Condition child : ((Or) c).getOperands())
			{
				Condition new_child = expandCondition(child);
				if (new_child != null)
					new_c.addOperand(expandCondition(child));
			}
			if (new_c.getOperands().isEmpty())
				return null;
			if (new_c.getOperands().size() == 1)
			{
				return new_c.getOperands().get(0);
			}
			return new_c;
		}
		else if (c instanceof Not)
		{
			Condition child = ((Not) c).getCondition();
			Condition new_child = expandCondition(child);
			if (new_child == null)
			{
				// Nothing to negate
				return null;
			}
			Not new_c = new Not(new_child);
			return new_c;
		}
		else if (c instanceof IsOfType)
		{
			return null;
		}
		return c;
	}
}
