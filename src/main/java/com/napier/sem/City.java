package com.napier.sem;

import jakarta.persistence.*;
import lombok.Data;

/*
 * City class for hibernate mapping
 * corresponds to city table in database
 * Include instances of capital city
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "CountryCode")
    private Country country;

    @Column(name = "District")
    private String district;

    @Column(name = "Population")
    private int population;

    /**
     * Converts the city object to a string representation.
     * @return String representation of the city object.
     */
    @Override
    public String toString(){
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country.getName() + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }

    /**
     * Converts the city object to a string representation if it is a capital city.
     * @return String representation of the city object if it is a capital city.
     */
    public String toStringCapital(){
        if (country.getCapital()==this) {
            return "CapitalCity{" +
                    "name='" + getName() + '\'' +
                    ", country='" + getCountry().getName() + '\'' +
                    ", population=" + getPopulation() +
                    '}';
        }
        return toString();
    }
}

