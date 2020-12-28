package textdecorators;

import textdecorators.util.InputDetails;
import textdecorators.util.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SpellCheckDecorator extends AbstractTextDecorator {
    private AbstractTextDecorator sentenceDecorator;
    private InputDetails inputD;

    /**
     * SpellCheckDecorator Constructor
     * @param sentenceDecorator
     * @param inputD
     */
    public SpellCheckDecorator(AbstractTextDecorator sentenceDecorator, InputDetails inputD) {
        Logger.writeMessage("In SpellCheckDecorator constructor", Logger.DebugLevel.SpellCheckDecorator);
        this.sentenceDecorator = sentenceDecorator;
        this.inputD = inputD;
    }

    /**
     * Method that retrieves keyword and replaces with
     * prefix and suffix as 'SPELLCHECK'.
     * Sets the updated input
     */
    @Override
    public void processInputDetails() throws IOException {
        Logger.writeMessage("In SpellCheckDecorator processInputDetails", Logger.DebugLevel.SpellCheckDecorator);
        for(int i=0;i<inputD.getReadInput().size();i++) {
            String input = inputD.getReadInput().get(i);
            String temp[] = input.split("[\\s+\\,\\.]");
            for(int j=0;j<inputD.getReadMispelled().size();j++) {
                String misspelled = inputD.getReadMispelled().get(j);
                Set<String> words = new HashSet<>(Arrays.asList(temp));
                for (String w: words) {
                    if(w.toLowerCase().contains(misspelled)){
                        input = input.replace(w, "SPELLCHECK_"+ w +"_SPELLCHECK");
                        inputD.getReadInput().set(i,input);
                    }
                }
                //System.out.println(input);
            }
        }

        if(null != sentenceDecorator) {
            sentenceDecorator.processInputDetails();
        }
    }

    /**
     * Debugging purpose
     * @return class name of String type
     */
    @Override
    public String toString() {
        return "SpellCheckDecorator CLASS" + "\n" + getClass().getName() + "\n" ;
    }
}

