package ru.simbirsoft.urltask.logics;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Setter
@Getter
public abstract class UrlProcessor {
    private List<Character> splitWords;

    public UrlProcessor(List<Character> splitWords) {
        this.splitWords = splitWords;
    }
    public abstract Optional<List<String>> processUrl(String url);
    public abstract List<String> processContent(String content);
}
