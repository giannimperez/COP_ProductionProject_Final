package application;

public abstract class Screen implements ScreenSpec {

    private String resolution;
    private int refreshRate, responseTime;

    /**
     * Constructs screen
     *
     * @param resolution
     * @param refreshRate
     * @param responseTime
     */
    public Screen(String resolution, int refreshRate, int responseTime) {
        this.resolution = resolution;
        this.refreshRate = refreshRate;
        this.responseTime = responseTime;
    } // end constructor

    /**
     * returns resolution
     *
     * @return resolution
     */
    public String getResolution() {
        return this.resolution;
    }

    /**
     * returns refreshRate
     *
     * @return refreshRate
     */
    public int getRefreshRate() {
        return this.refreshRate;
    }

    /**
     * returns responseTime
     *
     * @return responseTime
     */
    public int getResponseTime() {
        return this.responseTime;
    }

    /**
     * returns screen details
     *
     * @return screen details
     */
    @Override
    public String toString() {

        return "Screen:\n" + "Resolution: " + this.resolution + "\n" + "Refresh rate: " + this.refreshRate + "\n" + "Response time: " + this.responseTime;
    }

}