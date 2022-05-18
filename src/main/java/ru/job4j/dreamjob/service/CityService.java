package ru.job4j.dreamjob.service;


import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CityService {

    private final AtomicInteger idCount = new AtomicInteger(1);
    private Map<Integer, City> cities = new HashMap<Integer, City>();

    public CityService() {
        cities.put(idCount.get(), new City(idCount.getAndIncrement(), "Москва"));
        cities.put(idCount.get(), new City(idCount.getAndIncrement(), "СПб"));
        cities.put(idCount.get(), new City(idCount.getAndIncrement(), "Екб"));
    }

    public List<City> getAllCities() {
        return new ArrayList<>(cities.values());
    }

    public City findById(int id) {
        return cities.get(id);
    }
}
