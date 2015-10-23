package com.kanilturgut.phonemarketapp.model;

import java.io.Serializable;

/**
 * Author   : kanilturgut
 * Date     : 21/10/15
 * Time     : 19:25
 */
public class Product implements Serializable {

    private String name;
    private Description description;
    private double price;
    private String[] images;
    private Points points;

    public class Description implements Serializable {

        private double cpu;
        private double ram;
        private double storage;
        private boolean sdCard;
        private double display;
        private String resolution;
        private String os;

        public double getCpu() {
            return cpu;
        }

        public void setCpu(double cpu) {
            this.cpu = cpu;
        }

        public double getRam() {
            return ram;
        }

        public void setRam(double ram) {
            this.ram = ram;
        }

        public double getStorage() {
            return storage;
        }

        public void setStorage(double storage) {
            this.storage = storage;
        }

        public boolean isSdCard() {
            return sdCard;
        }

        public void setSdCard(boolean sdCard) {
            this.sdCard = sdCard;
        }

        public double getDisplay() {
            return display;
        }

        public void setDisplay(double display) {
            this.display = display;
        }

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }
    }

    public class Points implements Serializable {

        private int numberOfVote;
        private int sumOfVote;

        public int getNumberOfVote() {
            return numberOfVote;
        }

        public void setNumberOfVote(int numberOfVote) {
            this.numberOfVote = numberOfVote;
        }

        public int getSumOfVote() {
            return sumOfVote;
        }

        public void setSumOfVote(int sumOfVote) {
            this.sumOfVote = sumOfVote;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }
}
