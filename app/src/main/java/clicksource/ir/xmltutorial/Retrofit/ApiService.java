package clicksource.ir.xmltutorial.Retrofit;

import clicksource.ir.xmltutorial.Models.BaseModel;
import clicksource.ir.xmltutorial.Models.BaseNewsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("index.php")
    Call<BaseModel> getBaseModel();

    @GET("everything")
    Call<BaseNewsModel> getBaseNewsModel(@Query("q") String q,@Query("from") String from,
                                         @Query("sortBy") String sortBy,@Query("apiKey") String apiKey);
}
