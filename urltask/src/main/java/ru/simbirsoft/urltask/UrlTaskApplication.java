package ru.simbirsoft.urltask;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.simbirsoft.urltask.db.Db;
import ru.simbirsoft.urltask.logics.HtmlProcessor;
import ru.simbirsoft.urltask.logics.Statistics;
import ru.simbirsoft.urltask.logics.UrlProcessor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class UrlTaskApplication {
	Logger log = Logger.getLogger(UrlTaskApplication.class.getName());
	public static void main(String[] args) throws IOException {

		SpringApplication.run(UrlTaskApplication.class, args);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введите url");
		String url = scanner.nextLine();
		UrlProcessor urlProcessor = new HtmlProcessor(
				Arrays.asList(' ', ',', '.', '!', '?','"', ';', ':', '[', ']', '(', ')', '\n', '\r', '\t'));
		System.out.println("Статистика: ");
		Optional<List<String>> words = urlProcessor.processUrl(url);
		if(!words.isPresent()) {
			System.out.println("Упсс...Посмотрите в лог. Ошибка!");
		}
		else {
			Map<String, Integer> statistics = Statistics.getCountOfWords(words.get());
			System.out.println(statistics);
			System.out.println("Введите Y, если хотите сохранить статистику");
			String response = scanner.nextLine();
			if(response.equals("Y")) {
					Db.saveData(statistics, url, LocalDateTime.now());

			}
		}

	}

}
