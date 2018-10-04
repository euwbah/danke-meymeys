package ai.preferred.crawler.example.entity;

/**
 * This class allows you to store your entities. Define the
 * properties of your entities in this class.
 *
 * @author Ween Jiann Lee
 */
public class Listing {

  private final String url;
  public Listing(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}
