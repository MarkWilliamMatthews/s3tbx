/*
 *
 *  * Copyright (C) 2012 Brockmann Consult GmbH (info@brockmann-consult.de)
 *  *
 *  * This program is free software; you can redistribute it and/or modify it
 *  * under the terms of the GNU General Public License as published by the Free
 *  * Software Foundation; either version 3 of the License, or (at your option)
 *  * any later version.
 *  * This program is distributed in the hope that it will be useful, but WITHOUT
 *  * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *  * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 *  * more details.
 *  *
 *  * You should have received a copy of the GNU General Public License along
 *  * with this program; if not, see http://www.gnu.org/licenses/
 *
 */

package org.esa.s3tbx.olci.radiometry.rayleighcorrection;

import org.junit.Ignore;
import org.junit.Test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author muhammad.bc.
 */
@Ignore
public class AuxiliaryValuesTest {

    private AuxiliaryValues auxiliaryValues;
    private double[] angleOne = {1, 2, 3};
    private double[] angleTwo = {4, 5, 6};

/*    @Before
    public void setUp() throws Exception {
        auxiliaryValues = new AuxiliaryValues();
        auxiliaryValues.setSunZenithAngles(angleOne, sourceTile);
        auxiliaryValues.setViewZenithAngles(angleOne);
        auxiliaryValues.setSunAzimuthAngles(angleOne);
        auxiliaryValues.setViewAzimuthAngles(angleTwo);
        auxiliaryValues.setViewZenithAnglesRad();
        auxiliaryValues.setSunZenithAnglesRad();
        auxiliaryValues.setViewAzimuthAnglesRad();
        auxiliaryValues.setSunAzimuthAnglesRad();
        auxiliaryValues.setCosOZARads();
        auxiliaryValues.setCosSZARads();

        auxiliaryValues.setLatitudes(angleOne, sourceTile);
        auxiliaryValues.setLongitude(angleOne);
    }*/

    @Test
    public void testGetCosSZA() throws Exception {
        assertArrayEquals(angleOne, auxiliaryValues.getSunZenithAngles(), 1e-8);
        assertArrayEquals(new double[]{0.9998476951563913, 0.9993908270190958, 0.9986295347545738}, auxiliaryValues.getCosSZARads(), 1e-8);
    }

    @Test
    public void testGetSZARad() throws Exception {
        assertArrayEquals(angleOne, auxiliaryValues.getSunZenithAngles(), 1e-8);
        assertArrayEquals(new double[]{0.017453292519943295, 0.03490658504, 0.05235987756}, auxiliaryValues.getSunZenithAnglesRad(), 1e-8);
    }

    @Test
    public void testGetAirMass() throws Exception {
        auxiliaryValues.setAirMass();
        assertArrayEquals(new double[]{2.0003046560878155, 2.0012190885976433, 2.002744691995842}, auxiliaryValues.getAirMass(), 1e-8);
    }

    @Test
    public void testAziDiff() throws Exception {
        auxiliaryValues.setAziDifferent();
        assertArrayEquals(new double[]{0.05235987755983066, 0.05235987755983066, 0.05235987755983066}, auxiliaryValues.getAziDifferent(), 1e-8);
    }

    @Test
    public void testCreateLineSpaceOfArrayElements() throws Exception {
        double[] lineSpace = auxiliaryValues.getLineSpace(0, 10, 5);
        assertNotNull(lineSpace);
        assertEquals(5, lineSpace.length);
        assertArrayEquals(new double[]{0.0, 2.5, 5.0, 7.5, 10.0}, lineSpace, 1e-8);
        lineSpace = auxiliaryValues.getLineSpace(0, 1, 5);
        assertEquals(5, lineSpace.length);
        assertArrayEquals(new double[]{0.0, 0.25, 0.5, 0.75, 1.0}, lineSpace, 1e-8);

        lineSpace = auxiliaryValues.getLineSpace(0, 0, 5);
        assertEquals(5, lineSpace.length);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0, 0.0}, lineSpace, 1e-8);

        try {
            lineSpace = auxiliaryValues.getLineSpace(0, 10, -5);
            fail("Array cant have negative index");
        } catch (NegativeArraySizeException ex) {

        }
    }

    @Test
    public void Spike() throws Exception {
        int f = 2;
        System.out.println("++f = " + f);

    }
}