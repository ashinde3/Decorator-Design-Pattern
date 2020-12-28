Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in textdecorators/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile textdecorators/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile textdecorators/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

```commandline
ant -buildfile textdecorators/src/build.xml run -Dinput="input.txt" -Dmisspelled="src/misspelled.txt" -Dkeywords="keywords.txt" -Doutput="output.txt" -Ddebug=2
```

Note: Arguments accept the absolute path of the files. Debug values can be 1/2/3/4.


-----------------------------------------------------------------------
## Description:
This project is about implementation of the Decorator Design Pattern. There are 4 decorators i.e. MostFrequentWordDecorator, KeywordDecorator, SpellCheckDecorator and SentenceDecorator. All the Decorators inherit the AbstractTextDecorator class which is abstract class containing abstract method processInputDetails(). InputDetails.java class reads all the input files i.e. input.txt, keywords.txt and misspelled.txt and stores the file content in the data structure i.e. ArrayList, in this case. MostFrequentWordDecorator calculates the most frequent occurrences of the word in the input file and appends that particular word with prefix and suffix as 'MOST_FREQUENT' and sets the new updated input file. KeywordDecorator and SpellCheckDecorator performs the similar operations as compare the inputs of keywords.txt and misspelled.txt with the updated input file and appends the particular compared word with prefixes and suffixes as 'KEYWORD' and 'SPELLCHECK' and sets the updated input. SentenceDecorator performs the operation of appending the sentences with prefix and suffix as 'BEGIN_SENTENCE' and 'END_SENTENCE'. The final updated input is retrieved in the SentenceDecorator and is written to the output file.



## Justification for Data Structures used in this project:
1. Array : String array has been used to store the splitted strings.
Time Complexity: O(n)

2. List : ArrayList is used to store the input files that have been read.
Time Complexity: O(n)

3. Map : HashMap is used to find Most frequent word.
Time Complexity : O(1) in average case

4. Set: HashSet is used to store words in SpellCheckDecorator and KeywordDecorator
Time Complexity : O(1) in the best case. O(n) in the worst case



## Citations:
Head First Design Patterns


