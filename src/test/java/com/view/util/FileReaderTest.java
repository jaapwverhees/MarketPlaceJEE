package com.view.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderTest {
    @Test
    void fileReaderCanReadFile(){
        assertEquals("testingString\n",FileReader.read("files/test"));
    }
}