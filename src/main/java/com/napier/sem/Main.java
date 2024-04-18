package com.napier.sem;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.List;

/*
 * Main class
 * Pass DEBUG as argument for local debugging
 * Otherwise, container deploy is configured
 */
public class Main {
    public static void main(String[] args) {
        LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
        if(args.length!=0 && args[0].equals("DEBUG")) {
            DBReader.initSession("hibernate.cfg.xml", "jdbc:mysql://localhost:3306/world");
        } else {
            DBReader.initSession("hibernate.cfg.xml");
        }
//--Capital City
        System.out.println("World capital city population descending:");
        List<City> capCities = DBReader.queryDB(Query.CapitalCity.WORLD_DESC, 10);
        for (City capCity : capCities) {
            System.out.println(capCity.toStringCapital());
        }
        System.out.println("\nAsian continent capital city population descending:");
        capCities = DBReader.queryDB(Query.CapitalCity.CONTINENT_DESC, 10, Continent.Asia.name());
        for (City capCity : capCities) {
            System.out.println(capCity.toStringCapital());
        }
        System.out.println("\nCaribbean region capital city population descending:");
        capCities = DBReader.queryDB(Query.CapitalCity.REGION_DESC, 10, "Caribbean");
        for (City capCity : capCities) {
            System.out.println(capCity.toStringCapital());
        }
//--City
        System.out.println("\nWorld city population descending:");
        List<City> citiesWorld = DBReader.queryDB(Query.City.WORLD_DESC, 10);
        for (City city : citiesWorld) {
            System.out.println(city.toString());
        }
        System.out.println("\nAsian continent city population descending:");
        List<City> citiesContinent = DBReader.queryDB(Query.City.CONTINENT_DESC, 10, Continent.Asia.name());
        for (City city : citiesContinent) {
            System.out.println(city.toString());
        }
        System.out.println("\nFrance city population descending:");
        List<City> citiesCountry = DBReader.queryDB(Query.City.COUNTRY_DESC, 10, "France");
        for (City city : citiesCountry) {
            System.out.println(city.toString());
        }
        System.out.println("\nPolynesia region population descending:");
        List<City> citiesRegion = DBReader.queryDB(Query.City.REGION_DESC, 10, "Polynesia");
        for (City city : citiesRegion) {
            System.out.println(city.toString());
        }
        System.out.println("\nRajasthan district population descending:");
        List<City> citiesDistrict = DBReader.queryDB(Query.City.DISTRICT_DESC, 10, "Rajasthan");
        for (City city : citiesDistrict) {
            System.out.println(city.toString());
        }
//--Country
        System.out.println("\nWorld country population descending:");
        List<Country> Countries = DBReader.queryDB(Query.Country.WORLD_DESC, 10);
        for (Country c : Countries) {
            System.out.println(c.toString());
        }
        System.out.println("\nAsian continent country population descending:");
        Countries = DBReader.queryDB(Query.Country.CONTINENT_DESC, 10, Continent.Asia.name());
        for (Country c : Countries) {
            System.out.println(c.toString());
        }
        System.out.println("\nWestern Europe region country population descending:");
        Countries = DBReader.queryDB(Query.Country.REGION_DESC, 10, "Western Europe");
        for (Country c : Countries) {
            System.out.println(c.toString());
        }

//--Language
        System.out.println("\nLanguage report with the number of speakers and percentage of world population (in descending order):");
        List<Object[]> languageSpeakers = DBReader.queryDBNonEntity(Query.Language.LANGUAGE_DESC, 0, "");
        for (Object[] row : languageSpeakers) {
            String language = (String) row[0];
            // Filter the results
            if (language.equals("Chinese") || language.equals("English") || language.equals("Hindi") || language.equals("Spanish") || language.equals("Arabic")) {
                Double totalSpeakers = (Double) row[1];
                Double percentageOfWorldPopulation = (Double) row[2];
                System.out.printf("%s: %d speakers (%.2f%% of world population)\n", language, totalSpeakers.intValue(), percentageOfWorldPopulation);
            }
        }

//--Population
        System.out.println("\nPopulation report:");
        List<Population> Populations = DBReader.queryDB(Query.Population.WORLD, 0);
        for (Population p : Populations) {
            System.out.println(p.toString());
        }
        Populations = DBReader.queryDB(Query.Population.CONTINENT, 0, "Asia");
        for (Population p : Populations) {
            System.out.println(p.toString());
        }
        Populations = DBReader.queryDB(Query.Population.COUNTRY, 0, "Ukraine");
        for (Population p : Populations) {
            System.out.println(p.toString());
        }
        Populations = DBReader.queryDB(Query.Population.REGION, 0, "Western Europe");
        for (Population p : Populations) {
            System.out.println(p.toString());
        }
        Populations = DBReader.queryDB(Query.Population.DISTRICT, 0, "Kiova");
        for (Population p : Populations) {
            System.out.println(p.toString());
        }
        Populations = DBReader.queryDB(Query.Population.CITY, 0, "Kyiv");
        for (Population p : Populations) {
            System.out.println(p.toString());
        }

    }
}

