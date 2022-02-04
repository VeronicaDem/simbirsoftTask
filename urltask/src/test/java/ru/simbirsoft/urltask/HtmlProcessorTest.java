package ru.simbirsoft.urltask;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import ru.simbirsoft.urltask.logics.HtmlProcessor;

import java.util.Arrays;
import java.util.List;

public class HtmlProcessorTest {
    HtmlProcessor htmlProcessor = new HtmlProcessor(
            Arrays.asList(' ', ',', '.', '!', '?','"', ';', ':', '[', ']', '(', ')', '\n', '\r', '\t')
    );
    @Test
    public void processContentTest() {
        String content = "<div>Разработка программного обеспечения.Разработка</div><div><div>Simbirsoft, (simbirsoft)</div></div>";
        List<String> res = htmlProcessor.processContent(content);
        assertEquals(6, res.size());
        assertTrue(res.contains("Разработка"));
        assertTrue(res.contains("программного"));
        assertTrue(res.contains("обеспечения"));
        assertTrue(res.contains("Разработка"));
        assertTrue(res.contains("Simbirsoft"));
        assertTrue(res.contains("simbirsoft"));
        System.out.println(res);
    }
}
