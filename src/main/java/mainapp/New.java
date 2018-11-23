package mainapp;

public class New {

    private final long id;
    private final String input_sentence;
    private final String processed_input_sentence;
    private final String classification_bullying;
    private final String classification_insult;
    private final String classification_abuse;
    private final String classification_name;
    private final String classification_number;
    private final String classification_address;
    private final String classification_OK;

    public New(long id, String input_sentence, String processed_input_sentence,
               String classification_bullying, String classification_insult, String classification_abuse,
               String classification_name, String classification_number, String classification_address, String classification_OK) {
        this.id = id;
        this.input_sentence = input_sentence;
        this.processed_input_sentence = processed_input_sentence;
        this.classification_bullying = classification_bullying;
        this.classification_insult = classification_insult;
        this.classification_abuse = classification_abuse;
        this.classification_name = classification_name;
        this.classification_number = classification_number;
        this.classification_address = classification_address;
        this.classification_OK = classification_OK;
    }

    public long getId() {
        return id;
    }

    public String getInput_sentence() {
        return input_sentence;
    }

    public String getProcessed_input_sentence(){
        return processed_input_sentence;
    }

    public String getClassification_bullying() { return classification_bullying; }

    public String getClassification_insult() { return classification_insult; }

    public String getClassification_abuse() { return classification_abuse; }

    public String getClassification_name() { return classification_name; }

    public String getClassification_number() { return classification_number; }

    public String getClassification_address() { return classification_address; }

    public String getClassification_OK() { return classification_OK; }
}
