package ru.simbirsoft.urltask;

import org.junit.jupiter.api.Test;
import ru.simbirsoft.urltask.logics.Statistics;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hamcrest.collection.IsMapContaining;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StatisticsTest {
    @Test
    public void getCountTest() {
        List<String> words = Arrays.asList("Разработка",
                "программного", "обеспечения", "Разработка", "Simbirsoft", "simbirsoft");
        Map<String, Integer> counts = Statistics.getCountOfWords(words);
        assertThat(counts, IsMapContaining.hasEntry("РАЗРАБОТКА", 2));
        assertThat(counts, IsMapContaining.hasEntry("ОБЕСПЕЧЕНИЯ", 1));
        assertThat(counts, IsMapContaining.hasEntry("ПРОГРАММНОГО", 1));
        assertThat(counts, IsMapContaining.hasEntry("SIMBIRSOFT", 2));
        assertThat(counts.size(), is(4));
        System.out.println(words);
    }
}
