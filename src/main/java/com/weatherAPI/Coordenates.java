package com.weatherAPI;

import model.States;

import java.util.HashMap;
import java.util.Map;

//to-do arrumar
public class Coordenates {
    public States buildCoordinates(String state) {
        Map<String, States> states = new HashMap<>();
        states.put("AC", new States("AC", "-8.77", "-70.55"));
        states.put("AL", new States("AL", "-9.71", "-35.73"));
        states.put("AM", new States("AM", "-3.07", "-61.66"));
        states.put("AP", new States("AP", "1.41", "-51.77"));
        states.put("BA", new States("BA", "-12.96", "-38.51"));
        states.put("CE", new States("CE", "-3.71", "-38.54"));
        states.put("DF", new States("DF", "-15.83", "-47.86"));
        states.put("ES", new States("ES", "-19.19", "-40.34"));
        states.put("GO", new States("GO", "-16.64", "-49.31"));
        states.put("MA", new States("MA", "-2.55", "-44.30"));
        states.put("MT", new States("MT", "-12.64", "-55.42"));
        states.put("MS", new States("MS", "-20.51", "-54.54"));
        states.put("MG", new States("MG", "-18.10", "-44.38"));
        states.put("PA", new States("PA", "-5.53", "-52.29"));
        states.put("PB", new States("PB", "-7.06", "-35.55"));
        states.put("PR", new States("PR", "-24.89", "-51.55"));
        states.put("PE", new States("PE", "-8.28", "-35.07"));
        states.put("PI", new States("PI", "-8.28", "-43.68"));
        states.put("RJ", new States("RJ", "-22.84", "-43.15"));
        states.put("RN", new States("RN", "-5.22", "-36.52"));
        states.put("RO", new States("RO", "-11.22", "-62.80"));
        states.put("RS", new States("RS", "-30.01", "-51.22"));
        states.put("RR", new States("RR", "1.89", "-61.22"));
        states.put("SC", new States("SC", "-27.33", "-49.44"));
        states.put("SE", new States("SE", "-10.90", "-37.07"));
        states.put("SP", new States("SP", "-23.55", "-46.64"));
        states.put("TO", new States("TO", "-10.25", "-48.25"));

        return states.get(state);
    }
}
