package ai.preferred.crawler.example.master;

import ai.preferred.crawler.example.EntityCSVStorage;
import ai.preferred.crawler.example.entity.Car;
import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;
import ai.preferred.venom.response.Response;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarDetailsHandler implements Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDetailsHandler.class);
    private static int counter = 0;


    @Override
    public void handle(Request request, VResponse response, Scheduler scheduler, Session session, Worker worker) {
        LOGGER.info("Processing {}", request.getUrl());

        // Get the job listing array list we created
        final EntityCSVStorage csvStorage = session.get(ListingCrawler.CSV_STORAGE_KEY);

        final HashSet<Car> cars = session.get(ListingCrawler.CAR_DETAILS_KEY);

        // Get HTML
        final String html = response.getHtml();

        // JSoup
        final Document document = response.getJsoup();

        Pattern urlID = Pattern.compile(".+ID=(\\d+).*", Pattern.MULTILINE);

        Car car = new Car();

        car.setUrl(request.getUrl());

        Matcher matcher = urlID.matcher(request.getUrl());
        int carID = -1;

        if (matcher.find()) {
            carID = Integer.parseInt(matcher.group(1));
        } else {
            throw new IllegalStateException();
        }

        car.setId(carID);


        String carName = document.selectFirst(".link_redbanner").text();
        car.setName(carName);

        System.out.println("Car ID: " + carID + ", Car name: " + carName);

        final Elements trs = document.select("#main_left > div:nth-child(2) tr");


        for (Element tr : trs) {
            Element maybeLabel = tr.select(".label").first();
            if (maybeLabel == null)
                continue;

            String attributeName = maybeLabel.text();

            String attributeValue = tr.child(1).text();

            car.setValue(attributeName, attributeValue);
        }

        if (cars.add(car)) {
            csvStorage.append(car);
            counter ++;
            System.out.println(counter);
        } else {
            System.out.println("WARNING: Duplicate ID " + car.getId());
        }
    }
}
