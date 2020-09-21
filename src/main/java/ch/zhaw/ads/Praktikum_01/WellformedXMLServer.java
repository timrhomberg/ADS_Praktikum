package ch.zhaw.ads.Praktikum_01;

import ch.zhaw.ads.CommandExecutor;

/**
 * ch.zhaw.ads.Praktikum_01.BracketServer -- Praktikum Einführung in Algorithmen
 * Aufgabe 4 - XML Wellformed Tester
 *
 * @author Tim Rhomberg
 * @version 1.0, 17.09.2000
 */
public class WellformedXMLServer implements CommandExecutor {
    @Override
    public String execute(String command) throws Exception {
        if (checkWellformed(command)) {
            return "XML File correct formatted :)!\n";
        } else {
            return "Error: XML File NOT CORRECT formatted!\n";
        }
    }

    /**
     * In the first Step it checks if the XML Header is available.
     * @param arg
     * @return
     */
    public boolean checkWellformed(String arg) {
        ListStack listStack = new ListStack(arg.length());

        return true;
    }
}
