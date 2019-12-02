package application;

import static application.ItemType.VISUAL;
import static application.MonitorType.LCD;

/**
 * Creates a MoviePlayer Product, implements MultimediaControl
 */
public class MoviePlayer extends Product implements MultimediaControl {

    public String screen;
    public MonitorType monitorType = LCD;
    public String sName = "DBPOWER MK101";

    /**
     * Constructs a movie player
     *
     * @param name  movie player name
     * @param manufacturer movie player manufacturer
     * @param screen movie player screen
     * @param lcd movie player screen type
     */
    public MoviePlayer(String name, String manufacturer, String screen, MonitorType lcd) {
        super("DP-X1A", "Onkyo", VISUAL);
        this.screen = screen;
        this.monitorType = monitorType;
        System.out.println();
        //System.out.println(screen);
    }

    /**
     * prints play text
     */
    public void play() {
        System.out.println("Playing movie");
    }

    /**
     * prints stop text
     */
    public void stop() {
        System.out.println("Stopping movie");
    }

    /**
     * prints next text
     */
    public void next() {
        System.out.println("Next movie");
    }

    /**
     * prints previous text
     */
    public void previous() {
        System.out.println("Previous movie");
    }

    /**
     * returns the type and screen
     *
     * @return type and screen
     */
    public String toString() {

        return this.Type + this.screen;
    }


}

