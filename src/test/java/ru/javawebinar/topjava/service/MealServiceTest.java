package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
        MealTestData.setUp();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(100002, 100000);
        MATCHER.assertEquals(meals.get(5), meal);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundGet() throws Exception {
        Meal meal = service.get(100009, 100000);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(100002, 100000);
        MATCHER.assertListEquals(Arrays.asList(meals.get(0), meals.get(1), meals.get(2), meals.get(3), meals.get(4)),
                service.getAll(100000));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(100009, 100000);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        MATCHER.assertListEquals(Arrays.asList(meals.get(3), meals.get(4), meals.get(5)),
                service.getBetweenDates(LocalDate.of(2015, 5, 30), LocalDate.of(2015, 5, 30), 100000));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        MATCHER.assertListEquals(Arrays.asList(meals.get(4)),
                service.getBetweenDateTimes(LocalDateTime.of(2015, 5, 30, 12, 0), LocalDateTime.of(2015, 5, 30, 14, 0), 100000));
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Meal> all = service.getAll(100000);
        MATCHER.assertCollectionEquals(all, meals);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = service.get(100002, 100000);
        updated.setDescription("Ланч");
        updated.setCalories(330);
        service.update(updated, 100000);
        MATCHER.assertEquals(updated, service.get(100002, 100000));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() throws Exception {
        Meal updated = service.get(100009, 100000);
        updated.setDescription("Ланч");
        updated.setCalories(330);
        service.update(updated, 100000);
        MATCHER.assertEquals(updated, service.get(100009, 100000));
    }

    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.of(2017, Month.JANUARY, 31, 20, 0), "Ужин", 300);
        Meal created = service.save(newMeal, 100000);
        newMeal.setId(created.getId());
        MATCHER.assertEquals(newMeal, created);
        ArrayList<Meal> newMeals = new ArrayList<>();
        newMeals.add(newMeal);
        newMeals.addAll(meals);
        Collection<Meal> dbMeals = service.getAll(100000);
        MATCHER.assertCollectionEquals(dbMeals, newMeals);
    }
}