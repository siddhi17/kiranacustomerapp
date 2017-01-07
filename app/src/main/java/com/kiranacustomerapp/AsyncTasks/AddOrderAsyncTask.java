package com.kiranacustomerapp.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.kiranacustomerapp.Activities.HomeActivity;
import com.kiranacustomerapp.Activities.OtpConfirmation;
import com.kiranacustomerapp.Fragments.MerchantsFragment;
import com.kiranacustomerapp.Fragments.OrdersFragment;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.Excpetion2JSON;
import com.kiranacustomerapp.helper.ServerRequest;
import com.kiranacustomerapp.helper.SessionData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Siddhi on 12/13/2016.
 */
public class AddOrderAsyncTask extends AsyncTask<String, Void, JSONObject> {


    String api;
    JSONObject jsonParams;
    private Context mContext;
    private ProgressDialog progressDialog;
    private Boolean ordersFrag;

    public AddOrderAsyncTask(Context context,Boolean ordersFrag) {

        this.mContext = context;
        this.ordersFrag = ordersFrag;
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
            api = mContext.getResources().getString(R.string.server_url) + "addOrder";

            jsonParams = new JSONObject(params[0]);

            jsonParams.put("customer_id", params[2]);
            jsonParams.put("merchant_id", params[3]);
            jsonParams.put("date_time",params[4]);

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

            if(response != null)
            {

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
                        OrdersFragment fragment1 = new OrdersFragment();
                        fragmentManager.beginTransaction().replace(R.id.mycontainer, fragment1, "RETRIEVE_ORDERS_FRAGMENT").commit();

                        ((HomeActivity) mContext).showAlert(mContext.getString(R.string.orderSent));
                    }
                }
            }
            }
            else {
                ((HomeActivity) mContext).showAlert(mContext.getString(R.string.orderNotSent));
            }

        } catch(JSONException je) {

            je.printStackTrace();
            //  showAlert("Please Enter correct Email Id and Password.");
        }
    } //end of onPostExecute

}
