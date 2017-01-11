package com.kiranacustomerapp.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;

import com.kiranacustomerapp.Activities.HomeActivity;
import com.kiranacustomerapp.Activities.SearchActivity;
import com.kiranacustomerapp.Database.ItemsTableHelper;
import com.kiranacustomerapp.Fragments.SearchMerchantFragment;
import com.kiranacustomerapp.Models.Merchants;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.Excpetion2JSON;
import com.kiranacustomerapp.helper.ServerRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Siddhi on 12/26/2016.
 */
public class GetSearchedMerchantsAsyncTask extends AsyncTask<String, Void, JSONObject> {

    String api;
    private Context mContext;
    private ProgressDialog progressDialog;
    private GetSearchedMerchantsCallBack getSearchedMerchantsCallBack;
    private ArrayList<Merchants> merchantsList;
    private JSONObject jsonParams;

    public GetSearchedMerchantsAsyncTask(Context context,GetSearchedMerchantsCallBack getSearchedMerchantsCallBack)
    {

        this.mContext = context;
        this.getSearchedMerchantsCallBack = getSearchedMerchantsCallBack;

    }

    public interface GetSearchedMerchantsCallBack{
        void doPostExecute(ArrayList<Merchants> searchedMerchants);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage(mContext.getString(R.string.wait));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            api = mContext.getString(R.string.server_url) + "getSearchedMerchants";

            jsonParams = new JSONObject();

            jsonParams.put("customer_id", params[1]);
            jsonParams.put("latitude", params[2]);
            jsonParams.put("longitude",params[3]);

            ServerRequest request = new ServerRequest(api,jsonParams);
            return request.sendPostRequest(params[0]);

        } catch (Exception ue) {
            return Excpetion2JSON.getJSON(ue);
        }
    }  //end of doInBackground

    @Override
    protected void onPostExecute(JSONObject response) {
        super.onPostExecute(response);
        progressDialog.dismiss();
        try {

            merchantsList = new ArrayList<>();

            JSONArray jsonArray = response.getJSONArray("array");
            if(jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.has("message")) {
                        String message = jsonObject.getString("message");
                        ((HomeActivity)mContext).showAlert(message);
                    } else {
                        long id,merchantId;
                        String kiranaName,emailId,address,merchantName,phoneNo,avatar;
                        int fav;
                        double latitude,longitude;

                        merchantId = jsonObject.getLong("id");
                        kiranaName = jsonObject.getString("kirana_name");
                        merchantName = jsonObject.getString("cont_name");
                        phoneNo = jsonObject.getString("phone_no");
                        emailId = jsonObject.getString("email_id");
                      //  fav=jsonObject.getInt("fav");
                        address=jsonObject.getString("address");
                        latitude = Double.parseDouble(jsonObject.getString("Latitude"));
                        longitude = Double.parseDouble(jsonObject.getString("Longitude"));
                        avatar = jsonObject.getString("avatar");

                        Merchants merchants = new Merchants(merchantId,kiranaName,merchantName,phoneNo,emailId,address,latitude,longitude,avatar);
                        merchantsList.add(merchants);
                        getSearchedMerchantsCallBack.doPostExecute(merchantsList);

                    }
                }

            }
        } catch(JSONException je) {
            je.printStackTrace();
            // Toast.makeText(getActivity(), je.getMessage(), Toast.LENGTH_LONG).show();
        }
    } //end of onPostExecute
}
