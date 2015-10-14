package com.kanilturgut.samsungegitim.lists.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Author   : kanilturgut
 * Date     : 14/10/15
 * Time     : 11:39
 */
public class City {

    private String image;
    private String name;
    private String country;
    private String population;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public static List<City> createCityList() {

        City sydney = new City();
        sydney.setName("Sydney");
        sydney.setCountry("Australia");
        sydney.setPopulation("4.293 million");
        sydney.setImage("http://www.sydney.com/sites/default/files/sydney-harbour-and-opera-house_2.jpg");

        City istanbul = new City();
        istanbul.setName("Istanbul");
        istanbul.setCountry("Turkey");
        istanbul.setPopulation("14.03 million");
        istanbul.setImage("http://www.taksimpremiersuites.com/style/images/istanbul_1.jpg");

        City newYorkCity = new City();
        newYorkCity.setName("New York City");
        newYorkCity.setCountry("ABD");
        newYorkCity.setPopulation("8.406 million");
        newYorkCity.setImage("http://media-cdn.tripadvisor.com/media/photo-s/03/9b/2d/f2/new-york-city.jpg");

        City london = new City();
        london.setName("London");
        london.setCountry("England");
        london.setPopulation("8.63 million");
        london.setImage("https://upload.wikimedia.org/wikipedia/commons/3/3a/London_from_a_hot_air_balloon.jpg");

        City munich = new City();
        munich.setName("Munich");
        munich.setCountry("Germany");
        munich.setPopulation("1.5 million");
        munich.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Munich_skyline.jpg/300px-Munich_skyline.jpg");

        City paris = new City();
        paris.setName("Paris");
        paris.setCountry("France");
        paris.setPopulation("2.244 million");
        paris.setImage("http://cache-graphicslib.viator.com/graphicslib/thumbs674x446/2050/SITours/eiffel-tower-paris-moulin-rouge-show-and-seine-river-cruise-in-paris-150305.jpg");

        City beijing = new City();
        beijing.setName("Beijing");
        beijing.setCountry("China");
        beijing.setPopulation("11.51 million");
        beijing.setImage("http://www.lemessurier.com/sites/default/files/projectslides/beijing_yintai_centre_7_0.jpg");

        List<City> cityList = new LinkedList<>();
        cityList.add(sydney);
        cityList.add(istanbul);
        cityList.add(newYorkCity);
        cityList.add(london);
        cityList.add(munich);
        cityList.add(paris);
        cityList.add(beijing);

        return cityList;
    }
}
