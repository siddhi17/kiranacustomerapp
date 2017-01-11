package com.kiranacustomerapp.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.kiranacustomerapp.Activities.BillDetails;
import com.kiranacustomerapp.Database.UnitsTableHelper;
import com.kiranacustomerapp.Models.Bills;
import com.kiranacustomerapp.Models.LoadOrderDetails;
import com.kiranacustomerapp.Models.LoadOrderDetailsHeader;
import com.kiranacustomerapp.Models.Merchants;
import com.kiranacustomerapp.Models.Orders;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.CommonUtils;
import com.kiranacustomerapp.helper.Excpetion2JSON;
import com.kiranacustomerapp.helper.ServerRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Siddhi on 1/10/2017.
 */
//AsyncTask
public class GetBillAsyncTask extends AsyncTask<String, Void, JSONObject> {
    String api;
    public ProgressDialog progressDialog;
    private Context context;
    long id,order_id;
    double item_cost,item_quantity;
    String item_name,item_unit,tags,created_at;
    private JSONObject jsonParams;
    public GetBillCallBack getBillCallBack;

    public GetBillAsyncTask(Context context,GetBillCallBack getBillCallBack)
    {

        this.context = context;
        this.getBillCallBack = getBillCallBack;

    }
    public interface GetBillCallBack{
        void doPostExecute(JSONObject jsonObject);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.wait));
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            api = context.getResources().getString(R.string.server_url) + "getBill/"+params[0];

            jsonParams = new JSONObject();

            jsonParams.put("customer_id", params[2]);

            ServerRequest request = new ServerRequest(api, jsonParams);

            return request.sendPostRequest(params[1]);


        } catch (Exception ue) {
            return Excpetion2JSON.getJSON(ue);
        }
    }  //end of doInBackground

    @Override
    protected void onPostExecute(JSONObject response) {
        super.onPostExecute(response);



        try {
            JSONArray jsonArray = response.getJSONArray("array");
            if(jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.has("message")) {
                        String message = jsonObject.getString("message");
                        ((BillDetails)context).showAlert(message);

                    } else {

                        getBillCallBack.doPostExecute(jsonObject);
                        progressDialog.dismiss();
                    }
                }

            }
        } catch(JSONException je) {
            je.printStackTrace();
            // Toast.makeText(getActivity(), je.getMessage(), Toast.LENGTH_LONG).show();
        }
    } //end of onPostExecute
}
