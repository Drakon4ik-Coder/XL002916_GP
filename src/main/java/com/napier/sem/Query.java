package com.napier.sem;

abstract class Query {
    private Query(){

    }
    abstract static class CapitalCity {
        public static final QueryString WORLD_DESC = new QueryString("FROM City c WHERE c.id = c.country.capital.id ORDER BY c.population DESC", QueryType.CAPITAL_CITY);
        public static final QueryString CONTINENT_DESC = new QueryString("FROM City c WHERE c.id = c.country.capital.id AND c.country.continent = '%s' ORDER BY c.population DESC", QueryType.CAPITAL_CITY);
        public static final QueryString REGION_DESC = new QueryString("FROM City c WHERE c.id = c.country.capital.id AND c.country.region = '%s' ORDER BY c.population DESC", QueryType.CAPITAL_CITY);
    }
    abstract static class City {
        public static final QueryString WORLD_DESC = new QueryString("FROM City c ORDER BY population DESC", QueryType.CITY);
        public static final QueryString CONTINENT_DESC = new QueryString("FROM City c WHERE c.country.continent = '%s' ORDER BY c.population DESC", QueryType.CITY);
        public static final QueryString COUNTRY_DESC = new QueryString("FROM City c WHERE c.country.name = '%s' ORDER BY c.population DESC", QueryType.CITY);
        public static final QueryString REGION_DESC = new QueryString("FROM City c WHERE c.country.region = '%s' ORDER BY c.population DESC", QueryType.CITY);
        public static final QueryString DISTRICT_DESC = new QueryString("FROM City c WHERE c.district = '%s' ORDER BY c.population DESC", QueryType.CITY);

    }

    abstract static class Country {
        public static final QueryString WORLD_DESC = new QueryString("From Country c ORDER BY population DESC", QueryType.COUNTRY);
        public static final QueryString CONTINENT_DESC = new QueryString("FROM Country c WHERE c.continent = '%s' ORDER BY c.population DESC", QueryType.COUNTRY);
        public static final QueryString REGION_DESC = new QueryString("FROM Country c WHERE c.region = '%s' ORDER BY c.population DESC", QueryType.COUNTRY);
    }

    abstract static class Population {
        public static final QueryString WORLD = new QueryString("SELECT 'world' as reportName, r1.sum as total, r2.sum as city, r1.sum-r2.sum as notCity FROM (SELECT SUM(population) as sum FROM Country) r1, (SELECT SUM(population) as sum FROM City) r2", QueryType.POPULATION);
        public static final QueryString CONTINENT = new QueryString("SELECT '%s' as reportName, r1.sum as total, r2.sum as city, r1.sum-r2.sum as notCity FROM (SELECT SUM(population) as sum FROM Country WHERE continent='%s') r1, (SELECT SUM(population) as sum FROM City WHERE country.continent='%s') r2", QueryType.POPULATION);
        public static final QueryString COUNTRY = new QueryString("SELECT '%s' as reportName, r1.sum as total, r2.sum as city, r1.sum-r2.sum as notCity FROM (SELECT SUM(population) as sum FROM Country WHERE name='%s') r1, (SELECT SUM(population) as sum FROM City WHERE country.name='%s') r2", QueryType.POPULATION);
    }

}