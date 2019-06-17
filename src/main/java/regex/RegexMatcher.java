package regex;

/**
 * A front end for the regex functionality, which provides access to the implemented
 * regex operations.
 * 
 * @author AD
 */
public class RegexMatcher {
    private Nfa nfa;
    private NfaSimulator simulator;
    
    /**
     * Returns whether a match is found with the provided regex string and text.
     * 
     * @param regex The regex string
     * @param text The text from where the regex is searched
     * @return Is there a match between the regex string and the text given
     */
    public boolean isRegexFoundInText(String regex, String text) {
        this.simulateNfa(regex, text);
        return this.simulator.isMatch();
    }
    
    /**
     * Calculates the first index of the first match between the provided regex
     * string and text.
     * 
     * @param regex The regex string
     * @param text The text from where the regex is searched
     * @return The index of the first character of the first match, or -99 if no matches found
     */
    public int getFirstMatchPosition(String regex, String text) {
        this.simulateNfa(regex, text);
        return this.simulator.getFirstMatchPosition();
    }
    
    private void simulateNfa(String regex, String text) {
        this.nfa = new Nfa(regex);
        
        String textLowerCase = text.toLowerCase();
        
        this.simulator = new NfaSimulator(nfa);
        simulator.simulate(textLowerCase);
    }
    
    
}
