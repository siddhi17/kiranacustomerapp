package com.kiranacustomerapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.kiranacustomerapp.Adapters.BillsAdapter;
import com.kiranacustomerapp.Models.Bills;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.CommonUtils;
import com.kiranacustomerapp.helper.Excpetion2JSON;
import com.kiranacustomerapp.helper.ServerRequest;
import com.kiranacustomerapp.helper.SessionData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BillsActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView rv_fetch_bills;
    private ArrayList<Object> billList;
    private BillsAdapter billsAdapter;

    private SessionData sessionData;
    private String sessionUserId;
    private String access_token;
    private RelativeLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.bills));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });


        parentLayout=(RelativeLayout) findViewById(R.id.parentLayout);
        rv_fetch_bills =(RecyclerView)findViewById(R.id.rv_fetch_bills);
        billList = new ArrayList<Object>();
        billsAdapter = new BillsAdapter(this,billList);
        rv_fetch_bills.setLayoutManager(new LinearLayoutManager(context));
        rv_fetch_bills.setAdapter(billsAdapter);


        //access bills
        sessionData=new SessionData(this);
        sessionUserId = sessionData.getString("user_id","-1");
        access_token = sessionData.getString("api_key", "-1");
        accessBills();

    }

    public void  accessBills(){
        if (CommonUtils.isConnectedToInternet(this)){
            RetrieveBillsAsyncTask task = new RetrieveBillsAsyncTask();
            task.execute(sessionUserId,access_token);
        }else {
            Snackbar snackbar = Snackbar.make(parentLayout, getString(R.string.check_network), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public class RetrieveBillsAsyncTask extends AsyncTask<String, Void, JSONObject> {
        String api;
        private ProgressDialog progressDialog = new ProgressDialog(BillsActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage(getString(R.string.wait));
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                api = getResources().getString(R.string.server_url) + "getBills/"+params[0];

                ServerRequest request = new ServerRequest(api);
                return request.sendGetRequestForArray(params[1]);

            } catch (Exception ue) {
                return Excpetion2JSON.getJSON(ue);
            }
        }  //end of doInBackground

        @Override
        protected void onPostExecute(JSONObject response) {
            super.onPostExecute(response);
            progressDialog.dismiss();
            try {
                billList.clear();
                JSONArray jsonArray = response.getJSONArray("array");
                if(jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.has("message")) {
                            String message = jsonObject.getString("message");
                            Snackbar snackbar = Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG);
                            snackbar.show();
                        } else {
                            long id,totalAmount;
                            String kiranaName,startDate,endDate;


                            id=jsonObject.getLong("bl_id");
                            kiranaName=jsonObject.getString("kirana_name");
                            totalAmount = jsonObject.getLong("amount");
                            startDate = jsonObject.getString("start_date");
                            endDate = jsonObject.getString("end_date");


                            Bills billsData = new Bills(id,kiranaName,startDate,endDate,totalAmount);
                            billList.add(billsData);
                            billsAdapter.notifyDataSetChanged();

                        }
                    }

                }
            } catch(JSONException je) {
                je.printStackTrace();
                // Toast.makeText(getActivity(), je.getMessage(), Toast.LENGTH_LONG).show();
            }
        } //end of onPostExecute
    }

}
