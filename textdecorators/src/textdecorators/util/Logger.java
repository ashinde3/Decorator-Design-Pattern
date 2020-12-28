package textdecorators.util;

public class Logger {
    /**
     * MyLogger class for the purpose of debugging
     */
    public static enum DebugLevel { MostFrequentWordsDecorator, KeywordDecorator, SpellCheckDecorator, SentenceDecorator, NONE
    };

    private static DebugLevel debugLevel;

    public static void setDebugValue (int levelIn) {
        switch (levelIn) {
            case 4: debugLevel = DebugLevel.SentenceDecorator; break;
            case 3: debugLevel = DebugLevel.SpellCheckDecorator; break;
            case 2: debugLevel = DebugLevel.KeywordDecorator; break;
            case 1: debugLevel = DebugLevel.MostFrequentWordsDecorator; break;
            default: debugLevel = DebugLevel.NONE; break;
        }
    }

    public static void setDebugValue (DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    public static void writeMessage (String     message  ,
                                     DebugLevel levelIn ) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    /**
     * To check the debug level set value
     * @return debugLevel value
     */
    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}
