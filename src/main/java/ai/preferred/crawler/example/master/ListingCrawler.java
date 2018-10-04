/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.preferred.crawler.example.master;

import ai.preferred.crawler.example.EntityCSVStorage;
import ai.preferred.crawler.example.entity.Car;
import ai.preferred.crawler.example.entity.Listing;
import ai.preferred.venom.Crawler;
import ai.preferred.venom.Session;
import ai.preferred.venom.fetcher.AsyncFetcher;
import ai.preferred.venom.fetcher.Fetcher;
import ai.preferred.venom.request.VRequest;
import ai.preferred.venom.validator.EmptyContentValidator;
import ai.preferred.venom.validator.PipelineValidator;
import ai.preferred.venom.validator.StatusOkValidator;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Ween Jiann Lee
 */
public class ListingCrawler {

    // You can use this to log to console
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ListingCrawler.class);

    // Create session keys for things you would like to retrieve from handler
    static final Session.Key<ArrayList<Listing>> LISTING_KEY = new Session.Key<>();
    static final Session.Key<HashSet<Car>> CAR_DETAILS_KEY = new Session.Key<>();

    // Create session keys for CSV printer to print from handler
    static final Session.Key<EntityCSVStorage> CSV_STORAGE_KEY = new Session.Key<>();

    public static void main(String[] args) {

        // Get project directory
        String workingDir = System.getProperty("user.dir");

        // Start CSV printer
        try (final EntityCSVStorage printer = new EntityCSVStorage(workingDir + "/results.csv")) {

            // Let's init the session, this allows us to retrieve the array list in the handler
            final ArrayList<Listing> carListing = new ArrayList<>();
            final Session session = Session.builder()
                    .put(LISTING_KEY, carListing)
                    .put(CAR_DETAILS_KEY, new HashSet<>())
                    .put(CSV_STORAGE_KEY, printer)
                    .build();

            // Start crawler
            try (final Crawler crawler = crawler(fetcher(), session).start()) {
                LOGGER.info("Starting crawler...");

                final String startUrl = "https://www.sgcarmart.com/used_cars/listing.php?BRSR=0&RPG=20&AVL=2&VEH=0";

                // pass in URL and handler or use a HandlerRouter
                crawler.getScheduler().add(new VRequest(startUrl), new ListingHandler());
            }

            // We will retrieve all the listing here
            LOGGER.info("We have found {} listings!", carListing.size());

        } catch (Exception e) {
            LOGGER.error("Could not run crawler: ", e);
        }

    }


    private static Fetcher fetcher() {
        // You can look in builder the different things you can add
        return AsyncFetcher.builder()
                .validator(new PipelineValidator(
                        EmptyContentValidator.INSTANCE,
                        StatusOkValidator.INSTANCE,
                        new ListingValidator()))
                .build();
    }

    private static Crawler crawler(Fetcher fetcher, Session session) {
        // You can look in builder the different things you can add
        return Crawler.builder()
                .fetcher(fetcher)
                .session(session)
                .build();
    }
}
