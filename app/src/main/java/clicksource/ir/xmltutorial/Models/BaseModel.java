package clicksource.ir.xmltutorial.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseModel {
    @SerializedName("cat")
    private List<CatModel> catModels;

    public List<CatModel> getCatModels() {
        return catModels;
    }

    public void setCatModels(List<CatModel> catModels) {
        this.catModels = catModels;
    }
}
