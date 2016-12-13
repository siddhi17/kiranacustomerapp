package com.kiranacustomerapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiranacustomerapp.Activities.HomeActivity;
import com.kiranacustomerapp.Adapters.MerchantsAdapter;
import com.kiranacustomerapp.Adapters.OrdersAdapter;
import com.kiranacustomerapp.Models.Merchants;
import com.kiranacustomerapp.Models.Orders;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.CommonUtils;
import com.kiranacustomerapp.helper.Excpetion2JSON;
import com.kiranacustomerapp.helper.ServerRequest;
import com.kiranacustomerapp.helper.SessionData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MerchantsFragment extends Fragment {

    private Context context;
    private RecyclerView rv_fetch_merchants;
    private ArrayList<Object> merchantsList;
    private MerchantsAdapter merchantsAdapter;

    private SessionData sessionData;
    private String sessionUserId;
    private String access_token;
    private CoordinatorLayout coordinatorLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_merchants, container, false);

        Toolbar toolbar = (Toolbar) ((HomeActivity) getActivity()).findViewById(R.id.toolbar);
        toolbar.setTitle("MERCHANTS");
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        ((HomeActivity) getActivity()).setSupportActionBar(toolbar);
        final DrawerLayout drawer = (DrawerLayout)((HomeActivity) getActivity()). findViewById(R.id.drawer_layout1);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        coordinatorLayout=(CoordinatorLayout)view.findViewById(R.id.coordinatorLayout);
        rv_fetch_merchants=(RecyclerView)view.findViewById(R.id.rv_fetch_merchants);
        merchantsList = new ArrayList<Object>();
        merchantsAdapter = new MerchantsAdapter(this.getContext(),merchantsList,MerchantsFragment.this);
        rv_fetch_merchants.setLayoutManager(new LinearLayoutManager(context));
        rv_fetch_merchants.setAdapter(merchantsAdapter);

     //   loadData();

        //access orders
        sessionData=new SessionData(getActivity());
        sessionUserId = sessionData.getString("user_id","-1");
        access_token = sessionData.getString("api_key", "-1");
        accessMerchants();

        return view;
    }

     /*  public void loadData()
       {

           Merchants merchants = new Merchants("1","Bhavani kirana","Nashik",1);
           merchantsList.add(merchants);
           merchants = new Merchants("2","Lakshmi kirana","Nashik",0);
           merchantsList.add(merchants);
           merchants = new Merchants("3","Jay kirana","Mumbai",0);
           merchantsList.add(merchants);
           merchants = new Merchants("4","Jay kirana","Mumbai,shantinagar,chaya road,near jain mandir,sector 3,78",1);
           merchantsList.add(merchants);
           merchantsAdapter.notifyDataSetChanged();

       }*/
    public void  accessMerchants(){
        if (CommonUtils.isConnectedToInternet(getContext())){
            RetrieveMerchantsAsyncTask task = new RetrieveMerchantsAsyncTask();
            task.execute(sessionUserId,access_token);
        }else {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, getString(R.string.check_network), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public class RetrieveMerchantsAsyncTask extends AsyncTask<String, Void, JSONObject> {
        String api;
        private ProgressDialog progressDialog = new ProgressDialog(getContext());
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Please wait..");
            progressDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                api = getResources().getString(R.string.server_url) + "getactivatedmerchants/"+params[0];

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
                merchantsList.clear();
                JSONArray jsonArray = response.getJSONArray("array");
                if(jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.has("message")) {
                            String message = jsonObject.getString("message");
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
                            snackbar.show();
                        } else {
                            long id,merchantId;
                            String kiranaName,emailId,address,merchantName,phoneNo;
                            int fav;

                            merchantId = jsonObject.getLong("mer_id");
                            kiranaName = jsonObject.getString("kirana_name");
                            merchantName = jsonObject.getString("cont_name");
                            phoneNo = jsonObject.getString("phone_no");
                            emailId = jsonObject.getString("email_id");
                            fav=jsonObject.getInt("fav");
                            address=jsonObject.getString("address");

                            Merchants merchants = new Merchants(merchantId,kiranaName,merchantName,phoneNo,emailId,address,fav);
                            merchantsList.add(merchants);
                            merchantsAdapter.notifyDataSetChanged();

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
