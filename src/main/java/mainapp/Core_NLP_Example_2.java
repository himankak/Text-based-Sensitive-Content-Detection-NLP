package mainapp;

import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.io.File;
import java.util.stream.Collectors;

import static java.lang.System.*;

//@Component
@RestController
public class Core_NLP_Example_2 {


    //private final File file;
    /*public Core_NLP_Example_2(File file) throws FileNotFoundException {
        //out.println(String.format("\n TEST LINE PLEASE PRINT"));
        this.file = file;
    }*/

    //private static final String input = "INPUT:\n %s";
    //private static final String output = "OUTPUT:\n %s";

    private final AtomicLong counter = new AtomicLong();
    String text_or_WD;
    String text_or;
    String text1;
    String text2;
    String text3;
    String text4;
    String text5;
    String text6;
    String text7;
    String text8;
    String text9;
    String text10;

    //overriding the write function
    /*public void write(String b) throws Exception {
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.append(b);
        out.flush();
        out.close();
    }*/

    //importing the CSV file and transfering data to an array
    //static String csvFile_INSULT = "C:/Core_NLP_Example_2/mydbword/INSULT.csv";
    //static String csvFile_BULLY = "C:/Core_NLP_Example_2/mydbword/BULLYING.csv";
    //static String csvFile_SA = "C:/Core_NLP_Example_2/mydbword/SEXUAL_ABUSE.csv";

    //make changes in the array dimension if new words are added in the CSV database file.
    static String[][] csvValue_INSULT = new String[59][1];
    static String[][] csvValue_BULLY = new String[60][1];
    static String[][] csvValue_SA = new String[55][1];

    @RequestMapping(value = "/nw", method = RequestMethod.POST)
    public New nw(@RequestParam(value = "text", defaultValue = "WELCOME! API INITIATED. TYPE A SENTENCE IN text VARIABLE TO PROCESS!") String text) {
        boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false;


        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        props.setProperty("ner.useSUTime", "false");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);


        //getting length of input sentence and converting the sentence to UPPERCASE
        text = text.toUpperCase();
        int n = text.length();

        //Printstream used for redirection of Println to file.
        /*PrintStream printStream = null;
        try {
            printStream = new PrintStream(String.valueOf(new Core_NLP_Example_2(file)));
            printStream = new PrintStream(new FileOutputStream("D:\\Core_NLP_Example_2\\OutFile.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setOut(printStream);
        setErr(printStream);*/

        //FUNCTION REMOVING DUPLICATE
        //System.out.println(String.format("SENTENCE BEFORE REMOVING DUPLICATE LETTERS : %s", text));
        text_or_WD = text;
        text_or = removeDuplicate(text, n);
        text1 = "";
        text2 = "";
        text3 = "";
        text4 = "";
        text5 = "";
        text6 = "";
        text7 = "";
        text8 = "";
        text9 = "";
        text10 = "NA";

        //System.out.println(String.format("\nSENTENCE AFTER REMOVING DUPLICATE LETTERS: %s", text));
        String temp = "";
        String word = "";
        String word1 = "";
        String pos = "";
        String ne = "";
        String demo = "";
        try {
            setuparray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text_or);

        // run all Annotators on this text
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        //System.out.println(String.format("\nTHE PROCESSING AND OUTPUT OF THE INPUT SENTENCE IS AS FOLLOWS:"));
        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                //System.out.println(String.format("\nPrint: word: [%s]  Pos: [%s]  NE: [%s]", word, pos, ne));
                //demo = token.get(CoreAnnotations.)


