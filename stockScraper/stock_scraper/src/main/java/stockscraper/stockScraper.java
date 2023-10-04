package stockscraper;

import java.io.FileWriter;
import java.time.LocalDate;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class stockScraper {

    public static String currentDate() {
        String date = LocalDate.now().toString();
        return date;
    }

    public static void main(String[] args) {
        String url = "https://www.investing.com/indices/nq-100-components";
        String fileName = "nasdaq100-" + currentDate();

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("NASDAQ 100: " + currentDate() + "\n\n");
            Document doc = Jsoup.connect(url).get();
            Elements rows = doc.select("#cr1 tbody tr");

            for (Element row : rows) {
                String symbol = row.select("td").get(1).text();
                String change = row.select("td").get(6).text();
                String price = row.select("td").get(2).text();
                fileWriter.write(symbol + " " + price + " " + change + "\n");
            }

            fileWriter.close();
            System.out.println("Text file created successfully.");
        } catch (IOException exception) {
            System.err.println("An error occurred while creating the text file: " + exception.getMessage());
        }
    }
}
