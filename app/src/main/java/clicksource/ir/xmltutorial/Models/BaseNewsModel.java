package clicksource.ir.xmltutorial.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseNewsModel {

    private List<ArticleModel> articles;

    public List<ArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleModel> articles) {
        this.articles = articles;
    }
}
