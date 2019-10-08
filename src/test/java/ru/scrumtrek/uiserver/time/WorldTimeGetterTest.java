package ru.scrumtrek.uiserver.time;

import org.junit.Assert;
import org.junit.Test;

public class WorldTimeGetterTest {
    private WorldTimeGetter sut = new WorldTimeGetter();

    @Test
    public void checkEst() throws Exception {
        String time = sut.getTime("est");
        Assert.assertTrue(time.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}-\\d{2}:\\d{2}"));
    }

    @Test
    public void checkUtc() throws Exception {
        String time = sut.getTime("utc");
        Assert.assertTrue(time.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}Z"));
    }
}
