package com.kiranacustomerapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kiranacustomerapp.Adapters.BillOrdersAdapter;
import com.kiranacustomerapp.Adapters.OrdersAdapter;
import com.kiranacustomerapp.AsyncTasks.GetBillAsyncTask;
import com.kiranacustomerapp.Models.Orders;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.CommonUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BillDetails extends AppCompatActivity implements GetBillAsyncTask.GetBillCallBack{

    public TextView tvKiranaName,tvMerchantName,tvAmount,tvStartDate,tvEndDate,tvPhone,tvOrders;
    private RelativeLayout parentLayout;
    public SharedPreferences pref;
    public Long mBill_id;
    public ArrayList<Orders> ordersList;
    private RecyclerView rv_fetch_orders;
    private BillOrdersAdapter ordersAdapter;
    public CircleImageView imgMerchant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.bill_details));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);

        pref= getSharedPreferences("appdata",MODE_PRIVATE);

        Intent i = getIntent();

        mBill_id = i.getLongExtra("bill_id",0);

        ordersList = new ArrayList<>();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        setUpUI();

    }


    public void setUpUI()
    {

        tvAmount = (TextView)findViewById(R.id.textView_Amount_due);
        tvKiranaName = (TextView)findViewById(R.id.textView_kirana_name);
        tvMerchantName = (TextView)findViewById(R.id.textView_mer_name);
        tvPhone = (TextView)findViewById(R.id.textView_number);
        tvStartDate = (TextView)findViewById(R.id.textView_start_date);
        tvEndDate = (TextView)findViewById(R.id.textView_end_date);
        tvOrders = (TextView)findViewById(R.id.textView_total_order);
        parentLayout = (RelativeLayout)findViewById(R.id.parentLayout);
        imgMerchant = (CircleImageView)findViewById(R.id.imageViewMarchantAvatar);

        new GetBillAsyncTask(this,this).execute(String.valueOf(mBill_id),(pref.getString("api_key","")),pref.getString("user_id",""));

        rv_fetch_orders=(RecyclerView)findViewById(R.id.rv_fetch_orders);
        ordersAdapter = new BillOrdersAdapter(this,ordersList);
        rv_fetch_orders.setLayoutManager(new LinearLayoutManager(this));
        rv_fetch_orders.setAdapter(ordersAdapter);
        rv_fetch_orders.setHasFixedSize(true);
        rv_fetch_orders.setItemViewCacheSize(20);
        rv_fetch_orders.setDrawingCacheEnabled(true);
        rv_fetch_orders.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    public void showAlert(String alert) {

        Snackbar snackbar = Snackbar.make(parentLayout, alert, Snackbar.LENGTH_LONG);
        snackbar.show(); // Don’t forget to show!
    }

    @Override
    public void doPostExecute(JSONObject jsonObject)
    {
        try {
            JSONObject json = jsonObject.getJSONObject("bill");

            tvKiranaName.setText(json.getString("kirana_name"));
            tvMerchantName.setText(json.getString("cont_name"));
            tvPhone.setText(json.getString("phone_no"));
            tvStartDate.setText(CommonUtils.formateDateFromstring("yyyy-MM-dd", "dd/M/yyyy", json.getString("start_date")));
            tvEndDate.setText(CommonUtils.formateDateFromstring("yyyy-MM-dd", "dd/M/yyyy", json.getString("end_date")));
            tvAmount.setText(json.getString("amount"));

            String url = "http://104.131.162.126/testslim/v1/src/images/" + json.getString("avatar");

            Picasso.with(BillDetails.this)
                    .load(url)
                    .resize(400,400)
                    .placeholder(R.drawable.avatar_drk)
                    .error(R.drawable.avatar_drk)
                    .into(imgMerchant);

            JSONArray jsonArray = jsonObject.getJSONArray("orders");


            if(jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json1 = jsonArray.getJSONObject(i);

                    Orders order = new Orders(Long.parseLong(json1.getString("or_id")),Long.parseLong(json1.getString("total_amount")),json1.getString("receipt"),json1.getString("created_at"),Long.parseLong(json1.getString("totalItems")));

                    ordersList.add(order);
                }

                ordersAdapter.notifyDataSetChanged();
            }
            tvOrders.setText(String.valueOf(ordersList.size()));

        }
        catch (JSONException e)
        {

        }
    }

}
