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

package main;

import bscmail.*;
import bscmail.gui.*;
import java.io.*;
import java.util.*;
import javax.swing.JFrame;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Main program.
 * 
 * @author Wayne Miller
 */
public class Main {

    /**
     * Main program.  This method just executes {@link Main#run()}.
     *
     * @param args the command line arguments; not used
     */
    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }    // main(String[] args)

    private void convert() throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException {
        final String SHIFTS_XML_FILE = "shifts.xml";
        List<Shift> xmlShifts = getShifts(SHIFTS_XML_FILE);
        System.out.println(xmlShifts);
        
        final String SHIFTS_S_FILE = "shifts";
        writeShiftsS(xmlShifts, SHIFTS_S_FILE);
        List<Shift> sShifts = readShiftsS(SHIFTS_S_FILE);
        System.out.println(sShifts);

        System.out.println();
        
        final String MANAGERS_XML_FILE = "managers.xml";
        List<Manager> xmlManagers = getManagers(MANAGERS_XML_FILE);
        System.out.println(xmlManagers);
        
        final String MANAGERS_S_FILE = "managers";
        writeManagersS(xmlManagers, MANAGERS_S_FILE);
        List<Manager> sManagers = readManagersS(MANAGERS_S_FILE);
        System.out.println(sManagers);

        System.out.println();

        final String VOLUNTEERS_XML_FILE = "volunteers.xml";
        List<Volunteer> xmlVolunteers = getVolunteers(VOLUNTEERS_XML_FILE);
        System.out.println(xmlVolunteers);
        
        final String VOLUNTEERS_S_FILE = "volunteers";
        writeVolunteersS(xmlVolunteers, VOLUNTEERS_S_FILE);
        List<Volunteer> sVolunteers = readVolunteersS(VOLUNTEERS_S_FILE);
        System.out.println(sVolunteers);

        System.out.println();

    }    // convert()
    
    /**
     * Runs and tests the system.
     */
    private void run() {
        Application.setTestMode(false);
        JFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }    // run()

    /**
     * Returns a list of shifts read from an XML file.
     *
     * @param filename the name of the file; may not be null
     * @return a list containing all the shifts contained in {@code filename}
     * @throws FactoryConfigurationError if a {@link DocumentBuilderFactory}
     * cannot be instantiated
     * @throws IOException if an I/O error occurs
     * @throws ParserConfigurationException if a {@link DocumentBuilder} cannot
     * be instantiated
     * @throws SAXException if a parse error occurs are found
     */
    private List<Shift> getShifts(String filename) throws IOException, ParserConfigurationException, SAXException {
        assert (filename != null);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
        Document document = parser.parse(filename);
        Element root = document.getDocumentElement();
        NodeList shiftNodes = root.getElementsByTagName("shift");
        List<Shift> shifts = new LinkedList<>();
        for (int i = 0; i < shiftNodes.getLength(); ++i) {
            Element element = (Element) shiftNodes.item(i);
            String description = element.getTextContent();
            String isAngelShiftString = element.getAttribute("angel");
            boolean isAngelShift = Boolean.valueOf(isAngelShiftString);
            shifts.add(new Shift(description, isAngelShift));
        }    // for
        return shifts;
    }    // getShifts()

    private List<Shift> readShiftsS(String filename) throws IOException, ClassNotFoundException {
        assert (filename != null);
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename));
        return (List<Shift>)input.readObject();
    }    // readShiftsS()
    
    private void writeShiftsS(List<Shift> shifts, String filename) throws IOException {
        assert (shifts != null);
        assert (filename != null);
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename));
        output.writeObject(shifts);
    }    // writeShiftsS
    
    /**
     * Returns a list of managers read from an XML file.
     *
     * @param filename the name of the file; may not be null
     * @return a list containing all the managers contained in {@code filename}
     * @throws FactoryConfigurationError if a {@link DocumentBuilderFactory}
     * cannot be instantiated
     * @throws IOException if an I/O error occurs
     * @throws MalformedInputException if the input file is malformed
     * @throws ParserConfigurationException if a {@link DocumentBuilder} cannot
     * be instantiated
     * @throws SAXException if a parse error occurs are found
     */
    private List<Manager> getManagers(String filename) throws IOException, ParserConfigurationException, SAXException {
        assert (filename != null);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
        Document document = parser.parse(filename);
        Element root = document.getDocumentElement();
        NodeList managerNodes = root.getElementsByTagName("manager");
        List<Manager> managers = new LinkedList<>();
        for (int i = 0; i < managerNodes.getLength(); ++i) {
            Element element = (Element) managerNodes.item(i);
            String name = getChildText(element, "name");
            String email = getChildText(element, "email");
            String phone = getChildText(element, "phone");
            managers.add(new Manager(name, email, phone));
        }    // for
        return managers;
    }    // getManagers()

    private List<Manager> readManagersS(String filename) throws IOException, ClassNotFoundException {
        assert (filename != null);
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename));
        return (List<Manager>)input.readObject();
    }    // readManagersS()
    
    private void writeManagersS(List<Manager> managers, String filename) throws IOException {
        assert (managers != null);
        assert (filename != null);
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename));
        output.writeObject(managers);
    }    // writeManagersS

    /**
     * Returns a list of volunteers read from an XML file.
     *
     * @param filename the name of the file; may not be null
     * @return a list containing all the volunteers contained in
     * {@code filename}
     * @throws FactoryConfigurationError if a {@link DocumentBuilderFactory}
     * cannot be instantiated
     * @throws IOException if an I/O error occurs
     * @throws MalformedInputException if the input file is malformed
     * @throws ParserConfigurationException if a {@link DocumentBuilder} cannot
     * be instantiated
     * @throws SAXException if a parse error occurs are found
     */
    private List<Volunteer> getVolunteers(String filename) throws IOException, ParserConfigurationException, SAXException {
        assert (filename != null);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
        Document document = parser.parse(filename);
        Element root = document.getDocumentElement();
        NodeList volunteerNodes = root.getElementsByTagName("volunteer");
        List<Volunteer> volunteers = new LinkedList<>();
        for (int i = 0; i < volunteerNodes.getLength(); ++i) {
            Element element = (Element) volunteerNodes.item(i);
            String name = getChildText(element, "name");
            String email = getChildText(element, "email");
            String canAngelString = element.getAttribute("angel");
            boolean canAngel = Boolean.valueOf(canAngelString);
            volunteers.add(new Volunteer(name, email, canAngel));
        }    // for
        return volunteers;
    }    // getVolunteers()

    private List<Volunteer> readVolunteersS(String filename) throws IOException, ClassNotFoundException {
        assert (filename != null);
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename));
        return (List<Volunteer>)input.readObject();
    }    // readVolunteersS()
    
    private void writeVolunteersS(List<Volunteer> volunteers, String filename) throws IOException {
        assert (volunteers != null);
        assert (filename != null);
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename));
        output.writeObject(volunteers);
    }    // writeVolunteersS

    /**
     * Returns the text content of a named child node of an XML node.
     *
     * @param element the XML node; may not be null
     * @param tagName the name of the child node; may not be null
     * @return the text content of the child node; if multiple child nodes with
     * the name {@code name} are found, returns the text content of the first
     * child node found
     * @throws MalformedInputException if no child nodes with name {@code name}
     * are found
     */
    private String getChildText(Element element, String tagName) {
        assert (element != null);
        assert (tagName != null);
        NodeList children = element.getElementsByTagName(tagName);
        if (children.getLength() < 1) {
            throw new MalformedInputException("<" + tagName + "> tag not found");
        }    // if
        Node child = children.item(0);
        return child.getTextContent();
    }    // getChildText
}    // Main
