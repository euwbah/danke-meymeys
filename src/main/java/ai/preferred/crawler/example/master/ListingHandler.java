/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.preferred.crawler.example.master;

import ai.preferred.crawler.example.EntityCSVStorage;
import ai.preferred.crawler.example.entity.Listing;
import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Priority;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @author Ween Jiann Lee
 */
public class ListingHandler implements Handler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ListingHandler.class);

  @Override
  public void handle(Request request, VResponse response, Scheduler scheduler, Session session, Worker worker) {
    LOGGER.info("Processing {}", request.getUrl());

    // Get HTML
    final String html = response.getHtml();

    // JSoup
    final Document document = response.getJsoup();

    // We will use a parser class
    final ListingParser.FinalResult finalResult = ListingParser.parse(response);

    finalResult.getListings().forEach(listing -> {
      // LOGGER.info("Found job: {} in {} [{}]", listing.getName(), listing.getCompany(), listing.getUrl());

      String carDetailsURL = listing.getUrl();


      scheduler.add(new VRequest(carDetailsURL), new CarDetailsHandler(), Priority.HIGHEST);
    });

    // Crawl another page if there's a next page
    if (finalResult.getNextPage() != null) {
      final String nextPageURL = finalResult.getNextPage();

      // Schedule the next page
      scheduler.add(new VRequest(nextPageURL), this);
    }

  }
}
