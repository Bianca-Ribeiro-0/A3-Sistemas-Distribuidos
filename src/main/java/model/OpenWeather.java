package model;

//{

import java.util.List;
import java.util.Map;



public class OpenWeather {
    private Map<String, String> coord;
    private List<Map<String, String>> weather;;
    private Map<String, String> main;
    private Map<String, String> wind;
    private Map<String, String> sys;
    private String timezone;

    public Map<String, String> getCoord() {
        return coord;
    }

    public void setCoord(Map<String, String> coord) {
        this.coord = coord;
    }

    public List<Map<String, String>> getWeather() {
        return weather;
    }

    public void setWeather(List<Map<String, String>> weather) {
        this.weather = weather;
    }

    public Map<String, String> getMain() {
        return main;
    }

    public void setMain(Map<String, String> main) {
        this.main = main;
    }

    public Map<String, String> getWind() {
        return wind;
    }

    public void setWind(Map<String, String> wind) {
        this.wind = wind;
    }

    public Map<String, String> getSys() {
        return sys;
    }

    public void setSys(Map<String, String> sys) {
        this.sys = sys;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}