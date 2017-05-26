package ru.javawebinar.topjava.service;

/**
 * Created by root on 18.05.17.
 */

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles({Profiles.POSTGRES, Profiles.JPA})
public class MealServiceJPATest extends MealServiceTest{
}
