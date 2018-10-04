package ai.preferred.crawler.example.master;

import ai.preferred.crawler.example.entity.Listing;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ween Jiann Lee
 */
public class ListingParser {

  public static class FinalResult {

    private final List<Listing> listings;

    private final String nextPage;

    private FinalResult(List<Listing> listings, String nextPage) {
      this.listings = listings;
      this.nextPage = nextPage;
    }

    public List<Listing> getListings() {
      return listings;
    }

    public String getNextPage() {
      return nextPage;
    }
  }

  public static FinalResult parse(VResponse response) {
    final Document document = response.getJsoup();

    return new FinalResult(
        parseListings(document),
        parseNextPage(document)
    );
  }

  private static List<Listing> parseListings(Document document) {
    final ArrayList<Listing> carList = new ArrayList<>();

    final Elements carURLs = document.select(
            "td[valign=top] + td a");

    for (Element carURL : carURLs) {

      String href = carURL.attr("abs:href");

      carList.add(new Listing(href));
    }

    return carList;
  }

  public static String parseNextPage(Document document) {
    final Element nextPage = document.select(".pagebar").last();
    if (nextPage == null) {
      return null;
    }


    String attr = nextPage.attr("abs:href");

    return attr;
  }

}
