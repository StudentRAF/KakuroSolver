package rs.raf.kakuro.gui.util.model;

public enum Fonts {
    DIN       ("DIN"       , ".ttf"),
    DIN_MEDIUM("DIN Medium", ".ttf");

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
