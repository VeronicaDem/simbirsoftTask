package ru.simbirsoft.urltask;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.simbirsoft.urltask.logics.HtmlProcessor;
import ru.simbirsoft.urltask.logics.Statistics;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class UrlTaskApplicationTests {
	HtmlProcessor htmlProcessor = new HtmlProcessor(
			Arrays.asList(' ', ',', '.', '!', '?','"', ';', ':', '[', ']', '(', ')', '\n', '\r', '\t')
	);
	@Test
	public void testScenario() {
		String content = "<div>Разработка программного обеспечения.Разработка</div><div><div>Simbirsoft, (simbirsoft)</div></div>";
		List<String> res = htmlProcessor.processContent(content);
		Map<String, Integer> counts = Statistics.getCountOfWords(res);
		assertThat(counts, IsMapContaining.hasEntry("РАЗРАБОТКА", 2));
		assertThat(counts, IsMapContaining.hasEntry("ОБЕСПЕЧЕНИЯ", 1));
		assertThat(counts, IsMapContaining.hasEntry("ПРОГРАММНОГО", 1));
		assertThat(counts, IsMapContaining.hasEntry("SIMBIRSOFT", 2));
		assertThat(counts.size(), is(4));
		System.out.println(counts);
	}
}
