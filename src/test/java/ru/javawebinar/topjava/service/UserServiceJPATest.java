package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by root on 18.05.17.
 */
@ActiveProfiles({Profiles.POSTGRES, Profiles.JPA})
public class UserServiceJPATest extends UserServiceTest{
}
