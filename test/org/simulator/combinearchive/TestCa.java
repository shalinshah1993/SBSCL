/**
 * This file is part of SBSCL Simulation Core Library, a Java-based library
 * Copyright (c) 2018 Martin Scharm <https://binfalse.de/contact/>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.simulator.combinearchive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import java.io.File;

import de.unirostock.sems.cbarchive.CombineArchive;

public class TestCa
{
    @Test
    public void testOpen () {
        try {
            CombineArchive ca = new CombineArchive (new File ("test/org/simulator/combinearchive/12859_2014_369_MOESM1_ESM.zip"));
            assertEquals ("expected exactly 2 entries in the combine archive", ca.getEntries ().size (), 2);
        }
        catch (Exception e) {
            fail ("failed to read combine archive");
        }
    }
}
