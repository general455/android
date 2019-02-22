package clicksource.ir.xmltutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clicksource.ir.xmltutorial.Adapter.NewsAdapter;
import clicksource.ir.xmltutorial.Models.ArticleModel;
import clicksource.ir.xmltutorial.Models.BaseModel;
import clicksource.ir.xmltutorial.Models.BaseNewsModel;
import clicksource.ir.xmltutorial.Models.CatModel;
import clicksource.ir.xmltutorial.Models.NewsModel;
import clicksource.ir.xmltutorial.Retrofit.ApiClient;
import clicksource.ir.xmltutorial.Retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;

public class ServerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NewsModel>newsModelList;
    FrameLayout frameLayout;
    private String url="https://newsapi.org/v2/everything?q=bitcoin&from=2019-01-15&sortBy=publishedAt&apiKey=853b4874b9a449be8ff3817642cb8602";
   // private String url="http://192.168.1.85/server/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        Log.i("LOG", "appcompat onCreate: ");

        recyclerView=(RecyclerView)findViewById(R.id.rv_server_lastNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        frameLayout=(FrameLayout)findViewById(R.id.frame_server_waiting);
        newsModelList=new ArrayList<>();

        /*SampleGenericClass<Integer> sampleGenericClass=new SampleGenericClass<>();
        sampleGenericClass.add(new Integer(10));
        Toast.makeText(this, sampleGenericClass.get()+"", Toast.LENGTH_SHORT).show();*/

        //getStringDataFromServer();

       // stringRequestSample("nekooei");

        //getLastNews();

        //getBaseModel();

        //getLastNewsRetrofit();
    }

    private void getLastNewsRetrofit() {
        ApiService service=ApiClient.getClient().create(ApiService.class);
        Call<BaseNewsModel> call=service.getBaseNewsModel("bitcoin","2019-01-15","publishedAt","853b4874b9a449be8ff3817642cb8602");
        call.enqueue(new Callback<BaseNewsModel>() {
            @Override
            public void onResponse(Call<BaseNewsModel> call, retrofit2.Response<BaseNewsModel> response) {
                BaseNewsModel baseNewsModel=response.body();
                List<ArticleModel> articleModels=baseNewsModel.getArticles();
                for (int i = 0; i <articleModels.size() ; i++) {
                    ArticleModel articleModel=articleModels.get(i);
                    NewsModel newsModel=new NewsModel();
                    newsModel.setTitle(articleModel.getTitle());
                    newsModel.setSource(articleModel.getSourceModel().getName());
                    newsModel.setUrl(articleModel.getUrl());
                    newsModel.setImageUrl(articleModel.getUrlToImage());
                    newsModel.setDate(articleModel.getPublishedAt());



                    newsModelList.add(newsModel);
                }

                frameLayout.setVisibility(View.GONE);
                recyclerView.setAdapter(new NewsAdapter(ServerActivity.this,newsModelList));
            }

            @Override
            public void onFailure(Call<BaseNewsModel> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }

    private void getBaseModel() {
        ApiService service=ApiClient.getClient().create(ApiService.class);
        Call<BaseModel> call=service.getBaseModel();
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, retrofit2.Response<BaseModel> response) {
                BaseModel baseModel=response.body();
                List<CatModel> catModels=baseModel.getCatModels();
                for (int i = 0; i <catModels.size() ; i++) {
                    CatModel catModel=catModels.get(i);
                    String title=catModel.getTitle();
                    Log.i("LOG", title);
                }
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getLastNews() {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray articles=response.getJSONArray("articles");
                    for (int i = 0; i <articles.length() ; i++) {
                        JSONObject article=articles.getJSONObject(i);
                        JSONObject sourceObject=article.getJSONObject("source");
                        String source=sourceObject.getString("name");
                        String title=article.getString("title");
                        String imageUrl=article.getString("urlToImage");
                        String date=article.getString("publishedAt");
                        String url=article.getString("url");
                        NewsModel newsModel=new NewsModel();
                        newsModel.setTitle(title);
                        newsModel.setSource(source);
                        newsModel.setDate(date);
                        newsModel.setImageUrl(imageUrl);
                        newsModel.setUrl(url);

                        newsModelList.add(newsModel);


                    }
                    frameLayout.setVisibility(View.GONE);
                    recyclerView.setAdapter(new NewsAdapter(ServerActivity.this,newsModelList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(15000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

    private void getStringDataFromServer() {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("cat");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                       JSONObject catJsonObject= jsonArray.getJSONObject(i);
                       String title=catJsonObject.getString("title");
                        Log.i("LOG", title);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG", error.toString() );
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(15000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private void stringRequestSample(final String token){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("LOG", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG", error.toString() );
            }
        }){
            Map<String,String> params=new HashMap<>();

            @Override
            public Map<String, String> getParams() {
                params.put("token",token);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(15000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
