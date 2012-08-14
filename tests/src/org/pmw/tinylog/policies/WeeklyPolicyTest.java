/*
 * Copyright 2012 Martin Winandy
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package org.pmw.tinylog.policies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests for weekly policy.
 * 
 * @see WeeklyPolicy
 */
public class WeeklyPolicyTest extends AbstractTimeBasedTest {

	/**
	 * Test rolling after one week.
	 */
	@Test
	public final void testRollingAfterOneDay() {
		setTime(DAY);

		IPolicy policy = new WeeklyPolicy();
		assertTrue(policy.check(null, null));
		increaseTime(DAY * 7 - 1L);
		assertTrue(policy.check(null, null));
		increaseTime(1L);
		assertFalse(policy.check(null, null));

		policy.reset();
		assertTrue(policy.check(null, null));
		increaseTime(DAY * 7 - 1L);
		assertTrue(policy.check(null, null));
		increaseTime(1L);
		assertFalse(policy.check(null, null));
	}

	/**
	 * Test rolling at the end of the week.
	 */
	@Test
	public final void testRollingAtEndOfWeek() {
		setTime(DAY);

		IPolicy policy = new WeeklyPolicy(7);
		assertTrue(policy.check(null, null));
		increaseTime(DAY * 2 - 1L);
		assertTrue(policy.check(null, null));
		increaseTime(1L);
		assertFalse(policy.check(null, null));

		policy.reset();
		assertTrue(policy.check(null, null));
		increaseTime(DAY * 7 - 1L);
		assertTrue(policy.check(null, null));
		increaseTime(1L);
		assertFalse(policy.check(null, null));
	}

	/**
	 * Test String parameter.
	 */
	@Test
	public final void testStringParameter() {
		setTime(DAY * 4L); // Monday

		AbstractTimeBasedPolicy policy = new WeeklyPolicy("1");
		assertEquals(DAY * 4L + DAY * 7L, getCalendar(policy).getTimeInMillis());

		policy = new WeeklyPolicy("7");
		assertEquals(DAY * 4L + DAY * 6L, getCalendar(policy).getTimeInMillis());

		policy = new WeeklyPolicy("monday");
		assertEquals(DAY * 4L + DAY * 7L, getCalendar(policy).getTimeInMillis());

		policy = new WeeklyPolicy("Tuesday");
		assertEquals(DAY * 4L + DAY * 1L, getCalendar(policy).getTimeInMillis());

		policy = new WeeklyPolicy("WEDNESDAY");
		assertEquals(DAY * 4L + DAY * 2L, getCalendar(policy).getTimeInMillis());

		policy = new WeeklyPolicy("thursDay");
		assertEquals(DAY * 4L + DAY * 3L, getCalendar(policy).getTimeInMillis());

		policy = new WeeklyPolicy("friday");
		assertEquals(DAY * 4L + DAY * 4L, getCalendar(policy).getTimeInMillis());

		policy = new WeeklyPolicy("saturday");
		assertEquals(DAY * 4L + DAY * 5L, getCalendar(policy).getTimeInMillis());

		policy = new WeeklyPolicy("sunday");
		assertEquals(DAY * 4L + DAY * 6L, getCalendar(policy).getTimeInMillis());

		try {
			policy = new WeeklyPolicy("");
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException ex) {
			assertEquals(IllegalArgumentException.class, ex.getClass());
		}

		try {
			policy = new WeeklyPolicy("0");
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException ex) {
			assertEquals(IllegalArgumentException.class, ex.getClass());
		}

		try {
			policy = new WeeklyPolicy("8");
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException ex) {
			assertEquals(IllegalArgumentException.class, ex.getClass());
		}
	}

	/**
	 * Test exception for dayOfWeek = 0.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testTooLowDay() {
		new WeeklyPolicy(0);
	}

	/**
	 * Test exception for dayOfWeek = 8.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testTooHighDay() {
		new WeeklyPolicy(8);
	}

}
