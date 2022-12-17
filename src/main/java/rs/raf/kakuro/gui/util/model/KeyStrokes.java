package rs.raf.kakuro.gui.util.model;

public enum KeyStrokes {

    /**
     * Default Kakuro keystrokes.
     */
    DEFAULT("Default");

    private final String id;

    KeyStrokes(String id) {
        this.id = id;
    }

    /**
     * Returns the keystrokes identifier.
     * @return id
     */
    public String getId() {
        return id;
    }

}
