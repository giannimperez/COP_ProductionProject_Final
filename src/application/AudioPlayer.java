package application;



import static application.ItemType.AUDIO;

/**
 * Creates an AudioPlayer Product, implements MultimediaControl
 */
public class AudioPlayer extends Product implements MultimediaControl {

    public String supportedAudioFormats;
    public String supportedPlaylistFormats;

    /**
     * Constructs an audio player
     *
     * @param name                     audio player name
     * @param manufacturer             audio player manufacturer
     * @param supportedAudioFormats    audio player supported audio formats
     * @param supportedPlaylistFormats audio player supported playlist formats
     */
    public AudioPlayer(String name, String manufacturer, String supportedAudioFormats, String supportedPlaylistFormats) {

        super("DP-X1A", "Onkyo", AUDIO);
        this.supportedAudioFormats = supportedAudioFormats;
        this.supportedPlaylistFormats = supportedPlaylistFormats;
        System.out.println("Name: " + name);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Type: " + Type);
        System.out.println("Supported Audio Formats: " + supportedAudioFormats);
        System.out.println("Supported Playlist Formats: " + supportedPlaylistFormats);
        play();
        stop();
    }

    /**
     * prints play text
     */
    public void play() {
        System.out.println("Playing");
    }

    /**
     * prints stop text
     */
    public void stop() {
        System.out.println("Stopping");
    }

    /**
     * prints next text
     */
    public void next() {
        System.out.println("Next");
    }

    /**
     * prints previous text
     */
    public void previous() {
        System.out.println("Previous");
    }

    /**
     * @return returns supported audio formats and supported playlist formats
     */
    public String toString() {
        return this.supportedAudioFormats + "\n" + this.supportedPlaylistFormats;
    }


}



