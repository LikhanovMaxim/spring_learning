package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerFreeMarkerTest {

    @Autowired
    private ControllerFreeMarker controller;

    private ModelMap modelMap;

    @Before
    public void setUp() {
        modelMap = new ModelMap();
    }

    @Test
    public void should_return_jsp_view() {
        String user = "User123";
        String view = controller.hello(modelMap, user);
        assertEquals("hello", view);
        assertEquals(user, modelMap.values().stream().findFirst().get());
    }

}
