package com.Railway.System;
public class Train {
    private int id;
    private String name, source, destination, time;

    public Train(int id, String name, String source, String destination, String time) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.time = time;
    }

    public void display() {
        System.out.println(id + " | " + name + " | " + source + " -> " + destination + " | " + time);
    }
}
