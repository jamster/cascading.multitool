/*
 * Copyright (c) 2007-2009 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.cascading.org/
 *
 * This file is part of the Cascading project.
 *
 * Cascading is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cascading is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cascading.  If not, see <http://www.gnu.org/licenses/>.
 */

package multitool.factory;

import java.util.Map;

import cascading.operation.regex.RegexReplace;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;

/**
 *
 */
public class ReplaceFactory extends PipeFactory
  {
  public ReplaceFactory( String alias )
    {
    super( alias );
    }

  public String getUsage()
    {
    return "apply replace the regex";
    }

  public String[] getParameters()
    {
    return new String[]{"replace", "replaceAll"};
    }

  public String[] getParametersUsage()
    {
    return new String[]{"replacement string", "true if pattern should be applied more than once"};
    }

  public Pipe addAssembly( String value, Map<String, String> subParams, Map<String, Pipe> pipes, Pipe pipe )
    {
    String replace = getString( subParams, "replace", "" );
    boolean replaceAll = getBoolean( subParams, "replaceAll", false );


    return new Each( pipe, Fields.FIRST, new RegexReplace( Fields.UNKNOWN, value, replace, replaceAll ) );
    }
  }