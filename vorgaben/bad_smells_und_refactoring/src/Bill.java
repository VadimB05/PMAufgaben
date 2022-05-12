import java.util.ArrayList;
import java.util.Date;

public class Bill {
    private ArrayList<Article> articles;

    public Bill() {
        articles = new ArrayList<Article>();
    }

    public boolean addArticle(Article a) {
        return articles.add(a);
    }

    public String getDetails() {
        double total = 0;

        String result = "Article: \n";
        for (Article article : articles) {
            double price = 0;
            if (article.bike instanceof Brompton) {
                if (article.purchaseAmount > 1) {
                    price += (article.purchaseAmount - 1) * article.bike.price / 2;
                }
                price += article.bike.price * article.purchaseAmount;
            } else if (article.bike instanceof EBike) {
                price += article.bike.price * article.purchaseAmount;
            } else if (article.bike instanceof Mountainbike) {
                if (article.purchaseAmount > 2) {
                    price += article.purchaseAmount * article.bike.price * 9 / 10;
                } else {
                    price += article.bike.price * article.purchaseAmount;
                }
            }
            if (price > 1000f || price == 1000.0) {
                price = price * 0.8;
            }

            result += "\t" + article.bike.productName + "\tx\t" + article.purchaseAmount + "\t=\t" + String.valueOf(price) + "\n";
            total += price;
        }

        result += "\nTotal price:\t" + String.valueOf(total) + "\n";

        return result;
    }

}
