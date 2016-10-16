/*
 * Copyright © 2015 Wayne Miller
 */

package iolayer;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import main.*;
import org.w3c.dom.*;

/**
 * An I/O layer that reads and writes {@link ReadWritable}s to and from XML
 * files.
 * 
 * @author Wayne Miller
 * @since 2.1
 */
public class XMLIOLayer implements IOLayer {

    /**
     * Constructs a list of read-writables from an XML input stream using the
     * given read-writable factory. The programmer must ensure that the given
     * factory makes sense for the given file.
     *
     * If the I/O layer is unable to create a read-writable from the input
     * stream, it will insert a null into the appropriate place in the list.  If
     * the I/O layer is unable to create any read-writables, it will return
     * null.
     *
     * @param <T> the type of read-writable to construct
     * @param input the input stream; may not be null
     * @param factory the read-writable factory to use; may not be null
     * @return a list of read-writables constructed from {@code filename} using
     * {@code factory}, or null if no read-writables could be constructed
     * @throws NullPointerException if either parameter is null
     * @throws IOException if an I/O error occurs
     */
    @Override
    public <T extends ReadWritable> List<T> readAll(InputStream input, ReadWritableFactory<T> factory) throws IOException {
        if (input == null) {
            throw new NullPointerException("input may not be null");
        }    // if
        if (factory == null) {
            throw new NullPointerException("factory may not be null");
        }    // if

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = documentBuilderFactory.newDocumentBuilder();
            Document document = parser.parse(input);

            Element inventory = document.getDocumentElement();

            List<T> readWritables = new LinkedList<>();
            for (Node element = inventory.getFirstChild(); element != null; element = element.getNextSibling()) {
                Map<String, Object> properties = nodeToPropertyMap(element);
                if (! properties.isEmpty()) {
                    readWritables.add(factory.constructReadWritable(properties));
                }    // if
            }    // for element
            
            return readWritables;
        } catch (ParserConfigurationException e) {    // try
            throw new IOException("Unable to configure XML parser", e);
        } catch (Exception e) {    // catch
            System.out.println("Something went wrong!  " + e);
            return null;
        }    // catch
    }    // readAll()

    /**
     * Writes the given list of read-writables as XML to an output stream.
     *
     * @param output the output stream; may not be null
     * @param readWritables the list of read-writables; may not be null, nor
     * contain any null elements
     * @throws NullPointerException if either parameter is null, or if
     * {@code readWritables} contains a null element
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void writeAll(OutputStream output, List<? extends ReadWritable> readWritables) throws IOException {
        if (output == null) {
            throw new NullPointerException("output may not be null");
        }    // if
        if (readWritables == null) {
            throw new NullPointerException("readWritables may not be null");
        }    // if
        if (readWritables.contains(null)) {
            throw new NullPointerException("readWritables may not contain null");
        }    // if
        
        String elementName;
        if (readWritables.isEmpty()) {
            elementName = "element";
        } else {    // if
            ReadWritable element = readWritables.get(0);
            elementName = element.getClass().getSimpleName().toLowerCase();
        }    // else
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement(pluralize(elementName));
            document.appendChild(root);
            
            for (ReadWritable readWritable : readWritables) {
                Element element = createElement(document, elementName, readWritable);
                root.appendChild(element);
            }    // for

            writeDocument(document, output);
        } catch (ParserConfigurationException e) {    // try
            throw new IOException("Unable to configure XML parser", e);
        } catch (TransformerConfigurationException e) {    // catch
            throw new IOException("Unable to configure XML transformer", e);
        } catch (TransformerException e) {    // catch
            throw new IOException("Unrecoverable error during XML transformation", e);
        }    // catch
     }    // writeAll()
    
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
                    System.out.println("\tRecurse!");
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

}    // XMLIOLayer
