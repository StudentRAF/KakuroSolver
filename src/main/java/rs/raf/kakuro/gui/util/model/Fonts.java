package rs.raf.kakuro.gui.util.model;

public enum Fonts {

    DIN_REGULAR("DIN Regular", ".ttf"),
    DIN_MEDIUM ("DIN Medium",  ".ttf"),
    DIN_BOLD   ("DIN Bold"  ,  ".ttf"),

    MONTSERRAT_REGULAR  ("Montserrat Regular",  ".ttf"),
    MONTSERRAT_MEDIUM   ("Montserrat Medium",   ".ttf"),
    MONTSERRAT_SEMI_BOLD("Montserrat SemiBold", ".ttf"),
    MONTSERRAT_BOLD     ("Montserrat Bold",     ".ttf"),

    ROBOTO_MEDIUM ("Roboto Medium", ".ttf");

    private final String name;
    private final String file;

    Fonts(String name, String file) {
        this.name = name;
        this.file = file;
    }

    /**
     * Returns the font name.
     * @return font name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the font file extension.
     * @return file extension
     */
    public String getFile() {
        return file;
    }

}
