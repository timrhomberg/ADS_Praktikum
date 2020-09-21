package ch.zhaw.ads.Praktikum_01;

import ch.zhaw.ads.CommandExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ch.zhaw.ads.Praktikum_01.BracketServer -- Praktikum Einführung in Algorithmen
 * Aufgabe 4 - XML Wellformed Tester
 *
 * @author Tim Rhomberg
 * @version 1.0, 17.09.2000
 */
public class WellformedXMLServer implements CommandExecutor {
    private char[] argumentChars;
    private int currentIndex;
    private static final List<Character> openBrackets = new ArrayList<>();
    private static final List<Character> closeBrackets = new ArrayList<>();
    static {
        openBrackets.add('<');
        closeBrackets.add('>');
    }

    private static final String REGEX_XML_HEADER = "(<?xml version=)\"(\\d).(\\d)\"( encoding=)\"(.*)\"(?>)";

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
    public boolean checkWellformed(String arg) throws Exception {
        ListStack listStack = new ListStack(arg.length());
        // check after xml headline
        Pattern headerPattern = Pattern.compile(REGEX_XML_HEADER);
        Matcher matcher = headerPattern.matcher(arg);
        boolean startWithHead = matcher.find();
        if (startWithHead) {
            arg = arg.substring(arg.indexOf('\n')+1);
            arg = arg.strip();
            argumentChars = arg.toCharArray();

            String currentTag;
            String previousTag;
            // Wie viele Character wurden schon überprüft
            int charactersChecked = 0;
            currentIndex = 0;

            currentTag = nextCharWithBracket();

            while (!currentTag.equals("\0")) {
                previousTag = (String) listStack.peek(); //null
                if (charactersChecked != 0) currentTag = nextCharWithBracket();
                if (!currentTag.equals("\0")) listStack.push(currentTag);

                if (previousTag != null && previousTag.equals(currentTag.replace("/", ""))) {
                    listStack.pop();
                    listStack.pop();
                }
                charactersChecked++;
            }
            return listStack.isEmpty();
        } else {
            return false;
        }
    }

    /**
     * Gives an character which is a bracket.
     * For this it iterate over an char array with the argument. And it checks it the character is in one of the ArrayLists.
     * If it is so, it will return the character. And if not it will return '\0'.
     *
     * @return the next bracket character.
     */
    private String nextCharWithBracket() {
        String nextChar = "\0";
        int bothBracketsFound = 0;
        boolean openBracket = false;
        int openBracketIndex = 0;
        boolean closeBracket = false;
        int closeBracketIndex = 0;
        while (argumentChars.length > currentIndex && bothBracketsFound < 2) {
            char characterInExpression = argumentChars[currentIndex];
            openBracket = openBrackets.stream()
                    .anyMatch(character -> character.equals(characterInExpression));
            closeBracket = closeBrackets.stream()
                    .anyMatch(character -> character.equals(characterInExpression));
            if (openBracket) {
                bothBracketsFound++;
                openBracketIndex = currentIndex;
            }
            if (closeBracket) {
                bothBracketsFound++;
                closeBracketIndex = currentIndex;
            }
            currentIndex++;
        }
        int lengthOfTag = closeBracketIndex - openBracketIndex;
        if (lengthOfTag > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int index = openBracketIndex + 1; index < closeBracketIndex; index++) {
                stringBuilder.append(argumentChars[index]);
            }
            System.out.println(stringBuilder.toString());
            return stringBuilder.toString();
        } else {
            return nextChar;
        }
    }
}
