package textdecorators;

import textdecorators.util.InputDetails;
import textdecorators.util.Logger;

import java.io.IOException;
import java.util.*;

public class MostFrequentWordDecorator extends AbstractTextDecorator {
    private AbstractTextDecorator keywordDecorator;
    private InputDetails inputD;

    /**
     * MostFrequentWordDecorator Constructor
     * @param keywordDecorator
     * @param inputD
     */
    public MostFrequentWordDecorator(AbstractTextDecorator keywordDecorator, InputDetails inputD) {
        Logger.writeMessage("In MostFrequentWordDecorator constructor", Logger.DebugLevel.MostFrequentWordsDecorator);
        this.keywordDecorator = keywordDecorator;
        this.inputD = inputD;
    }

    /**
     * Method that gets the most frequent word and replaces the old string with
     * prefix and suffix as 'MOSTFREQUENT'.
     * Sets the updated input
     */
    @Override
    public void processInputDetails() throws IOException {
        Logger.writeMessage("In MostFrequentWordDecorator processInputDetails", Logger.DebugLevel.MostFrequentWordsDecorator);
        Map<String, Integer> wordFreq = new HashMap<>();
        for(String s:inputD.getReadInput()) {
            String word[] = s.toLowerCase().split("[\\s+\\,\\.]");
            for(String str:word) {
                if (wordFreq.keySet().contains(str))
                    // if already exists then update count.
                    wordFreq.put(str, wordFreq.get(str) + 1);
                else {
                    wordFreq.put(str, 1);
                }

            }
        }
        //System.out.println("mfc "+MostFreqWordCalc(wordFreq));
        for(int i=0;i<inputD.getReadInput().size();i++) {
            String temp = inputD.getReadInput().get(i);
            String wordTemp[] = temp.split("[\\s+\\,\\.]");
            for(String str:wordTemp) {
                if(str.equalsIgnoreCase(MostFreqWordCalc(wordFreq))) {
                    if(!temp.contains("MOST_FREQUENT_" + str + "_MOST_FREQUENT"))
                    temp = temp.replaceAll("\\b" + str + "\\b", "MOST_FREQUENT_" + str + "_MOST_FREQUENT");
                    inputD.getReadInput().set(i,temp);
                }
            }

        }

        if(null != keywordDecorator) {
            keywordDecorator.processInputDetails();
        }
    }

    /**
     * Method to return most frequent word
     * @param wordFreq
     * @return mostFreq word of String type
     */
    public String MostFreqWordCalc(Map<String, Integer> wordFreq) {
        String mostFreq = "";
        int maxFreq = 0;
        for (Map.Entry<String, Integer> entry : wordFreq.entrySet()) {
            if (entry.getValue() >= maxFreq) {
                maxFreq = entry.getValue();
                mostFreq = entry.getKey();
            }
        }
        return mostFreq;
    }

    /**
     * Debugging purpose
     * @return class name of String type
     */
    @Override
    public String toString() {
        return "MostFrequentWordDecorator CLASS" + "\n" + getClass().getName() + "\n" ;
    }
}

