package com.kiranacustomerapp.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.kiranacustomerapp.Activities.HomeActivity;
import com.kiranacustomerapp.Fragments.MerchantsFragment;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.Excpetion2JSON;
import com.kiranacustomerapp.helper.ServerRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Siddhi on 12/27/2016.
 */
public class SendRequestAsyncTask extends AsyncTask<String, Void, JSONObject> {


    String api;
    JSONObject jsonParams;
    private Context mContext;
    private ProgressDialog progressDialog;

    public SendRequestAsyncTask(Context context) {

        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();

    }

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            api = mContext.getResources().getString(R.string.server_url) + "sendRequest";

            jsonParams = new JSONObject();

            jsonParams.put("customer_id", params[0]);
            jsonParams.put("merchant_id", params[2]);

            ServerRequest request = new ServerRequest(api, jsonParams);
            return request.sendPostRequest(params[1]);

        } catch(JSONException je) {
            return Excpetion2JSON.getJSON(je);
        } catch (Exception ue) {
            return Excpetion2JSON.getJSON(ue);
        }
    }  //end of doInBackground

    @Override
    protected void onPostExecute(JSONObject response) {
        super.onPostExecute(response);
        if (progressDialog.isShowing())
            progressDialog.dismiss();

        try {
            JSONArray jsonArray = response.getJSONArray("array");
            Log.d("ServerResponsejsonArray", ""+jsonArray);
            if(jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.has("message")) {
                        String message = jsonObject.getString("message");
                        ((HomeActivity) mContext).showAlert(message);
                    } else {
                        //  Toast.makeText(mContext,"not updated",Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = ((HomeActivity) mContext).getSupportFragmentManager();
                        MerchantsFragment fragment1 = new MerchantsFragment();
                        fragmentManager.beginTransaction().replace(R.id.mycontainer, fragment1, "RETRIEVE_MERCHANTS_FRAGMENT").commit();

                        ((HomeActivity) mContext).img_navigation_icon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_search));
                        ((HomeActivity) mContext).toolbar.setNavigationIcon(R.drawable.ic_menu);

                        ((HomeActivity) mContext).img_merchant_white.setVisibility(View.VISIBLE);
                        ((HomeActivity) mContext).img_orders.setVisibility(View.VISIBLE);
                        ((HomeActivity) mContext).img_orders_white.setVisibility(View.GONE);
                        ((HomeActivity) mContext).img_merchant.setVisibility(View.GONE);

                        ((HomeActivity) mContext).showAlert("Request sent successfully.");
                    }
                }
            }

        } catch(JSONException je) {

            je.printStackTrace();
            //  showAlert("Please Enter correct Email Id and Password.");
        }
    } //end of onPostExecute
}
