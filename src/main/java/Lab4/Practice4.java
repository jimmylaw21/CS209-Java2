package Lab4;

import java.io.IOException;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Practice4 {
    public static class City
    {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population)
        {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName()
        {
            return name;
        }

        public String getState()
        {
            return state;
        }

        public int getPopulation()
        {
            return population;
        }

        @Override
        public String toString(){
            return "name = " + name + " state = " + state + " population = " + population;
        }

    }

    public static Stream<City> readCities(String filename) throws IOException
    {
        return Files.lines(Paths.get(filename))
                .map(l -> l.split(", "))
                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        Stream<City> cities = readCities("C:\\Users\\jimmylaw21\\OneDrive - 南方科技大学\\桌面\\CS209 java2\\src\\main\\java\\Lab4\\cities.txt");

//        System.out.printf(String.valueOf(Paths.get("cities.txt")));
        // Q1: count how many cities there are for each state
        // TODO: Map<String, Long> cityCountPerState = ...
        Map<String, Long> cityCountPerState = cities.collect(
                Collectors.groupingBy(City::getState, Collectors.counting()));


        cities = readCities("C:\\Users\\jimmylaw21\\OneDrive - 南方科技大学\\桌面\\CS209 java2\\src\\main\\java\\Lab4\\cities.txt");
        // Q2: count the total population for each state
        // TODO: Map<String, Integer> statePopulation = ...
        Map<String, Integer> statePopulation = cities.collect(
                Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));


        cities = readCities("C:\\Users\\jimmylaw21\\OneDrive - 南方科技大学\\桌面\\CS209 java2\\src\\main\\java\\Lab4\\cities.txt");
        // Q3: for each state, get the set of cities with >500,000 population,keep the state with no such cities
        // TODO: Map<String, Set<City>> largeCitiesByState = ...
        Map<String, Set<City>> largeCitiesByState = cities.filter(c -> c.getPopulation() > 500000)
                .collect(Collectors.groupingBy(City::getState, Collectors.toSet()));

        System.out.println(cityCountPerState);
        System.out.println(statePopulation);
        largeCitiesByState.forEach((k, v) -> System.out.println(k + ": " + v.toString()));


    }
}
