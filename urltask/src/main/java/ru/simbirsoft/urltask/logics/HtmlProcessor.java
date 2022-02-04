package ru.simbirsoft.urltask.logics;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import ru.simbirsoft.urltask.Util;

import java.io.IOException;
import java.util.*;

@Setter
@Getter
public class HtmlProcessor extends UrlProcessor{
    Logger log = Logger.getLogger(HtmlProcessor.class.getName());
    public HtmlProcessor(List<Character> splitWords) {
        super(splitWords);
    }

    @Override
    public Optional<List<String>> processUrl(String url) {
        List<String> processWords = null;
        try {
            Document document = Jsoup.connect(url).get();
            processWords = process(document);
        } catch (Exception e) {
            log.error(e);
        }
        return Optional.of(processWords);
    }

    @Override
    public List<String> processContent(String content) {
        Document document = Jsoup.parse(content);
        return process(document);
    }

    private List<String> process(Document document) {
        List<String> processWords = new ArrayList<>();
        String regexp = "[" + Util.concat(getSplitWords(), '|') + "]";
        for (Element el : document.select("body").select("*")) {

            for (TextNode node : el.textNodes()) {
                String innerText = node.text();
                List<String> words = Arrays.asList(innerText.split(regexp));
                processWords.addAll(words);
            }

        }
        processWords.removeIf(String::isEmpty);
        return processWords;
    }
}
