package regex;

public class RegexMatcher {
    private Nfa nfa;
    private NfaSimulator simulator;
    
    
    public boolean isRegexFoundInText(String regex, String text){
        this.simulateNfa(regex, text);
        return this.simulator.isMatch();
    }
    
    public int getFirstMatchPosition(String regex, String text){
        this.simulateNfa(regex, text);
        return this.simulator.getFirstMatchPosition();
    }
    
    private void simulateNfa(String regex, String text){
        this.nfa = new Nfa(regex);
        
        String textLowerCase = text.toLowerCase();
        
        this.simulator = new NfaSimulator(nfa);
        simulator.simulate(textLowerCase);
    }
    
    
}
