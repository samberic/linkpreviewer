package com.samatkinson;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void name() throws Exception {
       System.out.println("result" + App.getUrl("https://www.strava.com/athletes/4254456"));
       System.out.println("result" + App.getUrl("https://www.bbc.co.uk/news"));
       System.out.println("result" + App.getUrl("https://www.engadget.com/2019/05/19/impossible-foods-burger-sausage-empire/?utm_campaign=homepage&utm_medium=internal&utm_source=dl"));
        System.out.println("result" + App.getUrl("https://www.reddit.com/r/running"));
    }
}