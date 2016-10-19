/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
 *
 * This file is part of BSCMail.
 *
 * BSCMail is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BSCMail is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BSCMail.  If not, see <http://www.gnu.org/licenses/>.
 */

package iolayer;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Unit tests for {@link XMLIOLayer}.
 * 
 * @author Wayne Miller
 */
public class XMLIOLayerTest extends IOLayerTest {
    
    /**
     * Returns the I/O layer being tested.
     * 
     * @return the I/O layer being tested
     */
    @Override
    protected XMLIOLayer getIOLayer() {
        return new XMLIOLayer();
    }    // getIOLayer()
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("XMLIOLayer");
        System.out.println("==========");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass(

    /*
     * Unit tests
     */
    
    /**
     * Tests that {@link XMLIOLayer#XMLIOLayer()} does not throw
     * an exception.
     */
    @Test
    public void testConstructorNoException() {
        System.out.println("constructor - no exception");
        
        IOLayer ioLayer = new XMLIOLayer();
    }    // testConstructorNoException()

    /**
     * Tests that {@link XMLIOLayer#writeAll(Writer, List)} correctly
     * translates its argument to XML.
     */
    @Test
    public void testWriteAll() throws IOException, ParserConfigurationException, SAXException {
        System.out.println("writeAll");
        
        List<RWElement> readWritables = Arrays.asList(new RWElement("foo", 1), new RWElement("bar", 2), new RWElement("baz", 3));

        String expectedString = "<rwelement-list>\n";
        expectedString += "<rwelement><name>foo</name><value>1</value></rwelement>\n";
        expectedString += "<rwelement><name>bar</name><value>2</value></rwelement>\n";
        expectedString += "<rwelement><name>baz</name><value>3</value></rwelement>\n";
        expectedString += "</rwelement-list>";
        
        byte[] receivedBytes = null;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            IOLayer ioLayer = getIOLayer();
            ioLayer.writeAll(output, readWritables);
            receivedBytes = output.toByteArray();
        }    // try
        assert (receivedBytes != null);
        Document received = null;
        try (InputStream input = new ByteArrayInputStream(receivedBytes)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = factory.newDocumentBuilder();
            received = parser.parse(input);
        }    // try
        assert (received != null);
        String receivedString = new String(receivedBytes);
        
        expectedString = expectedString.replaceAll("\\s+", "");
        receivedString = receivedString.replaceAll("\\s+", "");
        assertEquals(expectedString, receivedString);
    }    // testWriteAll()

    /**
     * Tests that {@link XMLIOLayer#writeAll(Writer, List)} correctly
     * translates its argument to XML, when the argument is compound.
     */
    @Test
    public void testWriteAllCompound() throws IOException, ParserConfigurationException, SAXException {
        System.out.println("writeAll - compound argument");
        
        List<RWContainer> readWritables = Arrays.asList(
                new RWContainer("one", new RWElement("foo", 1)),
                new RWContainer("two", new RWElement("bar", 2)),
                new RWContainer("three", new RWElement("baz", 3))
        );    // readWritables

        String expectedString = "<rwcontainer-list>\n";
        expectedString += "<rwcontainer><name>one</name><element><name>foo</name><value>1</value></element></rwcontainer>\n";
        expectedString += "<rwcontainer><name>two</name><element><name>bar</name><value>2</value></element></rwcontainer>\n";
        expectedString += "<rwcontainer><name>three</name><element><name>baz</name><value>3</value></element></rwcontainer>\n";
        expectedString += "</rwcontainer-list>";
        
        byte[] receivedBytes = null;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            IOLayer ioLayer = getIOLayer();
            ioLayer.writeAll(output, readWritables);
            receivedBytes = output.toByteArray();
        }    // try
        assert (receivedBytes != null);
        Document received = null;
        try (InputStream input = new ByteArrayInputStream(receivedBytes)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = factory.newDocumentBuilder();
            received = parser.parse(input);
        }    // try
        assert (received != null);
        String receivedString = new String(receivedBytes);
        
        expectedString = expectedString.replaceAll("\\s+", "");
        receivedString = receivedString.replaceAll("\\s+", "");
        assertEquals(expectedString, receivedString);
    }    // testWriteAll()

}    // testWriteAllCompound