                switch (pos) {
                    case "NN":
                    case "NNS":
                    case "NNPS":
                    case "NNP":
                        for (int i = 1; i < Core_NLP_Example_2.csvValue_BULLY.length; i++) {
                            temp = Arrays.toString(Core_NLP_Example_2.csvValue_BULLY[i]);
                            temp = temp.substring(1, temp.length() - 1);
                            //System.out.println(String.format("WORD IS: %s",temp));
                            if (word.equals(temp)) {
                                //out.println(String.format("\n%s is BULLING WORD! So the sentence: %s is classified as a BULLYING SENTENCE", word, text));
                                text1 = text1 + word + " is a BULLING WORD! So the sentence: " + text_or + " is classified as a BULLYING SENTENCE.";
                                flag5 = true;
                            }
                            //System.out.println(word.equals(temp));
                            //System.out.println(String.format("WORD IS: %s",temp));
                        }
                        for (int i = 1; i < Core_NLP_Example_2.csvValue_INSULT.length; i++) {
                            temp = Arrays.toString(Core_NLP_Example_2.csvValue_INSULT[i]);
                            temp = temp.substring(1, temp.length() - 1);
                            if (word.equals(temp)) {
                                //out.println(String.format("\n%s is a INSULTING WORD! So the sentence: %s is classified as a INSULTING SENTENCE", word, text));
                                text2 = text2 + word + " is a INSULTING WORD! So the sentence: " + text_or + " is classified as a INSULTING SENTENCE.";
                                flag5 = true;
                            }

                        }
                        for (int i = 1; i < Core_NLP_Example_2.csvValue_SA.length; i++) {
                            temp = Arrays.toString(Core_NLP_Example_2.csvValue_SA[i]);
                            temp = temp.substring(1, temp.length() - 1);
                            if (word.equals(temp)) {
                                //out.println(String.format("\n%s is a SEXUALLY ABUSING WORD! So the sentence: %s is classified as an ABUSIVE SENTENCE", word, text));
                                text3 = text3 + word + " is a SEXUALLY ABUSING WORD! So the sentence: " + text_or + " is classified as a ABUSIVE SENTENCE.";
                                flag5 = true;
                            }

                        }
                        break;
                    case "VB":
                    case "VBP":
                    case "VBG":
                    case "VBN":
                    case "VBD":
                    case "VBZ":
                        for (int i = 1; i < Core_NLP_Example_2.csvValue_BULLY.length; i++) {
                            temp = Arrays.toString(Core_NLP_Example_2.csvValue_BULLY[i]);
                            temp = temp.substring(1, temp.length() - 1);
                            if (word.equals(temp)) {
                                //out.println(String.format("\n%s is a BULLING WORD! So the sentence: %s is classified as a BULLYING SENTENCE", word, text));
                                text1 = text1 + word + " is a BULLING WORD! So the sentence: " + text_or + " is classified as a BULLYING SENTENCE.";
                                flag5 = true;
                            }
                        }
                        for (int i = 1; i < Core_NLP_Example_2.csvValue_INSULT.length; i++) {
                            temp = Arrays.toString(Core_NLP_Example_2.csvValue_INSULT[i]);
                            temp = temp.substring(1, temp.length() - 1);
                            if (word.equals(temp)) {
                                //out.println(String.format("\n%s is a INSULTING WORD! So the sentence: %s is classified as a INSULTING SENTENCE", word, text));
                                text2 = text2 + word + " is a INSULTING WORD! So the sentence: " + text_or + " is classified as a INSULTING SENTENCE.";
                                flag5 = true;
                            }

                        }
                        for (int i = 1; i < Core_NLP_Example_2.csvValue_SA.length; i++) {
                            temp = Arrays.toString(Core_NLP_Example_2.csvValue_SA[i]);
                            temp = temp.substring(1, temp.length() - 1);
                            if (word.equals(temp)) {
                                //out.println(String.format("\n%s is a SEXUALLY ABUSING WORD! So the sentence: %s is classified as a ABUSIVE SENTENCE", word, text));
                                text3 = text3 + word + " is a SEXUALLY ABUSING WORD! So the sentence: " + text_or + " is classified as a ABUSIVE SENTENCE.";
                                flag5 = true;
                            }
                        }
                        break;
                    case "JJ":
                    case "JJR":
                    case "JJS":
                        for (int i = 1; i < Core_NLP_Example_2.csvValue_BULLY.length; i++) {
                            temp = Arrays.toString(Core_NLP_Example_2.csvValue_BULLY[i]);
                            temp = temp.substring(1, temp.length() - 1);
                            //System.out.println(String.format("WORD IS: %s",temp));
                            if (word.equals(temp)) {
                                //out.println(String.format("\n%s is BULLING WORD! So the sentence: %s is classified as a BULLYING SENTENCE", word, text));
                                text1 = text1 + word + " is a BULLING WORD! So the sentence: " + text_or + " is classified as a BULLYING SENTENCE.";
                                flag5 = true;
                            }
                            //System.out.println(word.equals(temp));
                            //System.out.println(String.format("WORD IS: %s",temp));
                        }
                        for (int i = 1; i < Core_NLP_Example_2.csvValue_INSULT.length; i++) {
                            temp = Arrays.toString(Core_NLP_Example_2.csvValue_INSULT[i]);
                            temp = temp.substring(1, temp.length() - 1);
                            if (word.equals(temp)) {
                                //out.println(String.format("\n%s is a INSULTING WORD! So the sentence: %s is classified as a INSULTING SENTENCE", word, text));
                                text2 = text2 + word + " is a INSULTING WORD! So the sentence: " + text_or + " is classified as a INSULTING SENTENCE.";
                                flag5 = true;
                            }

                        }
                        for (int i = 1; i < Core_NLP_Example_2.csvValue_SA.length; i++) {
                            temp = Arrays.toString(Core_NLP_Example_2.csvValue_SA[i]);
                            temp = temp.substring(1, temp.length() - 1);
                            if (word.equals(temp)) {
                                //out.println(String.format("\n%s is a SEXUALLY ABUSING WORD! So the sentence: %s is classified as an ABUSIVE SENTENCE", word, text));
                                text3 = text3 + word + " is a SEXUALLY ABUSING WORD! So the sentence: " + text_or + " is classified as a ABUSIVE SENTENCE.";
                                flag5 = true;
                            }

                        }
                        break;
                }
                switch (ne) {
                    case "PERSON":
                        //out.println(String.format("\n%s is a NAME! So the sentence: %s might contain PERSON(S) NAME(S)", word, text));
                        text7 = text7 + word + " is a NAME! So the sentence: " + text_or + " might contain PERSON(S) NAME(S).";
                        flag5 = true;
                        break;
                    case "NUMBER":
                        if (pos.equalsIgnoreCase("CD")) {
                            //out.println(String.format("\n%s is a NUMBER! So the sentence: %s might contain PHONE/ PIN/ STREET/ SOCIAL SECURITY NUMBER", word, text));
                            text8 = text8 + word + " is a NUMBER! So the sentence: " + text_or + " might contain PHONE/ PIN/ STREET/ BUILDING/ SOCIAL SECURITY NUMBER.";
                            flag5 = true;
                        }
                        //flag1 = true;
                        break;
                    case "CITY":
                        word1 = word;
                        flag2 = true;
                        flag5 = true;
                        break;

                    case "COUNTRY":
                        word1 = word;
                        flag3 = true;
                        flag5 = true;
                        break;

                    case "ORGANIZATION":
                        word1 = word;
                        flag4 = true;
                        flag5 = true;
                        break;

                    case "LOCATION":
                        word1 = word;
                        flag6 = true;
                        flag5 = true;
                }
            }
            if (flag2 || flag3 || flag4 || flag6) {
                //out.println(String.format("\nThe sentence: %s might contain ADDRESS", text));
                text9 = text9 + word1 + " is a CITY/ COUNTRY/ ORGANIZATION/ LOCATION so the sentence: " + text_or + " might contain ADDRESS.";
            }

            if (flag5 == false) {
                //out.println(String.format("\nThis sentence: %s is OK! It Does not contain any ABUSE/INSULT/BULLYING/NAME/ADDRESS/SENSITIVE NUMBERS", text));
                text10 = "The sentence: " + text_or + " is OK! It Does not contain any ABUSE/ INSULT/ BULLYING/ NAME/ ADDRESS/ SENSITIVE NUMBERS.";
            }

            //System.out.println(String.format("\nUSED TEXT IS = %s\n", text));
            // this is the parse tree of the current sentence
            Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);

            //System.out.println(String.format("CSV FILE IS:\n%s", csvFile));

            //Set<Dependency<Label, Label, Object>> test= tree.dependencies();
            //System.out.println(tree);
            // this is the Stanford dependency graph of the current sentence
            SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
            //System.out.print(dependencies);
            //System.out.println("DEPENDENCIES: "+dependencies.toList());
            //System.out.println("DEPENDENCIES: "+dependencies.toString());
            //System.out.println("DEPENDENCIES SIZE: "+dependencies.size());
        }
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            SemanticGraph sg = sentence.get(SemanticGraphCoreAnnotations.EnhancedPlusPlusDependenciesAnnotation.class);
            Collection<TypedDependency> dependencies = sg.typedDependencies();
            /*for (TypedDependency td : dependencies) {
                System.out.println(td);
            }*/
        }
        // This is the coreference link graph
        // Each chain stores a set of mentions that link to each other,
        // along with a method for getting the most representative mention
        // Both sentence and token offsets start at 1!
        Map<Integer, CorefChain> graph =
                document.get(CorefCoreAnnotations.CorefChainAnnotation.class);
        //System.out.println(graph.toString());
        //System.out.println(String.format("VALUE OF STRING BUFFER %s", file));


        /*String outs = "D:\\Core_NLP_Example_2\\OutFile.txt";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(outs));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        //BufferedReader br = new BufferedReader(new FileReader("OutFile.txt"));

        /*from here

        String aLineFromFile1 = br.lines().map(aLineFromFile -> aLineFromFile + "\n").collect(Collectors.joining());
        //template = new String(aLineFromFile1);
        //JOptionPane.showMessageDialog(null, aLineFromFile1, "OUTPUT", JOptionPane.INFORMATION_MESSAGE);
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = aLineFromFile1;          to here*/

        //text = graph.toString();
        return new New(counter.incrementAndGet(),
                String.format(text_or_WD),
                String.format(text_or),
                String.format(text1),
                String.format(text2),
                String.format(text3),
                //String.format(text4),
                //String.format(text5),
                //String.format(text6),
                String.format(text7),
                String.format(text8),
                String.format(text9),
                String.format(text10));
    }

    static void setuparray() throws IOException {

        String ipline_INSULT;
        String ipline_BULLY;
        String ipline_SA;

        String[] split_arr_INSULT;
        String[] split_arr_BULLY;
        String[] split_arr_SA;

        int row_pos = 0;
        Scanner s_INSULT = null;
        Scanner s_BULLY = null;
        Scanner s_SA = null;

        InputStream csvFile_INSULT1 = Core_NLP_Example_2.class.getResourceAsStream("/INSULT.csv");
        BufferedReader csvFile_INSULT = new BufferedReader(new InputStreamReader(csvFile_INSULT1));

        InputStream csvFile_BULLY1 = Core_NLP_Example_2.class.getResourceAsStream("/BULLYING.csv");
        BufferedReader csvFile_BULLY = new BufferedReader(new InputStreamReader(csvFile_BULLY1));

        InputStream csvFile_SA1 = Core_NLP_Example_2.class.getResourceAsStream("/SEXUAL_ABUSE.csv");
        BufferedReader csvFile_SA = new BufferedReader(new InputStreamReader(csvFile_SA1));

        //setup the scanner which grabs the CSV file
        s_INSULT = new Scanner(new BufferedReader(csvFile_INSULT));
        s_BULLY = new Scanner(new BufferedReader(csvFile_BULLY));
        s_SA = new Scanner(new BufferedReader(csvFile_SA));

        //loop for INSULT DATABASE
        while (s_INSULT.hasNextLine()) {

            //read line from the file
            ipline_INSULT = s_INSULT.nextLine();

            //split the input line into array elements by comparing commas
            split_arr_INSULT = ipline_INSULT.split(",");

            int y = 1;

            //copy contents of the arr into csvValue
            for (int x = 0; x < y; x++) {
                csvValue_INSULT[row_pos][x] = split_arr_INSULT[y];
            }
            row_pos++;
        }
        row_pos = 0;

        //loop for BULLY DATABASE
        while (s_BULLY.hasNextLine()) {

            //read line from the file
            ipline_BULLY = s_BULLY.nextLine();

            //split the input line into array elements by comparing commas

            split_arr_BULLY = ipline_BULLY.split(",");
            int y = 1;

            //copy contents of the arr into csvValue
            for (int x = 0; x < y; x++) {
                csvValue_BULLY[row_pos][x] = split_arr_BULLY[y];
            }
            row_pos++;
        }
        row_pos = 0;

        //loop for SEXUAL ABUSE DATABASE
        while (s_SA.hasNextLine()) {

            //read line from the file

            ipline_SA = s_SA.nextLine();

            //split the input line into array elements by comparing commas

            split_arr_SA = ipline_SA.split(",");
            int y = 1;

            //copy contents of the arr into csvValue
            for (int x = 0; x < y; x++) {

                csvValue_SA[row_pos][x] = split_arr_SA[y];
            }
            row_pos++;
        }
    }
        //Always start the comparison from index 1 since index 0 contains the HEADING FROM DATABASE which is WORD
            /*for (int x = 1; x < csvValue_INSULT.length; x++)
                System.out.println(String.format("CSV OF INSULT DATABASE = %s", csvValue_INSULT[x]));

            for (int x = 1; x < csvValue_BULLY.length; x++)
                System.out.println(String.format("CSV OF BULLYING DATABASE = %s", csvValue_BULLY[x]));

            for (int x = 1; x < csvValue_SA.length; x++)
                System.out.println(String.format("CSV OF SEXUAL ABUSE DATABASE = %s", csvValue_SA[x]));*/

        //System.out.println(String.format("\nALL DATABASE FILES FOUND SUCCESSFULLY!"));

        /*} catch (Exception e) {
            System.out.println(e);
        }
    }
    }*/

    public static String removeDuplicate(String str, int n) {
        StringBuffer sb = new StringBuffer();

        //System.out.println(String.format("FIRST VALUE INSIDE FUNCTION: %s", str));

        String[] str1 = str.split(" ");

        for (String str2 : str1) {

            //System.out.println(str2);
            char[] str3 = str2.toCharArray();
            n = str3.length;

            // Used as index in the modified string
            int index = 0;

            // Traverse through all characters
            for (int i = 0; i < n; i++) {

                // Check if str[i] is present before it
                int j;
                for (j = 0; j < i; j++) {
                    if (str3[i] == str3[j] && str3[i - 1] == str3[j]) {
                        break;
                    }
                }

                // If not present, then add it to
                // result.
                if (j == 0 && j == i) {
                    str3[index++] = str3[i];
                } else if (j > 0 && (j == i || j + 1 == i)) {
                    str3[index++] = str3[i];
                }
            }
                /*for (int x = 0; x<index; x++) {
                    System.out.println(String.format("SECOND PRINT CHAR ARRAY: %c", str3[x]));
                }*/
            str2 = new String(str3);
            //System.out.println(String.format("VALUE OF STR2: %s", str2));
            str2 = str2.substring(0, index);
            //System.out.println(String.format("VALUE OF FINAL STR2: %s", str2));
            sb.append(str2);
            sb.append(" ");
            //System.out.println(String.format("VALUE OF STRING BUFFER %s", sb));
        }
        str = sb.toString();
        //System.out.println(String.format("SECOND VALUE INSIDE FUNCTION: %s", str));
        return (str);
    }
}

