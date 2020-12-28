package textdecorators;

import textdecorators.util.InputDetails;
import textdecorators.util.Logger;

import java.io.IOException;

public class SentenceDecorator extends AbstractTextDecorator {
    private AbstractTextDecorator atdIn;
    private InputDetails inputD;

    /**
     * SentenceDecorator Constructor
     * @param atdIn
     * @param inputD
     */
    public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails inputD) {
        Logger.writeMessage("In SentenceDecorator constructor", Logger.DebugLevel.SentenceDecorator);
        this.atdIn = atdIn;
        this.inputD = inputD;
    }

    /**
     * Method that appends the sentence with
     * prefix and suffix as 'BEGIN_SENTENCE' and 'END_SENTENCE'.
     * Sets the updated input
     */
    @Override
    public void processInputDetails() throws IOException {
        Logger.writeMessage("In SentenceDecorator processInputDetails", Logger.DebugLevel.SentenceDecorator);
        for(int i=0;i<inputD.getReadInput().size();i++) {
            String sentence = inputD.getReadInput().get(i);
            if(i==0)
                sentence = "BEGIN_SENTENCE__"+sentence;
            sentence = sentence.replace(".", "__END_SENTENCE"+"."+"BEGIN_SENTENCE__");
            if(i==inputD.getReadInput().size()-1)
                sentence=sentence.substring(0,sentence.length()-16);
            //sentence = String.join(".",splitSentence);
            inputD.getReadInput().set(i,sentence);
            inputD.writeToFile(sentence);
        }
        if(null != atdIn) {
            atdIn.processInputDetails();
        }
    }

    /**
     * Debugging purpose
     * @return class name of String type
     */
    @Override
    public String toString() {
        return "SentenceDecorator CLASS" + "\n" + getClass().getName() + "\n" ;
    }
}

