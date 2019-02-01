package com.example.guide.models.hotel;

import java.io.Serializable;

public class Records implements Serializable  {
    public Fields fields;

    @Override
    public String toString() {
        return "Records{" +
                "fields=" + fields +
                '}';
    }
}
