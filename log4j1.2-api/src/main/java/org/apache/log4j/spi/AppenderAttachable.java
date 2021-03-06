/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.log4j.spi;

import java.util.Enumeration;

import org.apache.log4j.Appender;

/**
 * Interface for attaching appenders to objects.
 * 
 * @author Ceki G&uuml;lc&uuml;
 * @since 0.9.1
 */
public interface AppenderAttachable {

	/**
	 * Add an appender.
	 * 
	 * @param newAppender
	 *            Appender to add
	 */
	void addAppender(Appender newAppender);

	/**
	 * Get all previously added appenders as an Enumeration.
	 * 
	 * @return All registered appenders
	 */
	@SuppressWarnings("rawtypes")
	Enumeration getAllAppenders();

	/**
	 * Get an appender by name.
	 * 
	 * @param name
	 *            Name of the wanted appender
	 * 
	 * @return Found appender
	 */
	Appender getAppender(String name);

	/**
	 * Returns {@code true} if the specified appender is in list of attached attached, {@code false} otherwise.
	 * 
	 * @param appender
	 *            Appender instance
	 * @return {@code true} if attached, {@code false} if not
	 * 
	 * @since 1.2
	 */
	boolean isAttached(Appender appender);

	/**
	 * Remove all previously added appenders.
	 */
	void removeAllAppenders();

	/**
	 * Remove the appender passed as parameter from the list of appenders.
	 * 
	 * @param appender
	 *            Appender instance
	 */
	void removeAppender(Appender appender);

	/**
	 * Remove the appender with the name passed as parameter from the list of appenders.
	 * 
	 * @param name
	 *            Name of appender to remove
	 */
	void removeAppender(String name);

}
