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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import main.ReadWritable;
import main.ReadWritableFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * An I/O layer that reads and writes {@link ReadWritable}s to and from an XML
 * file.  The data persists in the file, beyond the lifetime of the object.
 * 
 * @author Wayne Miller
 * @param <T> the type of read-writable managed by this I/O layer
 * @since 2.1
 */
public class XMLIOLayer<T extends ReadWritable> implements IOLayer<T> {

    /**
     * The pathname of the XML file used to store the read-writables
     */
    private final String pathname;

    /**
     * The factory used to create read-writables from property maps.
     */
    private final ReadWritableFactory<T> factory;

    /**
     * Constructs a new XML file I/O layer. The pathname of the XML file and the
     * factory used to create the read-writables are supplied as parameters. It
     * is the responsibility of the programmer to ensure that both parameters
     * make sense. (Isn't it always?)
     *
     * @param pathname the pathname of the XML file used to store the
     * read-writables; may not be null
     * @param factory the factory used to construct read-writables; may not be
     * null
     * @throws NullPointerException if either parameter is null
     */
    public XMLIOLayer(String pathname, ReadWritableFactory<T> factory) {
        if (pathname == null) {
            throw new NullPointerException("pathname may not be null");
        }    // if
        if (factory == null) {
            throw new NullPointerException("factory may not be null");
        }    // if

        this.pathname = pathname;
        this.factory = factory;
        assertInvariant();
    }    // XMLIOLayer

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() throws IOException {
        assertInvariant();
        FileInputStream inputStream = new FileInputStream(pathname);

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = documentBuilderFactory.newDocumentBuilder();
            Document document = parser.parse(inputStream);

            Element inventory = document.getDocumentElement();
            List<T> readWritables = new LinkedList<>();
            for (Node element = inventory.getFirstChild(); element != null; element = element.getNextSibling()) {
                Map<String, Object> properties = nodeToPropertyMap(element);
                if (! properties.isEmpty()) {
                    readWritables.add(factory.constructReadWritable(properties));
                }    // if
            }    // for element

            assertInvariant();
            return readWritables;
        } catch (ParserConfigurationException e) {    // try
            throw new IOException("Unable to configure XML parser", e);
        } catch (SAXException e) {    // catch
            throw new IOException("Unable to parse file", e);
        }    // catch
    }    // getAll()

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAll(List<T> list) throws IOException {
        assertInvariant();
        if (list == null) {
            throw new NullPointerException("list may not be null");
        }    // if
        if (list.contains(null)) {
            throw new NullPointerException("list may not contain null");
        }    // if
        
        String elementName;
        if (list.isEmpty()) {
            elementName = "element";
        } else {    // if
            ReadWritable element = list.get(0);
            elementName = element.getClass().getSimpleName().toLowerCase();
        }    // else
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement(pluralize(elementName));
            document.appendChild(root);
            
            for (ReadWritable readWritable : list) {
                Element element = createElement(document, elementName, readWritable);
                root.appendChild(element);
            }    // for

            FileOutputStream outputStream = new FileOutputStream(pathname);
            writeDocument(document, outputStream);
        } catch (ParserConfigurationException e) {    // try
            throw new IOException("Unable to configure XML parser", e);
        } catch (TransformerConfigurationException e) {    // catch
            throw new IOException("Unable to configure XML transformer", e);
        } catch (TransformerException e) {    // catch
            throw new IOException("Unrecoverable error during XML transformation", e);
        }    // catch
    }
    
    /**
     * Converts a DOM node into a read-writable property map.
     *
     * @param node the DOM node; may not be null
     * @return a read-writable property map corresponding to the values of
     * {@code node}
     */
    private Map<String, Object> nodeToPropertyMap(Node node) {
        assert (node != null);
        Map<String, Object> properties = new LinkedHashMap<>();
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element property = (Element) child;
                assert (property != null);
                String key = property.getTagName();
                Object value;
                if (nodeHasElementChildren(property)) {
                    value = nodeToPropertyMap(property);
                } else {    // if
                    value = property.getTextContent();
                }    // else
                properties.put(key, value);
            }    // if
        }    // for property

        return properties;
    }    // nodeToPropertyMap()

    /**
     * Returns true if the given DOM node has children that are DOM elements.
     * 
     * @param node the DOM node; may not be null
     * @return true if {@code node} has children that are DOM elements;; false
     * otherwise
     */
    private boolean nodeHasElementChildren(Node node) {
        assert (node != null);
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                return true;
            }    // if
        }    // for
        return false;
    }    // nodeHasElementChildren()
    
    /**
     * Returns an element for the given XML document created from the given
     * read-writable.
     *
     * @param document the document; may not be null
     * @param elementName the name of the new element; may not be null
     * @param readWritable the read-writable from which the element will be
     * created; may not be null
     * @return an element created from {@code readWritable}
     */
    private Element createElement(Document document, String elementName, ReadWritable readWritable) {
        assert (document != null);
        assert (elementName != null);
        assert (readWritable != null);

        Element element = document.createElement(elementName);
        Map<String, Object> properties = readWritable.getReadWritableProperties();
        for (String key : properties.keySet()) {
            Object value = properties.get(key);
            Element property;
            if (value instanceof ReadWritable) {
                property = createElement(document, key, (ReadWritable)value);
            } else {    // if
                property = document.createElement(key);
                property.setTextContent(value.toString());
            }    // else
            element.appendChild(property);
        }    // for

        return element;
    }    // createElement()
    
    /**
     * Returns the plural form of a noun, for the purposes of writing to an
     * XML file.
     *
     * @param noun; may not be null
     * @return a string containing the plural form of the contents of
     * {@code noun}
     */
    private String pluralize(String noun) {
        assert (noun != null);
        
        if (noun.isEmpty()) {
            return noun;
        }    // if
        
        char lastChar = noun.toLowerCase().charAt(noun.length() - 1);
        return noun + "-list";
    }    // pluralize()
    
    /**
     * Writes an XML document to an output stream.
     *
     * @param document the XML document; may not be null
     * @param outputStream the outputStream; may not be null
     * @throws TransformerConfigurationException if it is not possible to create
     * a transformer instance
     * @throws TransformerException if an unrecoverable error occurs during the
     * course of the transformation
     */
    private void writeDocument(Document document, OutputStream outputStream) throws TransformerConfigurationException, TransformerException {
        assert (document != null);
        assert (outputStream != null);
        
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        Source source = new DOMSource(document);
        
        Result result = new StreamResult(outputStream);
        transformer.transform(source, result);
    }    // writeDocument()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (pathname != null);
        assert (factory != null);
    }    // assertInvariant()
}    // XMLIOLayer
