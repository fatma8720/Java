import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelDataReader {

    public static void main(String[] args) {
        List<Country> countries = readCountriesFromFile("countries.csv");
        List<City> cities = readCitiesFromFile("cities.csv");

        Map<String, City> highestPopulatedCitiesByCountry = findHighestPopulatedCityByCountry(countries, cities);
        System.out.println("Highest populated city of each country:");
        printHighestPopulatedCities(highestPopulatedCitiesByCountry);

        Map<String, Country> mostPopulatedCountriesByContinent = findMostPopulatedCountryByContinent(countries);
        System.out.println("\nMost populated country of each continent:");
        printMostPopulatedCountries(mostPopulatedCountriesByContinent);

        City highestPopulatedCapital = findHighestPopulatedCapital(countries, cities);
        System.out.println("\nHighest populated capital city:");
        printCapitalCity(highestPopulatedCapital);
    }

    private static List<Country> readCountriesFromFile(String fileName) {
        List<Country> countries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Country country = new Country(values[0], values[1], values[2], Integer.parseInt(values[3]),
                        Double.parseDouble(values[4]), Double.parseDouble(values[5]), Integer.parseInt(values[6]));
                countries.add(country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countries;
    }

    private static List<City> readCitiesFromFile(String fileName) {
        List<City> cities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                City city = new City(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2]), values[3]);
                cities.add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }

    private static Map<String, City> findHighestPopulatedCityByCountry(List<Country> countries, List<City> cities) {
        Map<String, City> highestPopulatedCities = new HashMap<>();
        for (Country country : countries) {
            List<City> citiesInCountry = cities.stream()
                    .filter(city -> city.getCountryCode().equals(country.getCode()))
                    .collect(Collectors.toList());

            City highestPopulatedCity = citiesInCountry.stream()
                    .max(Comparator.comparingInt(City::getPopulation))
                    .orElse(null);

            if (highestPopulatedCity != null) {
                highestPopulatedCities.put(country.getCode(), highestPopulatedCity);
            }
        }
        return highestPopulatedCities;
    }

    private static Map<String, Country> findMostPopulatedCountryByContinent(List<Country> countries) {
        Map<String, Country> mostPopulatedCountries = new HashMap<>();
        for (Country country : countries) {
            mostPopulatedCountries.merge(country.getContinent(), country,
                    (existing, replacement) -> existing.getPopulation() > replacement.getPopulation() ? existing : replacement);
        }
        return mostPopulatedCountries;
    }

    private static City findHighestPopulatedCapital(List<Country> countries, List<City> cities) {
        List<City> capitalCities = cities.stream()
                .filter(city -> countries.stream()
                        .anyMatch(country -> country.getCapital() == city.getId()))
                .collect(Collectors.toList());

        return capitalCities.stream().max(Comparator.comparingInt(City::getPopulation)).orElse(null);
    }

    private static void printHighestPopulatedCities(Map<String, City> cities) {
        cities.forEach((countryCode, city) ->
                System.out.println(countryCode + ": " + city.getName() + " - Population: " + city.getPopulation()));
    }

    private static void printMostPopulatedCountries(Map<String, Country> countries) {
        countries.forEach((continent, country) ->
                System.out.println(continent + ": " + country.getName() + " - Population: " + country.getPopulation()));
    }

    private static void printCapitalCity(City city) {
        System.out.println(city.getName() + " - Population: " + city.getPopulation());
    }
}
