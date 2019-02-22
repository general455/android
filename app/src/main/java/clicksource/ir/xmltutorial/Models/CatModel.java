package clicksource.ir.xmltutorial.Models;

import com.google.gson.annotations.SerializedName;

public class CatModel {
    private String id;
   // @SerializedName("title")
    private String title;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
