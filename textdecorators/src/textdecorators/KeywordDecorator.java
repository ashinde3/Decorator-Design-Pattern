package textdecorators;

import textdecorators.util.InputDetails;
import textdecorators.util.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KeywordDecorator extends AbstractTextDecorator {
    private AbstractTextDecorator spellCheckDecorator;
    private InputDetails inputD;

    /**
     * KeywordDecorator Constructor
     * @param spellCheckDecorator
     * @param inputD
     */
    public KeywordDecorator(AbstractTextDecorator spellCheckDecorator, InputDetails inputD) {
        Logger.writeMessage("In KeywordDecorator constructor", Logger.DebugLevel.KeywordDecorator);
        this.spellCheckDecorator = spellCheckDecorator;
        this.inputD = inputD;
    }

    /**
     * Method that retrieves keyword and replaces with
     * prefix and suffix as 'KEYWORD'.
     * Sets the updated input
     */
    @Override
    public void processInputDetails() throws IOException {
        Logger.writeMessage("In KeywordDecorator processInputDetails", Logger.DebugLevel.KeywordDecorator);
        for(int i=0;i<inputD.getReadInput().size();i++) {
            String input = inputD.getReadInput().get(i);
            String temp[] = input.split("[\\s+\\,\\.]");
            for(int j=0;j<inputD.getReadKeyword().size();j++) {
                String keyword = inputD.getReadKeyword().get(j);
                Set<String> words = new HashSet<>(Arrays.asList(temp));
                for (String w: words) {
                    if(w.toLowerCase().contains(keyword)){
                        input = input.replace(w, "KEYWORD_"+ w +"_KEYWORD");
                        inputD.getReadInput().set(i,input);
                    }
                }
            //System.out.println(input);
            }
        }

        if(null != spellCheckDecorator) {
            spellCheckDecorator.processInputDetails();
        }
    }

    /**
     * Debugging purpose
     * @return class name of String type
     */
    @Override
    public String toString() {
        return "KeywordDecorator CLASS" + "\n" + getClass().getName() + "\n" ;
    }
}

