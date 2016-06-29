package com.l99.chinafootball.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.l99.chinafootball.Url;
import com.l99.chinafootball.bean.ElearningQuizCategoriesTreeBean;
import com.l99.chinafootball.bean.ElearningTrainingCategoriesTreeBean;
import com.l99.chinafootball.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/6/27.
 */
public class ElearningApi {

    private String url =  Url.BASE_URL+"/elearning";

    private Context context;

    private ArrayList<ElearningQuizCategoriesTreeBean> elearningQuizCategoriesTreeBeans;

    private ArrayList<ElearningTrainingCategoriesTreeBean> elearningTrainingCategoriesTreeBeans;

    public ElearningApi(Context context) {
        this.context = context;
    }

    public ArrayList<ElearningQuizCategoriesTreeBean> getElearningQuizCategoriesTree(String key) {
//     http://192.168.50.154:8000/football/elearning/quiz_categories?key=visitor
        url = url +"/quiz_categories"+"?key="+key;
        LogUtil.e(url);
        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LogUtil.e(response);
                        elearningQuizCategoriesTreeBeans = new ArrayList<>();
                        elearningQuizCategoriesTreeBeans = processElearningQuizCategoriesTree(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        mQueue.add(stringRequest);
        return elearningQuizCategoriesTreeBeans;

    }

    private ArrayList<ElearningQuizCategoriesTreeBean> processElearningQuizCategoriesTree(String json) {
        ArrayList<ElearningQuizCategoriesTreeBean.CatBean> catBeans = null;
        elearningQuizCategoriesTreeBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++) {

               JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String id = jsonObject.optString("id");
                String image = jsonObject.optString("image");
                String name = jsonObject.optString("name");
                String text = jsonObject.optString("text");
                int type = jsonObject.optInt("type");

                JSONArray cats = jsonObject.optJSONArray("cats");
                if(cats !=null && cats.length() >0) {
                    catBeans = new ArrayList<>();
                    for(int j = 0; j < cats.length(); j++) {
                        JSONObject object = (JSONObject) cats.get(j);
                        String cats_id = object.optString("id");
                        String cats_name = object.optString("name");
                        String cats_text = object.optString("text");
                        int cats_type = object.optInt("type");

                        ElearningQuizCategoriesTreeBean.CatBean catBean = new ElearningQuizCategoriesTreeBean.CatBean();
                        catBean.setId(cats_id);
                        catBean.setName(cats_name);
                        catBean.setText(cats_text);
                        catBean.setType(cats_type);

                        catBeans.add(catBean);

                    }
                }

                ElearningQuizCategoriesTreeBean elearningQuizCategoriesTreeBean = new ElearningQuizCategoriesTreeBean();
                elearningQuizCategoriesTreeBean.setId(id);
                elearningQuizCategoriesTreeBean.setImage(image);
                elearningQuizCategoriesTreeBean.setName(name);
                elearningQuizCategoriesTreeBean.setText(text);
                elearningQuizCategoriesTreeBean.setType(type);
                elearningQuizCategoriesTreeBean.setCatBeans(catBeans);

                elearningQuizCategoriesTreeBeans.add(elearningQuizCategoriesTreeBean);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return elearningQuizCategoriesTreeBeans;
    }

    public ArrayList<ElearningTrainingCategoriesTreeBean> getElearningTrainingCategoriesTree(String key) {
//    http://192.168.50.154:8000/football/elearning/training_categories?key=visitor
        url = url +"/training_categories"+"?key="+key;
        LogUtil.e(url);
        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LogUtil.e(response);
                        elearningTrainingCategoriesTreeBeans = new ArrayList<>();
                        elearningTrainingCategoriesTreeBeans = processElearningTrainingCategoriesTree(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        mQueue.add(stringRequest);

        return elearningTrainingCategoriesTreeBeans;
    }

    private ArrayList<ElearningTrainingCategoriesTreeBean> processElearningTrainingCategoriesTree(String json) {
        ArrayList<ElearningTrainingCategoriesTreeBean.CatsBean> cats1 = new ArrayList<>();
        elearningTrainingCategoriesTreeBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++) {
               JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String id = jsonObject.optString("id");
                String image = jsonObject.optString("image");
                String name = jsonObject.optString("name");
                int pageCount = jsonObject.optInt("pageCount");
                int type = jsonObject.optInt("type");

                JSONArray cats = jsonObject.optJSONArray("cats");
                if(cats !=null && cats.length() >0 ) {
                    for(int j = 0; j < cats.length(); j++) {
                        JSONObject obj = (JSONObject) cats.get(j);
                        String cat_id = obj.optString("id");
                        String cat_name = obj.optString("name");
                        int cat_pageCount = obj.optInt("pageCount");
                        int cat_type = obj.optInt("type");

                        ElearningTrainingCategoriesTreeBean.CatsBean cat = new ElearningTrainingCategoriesTreeBean.CatsBean();
                        cat.setId(cat_id);
                        cat.setName(cat_name);
                        cat.setPageCount(cat_pageCount);
                        cat.setType(cat_type);
                        cats1.add(cat);

                    }
                }

                ElearningTrainingCategoriesTreeBean elearningTrainingCategoriesTreeBean = new ElearningTrainingCategoriesTreeBean();
                elearningTrainingCategoriesTreeBean.setId(id);
                elearningTrainingCategoriesTreeBean.setImage(image);
                elearningTrainingCategoriesTreeBean.setName(name);
                elearningTrainingCategoriesTreeBean.setPageCount(pageCount);
                elearningTrainingCategoriesTreeBean.setType(type);
                elearningTrainingCategoriesTreeBean.setCats(cats1);

                elearningTrainingCategoriesTreeBeans.add(elearningTrainingCategoriesTreeBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return elearningTrainingCategoriesTreeBeans;
    }
}
