package application;

/**
 * Identifies an Item's "ItemType"
 */
public enum ItemType {

    AUDIO("AU"), VISUAL("VI"), AUDIO_MOBILE("AM"), VISUAL_MOBILE("VM");

    private String label;

    /**
     * Sets label to c
     *
     * @param c
     */
    ItemType(String c) {
        label = c;
    }

    /**
     * returns the label
     *
     * @return ItemType label
     */
    public String getLabel() {
        return this.label;
    }

}
