/* Copyright (c) 2012-2013 Jesper Öqvist <jesper@llbit.se>
 *
 * This file is part of Chunky.
 *
 * Chunky is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Chunky is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with Chunky.  If not, see <http://www.gnu.org/licenses/>.
 */
package se.llbit.chunky.renderer.scene;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test scene name sanitizing
 * @author Jesper Öqvist <jesper@llbit.se>
 */
public class TestSceneManager {
	/**
	 * Sensitive characters are replaced by underscore
	 */
	@Test
	public void testSceneNameSanitizing_1() {
		assertEquals("ab_cd", SceneManager.sanitizedSceneName("ab/cd"));
		assertEquals("x___cd", SceneManager.sanitizedSceneName("x<>|cd"));
		assertEquals("______#42", SceneManager.sanitizedSceneName(":*?\\\"/#42"));
	}

	/**
	 * Whitespace is trimmed
	 */
	@Test
	public void testSceneNameSanitizing_2() {
		assertEquals("foo bar", SceneManager.sanitizedSceneName(" foo bar   "));
		assertEquals("foo bar", SceneManager.sanitizedSceneName(" \nfoo bar\t "));
	}

	/**
	 * Control characters are stripped
	 */
	@Test
	public void testSceneNameSanitizing_3() {
		assertEquals("xyzabc", SceneManager.sanitizedSceneName("xyz\u007fabc"));
		assertEquals("12", SceneManager.sanitizedSceneName("1\u009f2"));
		assertEquals("AZ", SceneManager.sanitizedSceneName("A\u0000\u0001\u001fZ"));
	}

	/**
	 * Empty names are replaced by 'Scene'
	 */
	@Test
	public void testSceneNameSanitizing_4() {
		assertEquals("Scene", SceneManager.sanitizedSceneName(""));
		assertEquals("Scene", SceneManager.sanitizedSceneName("   "));
		assertEquals("Scene", SceneManager.sanitizedSceneName("\t\n\b\r"));
		assertEquals("Scene", SceneManager.sanitizedSceneName("\u0080"));
	}
}
