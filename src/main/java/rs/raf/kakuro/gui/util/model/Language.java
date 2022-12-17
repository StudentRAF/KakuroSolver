package rs.raf.kakuro.gui.util.model;

public enum Language {

    /**
     * Language: English | Region: United States
     */
    ENGLISH_US      ("en-US");

    private final String id;

    Language(String id) {
        this.id   = id;
    }

    /**
     * Returns the language identifier.
     * @return language id
     */
    public String getId() {
        return id;
    }

}
