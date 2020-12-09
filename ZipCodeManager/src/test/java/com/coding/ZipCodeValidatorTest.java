package com.coding;

import org.junit.*;

public class ZipCodeValidatorTest {

    ZipCodeValidator uut;

    @Before
    public void setup(){
        uut = new ZipCodeValidator();
    }
    @Test
    public void zipCodeManager() {
        String[] args = {"[94133,94133]","[94200,94299]","[94226,94399]"};

        String expected = "[94133,94133][94200,94399]";
        String actual = uut.zipCodeManager(args);

        Assert.assertEquals(expected, actual);
    }
}