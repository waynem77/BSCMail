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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import main.ReadWritableFactory;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link XMLIOLayer}.
 * 
 * @author Wayne Miller
 */
public class XMLIOLayerTest extends IOLayerTest {

    /**
     * File used to store XML data for tests.
     */
    private File tempfile;

    /**
     * The initial data that should be stored in the I/O layer.
     */
    private final List<RWElement> seedList = Arrays.asList(
            new RWElement("foo", 1),
            new RWElement("bar", 2),
            new RWElement("baz", 3));

    /**
     * The initial XML used to seed the XML I/O layer.  This should correspond
     * to the list in {@link #seedList}.
     */
    private final String seedXML = "<rwelement-list>\n"
            + "<rwelement><name>foo</name><value>1</value></rwelement>\n"
            + "<rwelement><name>bar</name><value>2</value></rwelement>\n"
            + "<rwelement><name>baz</name><value>3</value></rwelement>\n"
            + "</rwelement-list>\n";

    /**
     * Returns the XML I/O layer being tested.
     * 
     * @return the XML I/O layer being tested
     * @throws IOException if an I/O exception occurs while constructing the
     * I/O layer
     */
    @Override
    protected XMLIOLayer<RWElement> getIOLayer() throws IOException {
        return new XMLIOLayer<>(tempfile.getCanonicalPath(), RWElement.getFactory());
    }    // getIOLayer()

    /**
     * Returns true, indicating that the data persists beyond the lifetime of
     * the XML I/O layer being tested.
     *
     * @return true
     */
    @Override
    protected boolean dataIsPersistent() {
        return true;
    }    // dataIsPersistemt()
    
    /**
     * Creates a temporary file, marks it to be deleted upon application exit,
     * and places a reference to it in {@link tempfile}.
     */
    @Before
    public void setUpFileStorage() throws IOException {
        tempfile = File.createTempFile("bscmailtest", null);
        Writer writer = new FileWriter(tempfile);
        writer.write(seedXML);
        writer.flush();
    }    // setUpFileStorage()

    /**
     * Sets tempfile to null.
     */
    @After
    public void removeFileStorage() {
        tempfile.delete();
        tempfile = null;
    }    // removeFileStorage()

    /*
     * Unit tests
     */

    /**
     * Tests that
     * {@link XMLIOLayer#XMLIOLayer(java.lang.String, main.ReadWritableFactory)}
     * throws a {@link NullPointerException} when pathname is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenPathnameIsNull() {
        String pathname = null;
        ReadWritableFactory<RWElement> factory = RWElement.getFactory();

        IOLayer<RWElement> ioLayer = new XMLIOLayer<>(pathname, factory);
    }    // constructorThrowsExceptionWhenPathnameIsNull()

    /**
     * Tests that
     * {@link XMLIOLayer#XMLIOLayer(java.lang.String, main.ReadWritableFactory)}
     * throws a {@link NullPointerException} when factory is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenFactoryIsNull() throws IOException {
        String pathname = tempfile.getCanonicalPath();
        ReadWritableFactory<RWElement> factory = null;

        IOLayer<RWElement> ioLayer = new XMLIOLayer<>(pathname, factory);
    }    // constructorThrowsExceptionWhenFactoryIsNull()

    /**
     * Tests that
     * {@link XMLIOLayer#XMLIOLayer(java.lang.String, main.ReadWritableFactory)}
     * does not throw an exception when no parameter is null.
     */
    @Test
    public void constructorDoesNotThrowAnExceptionWhenNoParameterIsNull() throws IOException {
        String pathname = tempfile.getCanonicalPath();
        ReadWritableFactory<RWElement> factory = RWElement.getFactory();

        IOLayer<RWElement> ioLayer = new XMLIOLayer<>(pathname, factory);
    }    // constructorDoesNotThrowAnExceptionWhenNoParameterIsNull()

    /**
     * Tests that {@link XMLIOLayer} correctly processes the XML file.
     */
    public void layerCorrectlyProcessesXML() throws IOException {
        String pathname = tempfile.getCanonicalPath();
        ReadWritableFactory<RWElement> factory = RWElement.getFactory();
        IOLayer<RWElement> ioLayer = new XMLIOLayer<>(pathname, factory);

        List<RWElement> received = ioLayer.getAll();

        List<RWElement> expected = seedList;
        assertEquals(expected, received);
    }    // layerCorrectlyProcessesXML()

    /**
     * Tests that {@link XMLIOLayer#setAll(java.util.List)} correctly
     * outputs XML.
     */
    @Test
    public void setAllSavesXML() throws IOException {
        String pathname = tempfile.getCanonicalPath();
        ReadWritableFactory<RWElement> factory = RWElement.getFactory();
        IOLayer<RWElement> ioLayer = new XMLIOLayer<>(pathname, factory);
        List<RWElement> list = Arrays.asList(new RWElement("aaa", 10), new RWElement("bbb", 100));

        ioLayer.setAll(list);

        String expected = "<rwelement-list>\n"
                + "<rwelement><name>aaa</name><value>10</value></rwelement>\n"
                + "<rwelement><name>bbb</name><value>100</value></rwelement>\n"
                + "</rwelement-list>";
        BufferedReader reader = new BufferedReader(new FileReader(tempfile));
        String received = "";
        for (String line; (line = reader.readLine()) != null; ) {
            received += line;
        }    // for
        expected = expected.replaceAll("\\s+", "");    // Place in a sort of canonical form.
        received = received.replaceAll("\\s+", "");    // Ditto.
        assertEquals(expected, received);
    }    // setAllSavesXML()
}    // testWriteAllCompound
