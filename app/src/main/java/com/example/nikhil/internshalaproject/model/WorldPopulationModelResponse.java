package com.example.nikhil.internshalaproject.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class WorldPopulationModelResponse {

    List<WorldPopulation> worldpopulation;

    public List<WorldPopulation> getWorldpopulation() {
        return worldpopulation;
    }

    public void setWorldpopulation(List<WorldPopulation> worldpopulation) {
        this.worldpopulation = worldpopulation;
    }
}
