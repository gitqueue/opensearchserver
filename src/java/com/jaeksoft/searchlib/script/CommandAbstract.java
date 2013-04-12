/**   
 * License Agreement for OpenSearchServer
 *
 * Copyright (C) 2013 Emmanuel Keller / Jaeksoft
 * 
 * http://www.open-search-server.com
 * 
 * This file is part of OpenSearchServer.
 *
 * OpenSearchServer is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * OpenSearchServer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenSearchServer. 
 *  If not, see <http://www.gnu.org/licenses/>.
 **/

package com.jaeksoft.searchlib.script;

public abstract class CommandAbstract {

	private final CommandEnum commandEnum;

	private Object[] parameters;

	protected CommandAbstract(CommandEnum commandEnum) {
		this.commandEnum = commandEnum;
		this.parameters = null;
	}

	protected void checkParameters(int count, Object... parameters)
			throws ScriptException {
		if (count == 0)
			return;
		this.parameters = parameters;
		if (parameters == null)
			throwError("Missing parameters");
		if (parameters.length < count)
			throwError("Missing parameter(s)");
		for (int i = 0; i < count; i++)
			if (parameters[i] == null)
				throwError("The parameter is empty: " + (i + 1));
	}

	protected String getParameterString(int pos) {
		if (pos >= parameters.length)
			return null;
		Object p = parameters[pos];
		if (p == null)
			return null;
		return p.toString();
	}

	protected void throwError(String message) throws ScriptException {
		throw new ScriptException("Command " + commandEnum.name() + " - "
				+ message);
	}

	public abstract void run(ScriptCommandContext context, String id,
			Object... parameters) throws ScriptException;
}