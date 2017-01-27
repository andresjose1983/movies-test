package com.example.andres.movies_test.model;

import java.io.Serializable;

/**
 * Created by andres on 27/01/17.
 */

public class Genre implements Serializable {

    private int id;
    private String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get genre id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Get genre name
     * @return
     */
    public String getName() {
        return name;
    }
}
