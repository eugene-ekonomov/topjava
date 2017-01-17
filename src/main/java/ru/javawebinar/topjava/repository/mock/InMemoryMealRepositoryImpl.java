package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> save(m.getUserId(), m));
    }

    @Override
    public Meal save(int userId, Meal meal) {

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }

        if(repository.containsKey(meal.getId())){
            Meal mealRep = repository.get(meal.getId());
            if(mealRep.getUserId()!=null && mealRep.getUserId()!=userId) {
                return null;
            }
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int userId, int id) {
        return repository.remove(id)!=null;
    }

    @Override
    public Meal get(int userId, int id) {
        Meal meal = repository.get(id);
        if(meal!= null && meal.getUserId()== AuthorizedUser.id()){
            return meal;
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        Map<Integer, Meal> userMeals = new HashMap<>();
        for(Map.Entry<Integer, Meal> em:repository.entrySet()){
            if(em.getValue().getUserId() == userId){
                userMeals.put(em.getKey(), em.getValue());
            }
        }
        return userMeals.values().stream()
                .sorted((u1, u2) -> u1.getDateTime().compareTo(u2.getDateTime()))
                .collect(Collectors.toList());

    }
}

