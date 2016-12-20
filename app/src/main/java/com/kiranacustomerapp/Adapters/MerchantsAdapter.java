package com.kiranacustomerapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiranacustomerapp.Activities.HomeActivity;
import com.kiranacustomerapp.Activities.MerchantProfileActivity;
import com.kiranacustomerapp.AsyncTasks.AddFavMerchantAsyncTask;
import com.kiranacustomerapp.Fragments.AddOrderFragment;
import com.kiranacustomerapp.Fragments.MerchantsFragment;
import com.kiranacustomerapp.Models.Merchants;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.CommonUtils;
import com.kiranacustomerapp.helper.SessionData;
import com.kiranacustomerapp.viewHolders.LoadMerchantsHolder;

import java.util.List;

/**
 * Created by Siddhi on 11/26/2016.
 */
public class MerchantsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //static var

    static final int LOAD_MERCHANTS = 0;
    private Context context;
    private List<Object> list;

    private MerchantsFragment merchantsFragment;

    public MerchantsAdapter(Context context, List<Object> list, MerchantsFragment merchantsFragment) {
        this.context = context;
        this.list = list;
        this.merchantsFragment = merchantsFragment;
    }

    @Override
    public int getItemViewType(int position) {

        Object obj = list.get(position);

        if (obj instanceof Merchants) return LOAD_MERCHANTS;

        return super.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        switch (viewType) {

            case LOAD_MERCHANTS:
                LoadMerchantsHolder loadMerchantsHolder = (LoadMerchantsHolder) holder;
                retriveAllMerchants(loadMerchantsHolder, position);
                break;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (viewType) {
            case LOAD_MERCHANTS:
                View v_image_msg = inflater.inflate(R.layout.layout_merchants, parent, false);
                viewHolder = new LoadMerchantsHolder(v_image_msg);
                break;
        }

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void retriveAllMerchants(final LoadMerchantsHolder holder, int position) {

        final Merchants data = (Merchants) list.get(position);
        int fav = data.getFav();

        if(fav == 1)
        {
            holder.imgFavFill.setVisibility(View.VISIBLE);
            holder.imgFavEmpty.setVisibility(View.INVISIBLE);
        }
        else {
            holder.imgFavFill.setVisibility(View.INVISIBLE);
            holder.imgFavEmpty.setVisibility(View.VISIBLE);
        }


        holder.tv_kiarana_name.setText(data.getKirana_name());
        holder.tv_address.setText(data.getMerchant_address());

        holder.imgFavEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SessionData sessionData=new SessionData(context);
                String sessionUserId = sessionData.getString("user_id","-1");
                String access_token = sessionData.getString("api_key", "-1");


                if (CommonUtils.isConnectedToInternet(context)){

                    AddFavMerchantAsyncTask task = new AddFavMerchantAsyncTask(context);
                    task.execute(sessionUserId,access_token,sessionUserId,String.valueOf(data.getMerchant_id()));


                  // ((HomeActivity)context).showAlert("This merchant is now set as your favorite merchant.");

                }else {
                //    Snackbar snackbar = Snackbar.make(coordinatorLayout, getString(R.string.check_network), Snackbar.LENGTH_LONG);
                   // snackbar.show();
                    ((HomeActivity)context).showAlert(context.getString(R.string.check_network));
                }

            }
        });

        holder.lay_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, MerchantProfileActivity.class);

                i.putExtra("cont_name",data.getMerchant_name());
                i.putExtra("kirana_name",data.getKirana_name());
                i.putExtra("email_id",data.getEmail_id());
                i.putExtra("phone_no",data.getPhone());
                i.putExtra("address",data.getMerchant_address());

                context.startActivity(i);

            }
        });

        holder.btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = ((HomeActivity)context).getSupportFragmentManager();
                AddOrderFragment fragment1 = new AddOrderFragment();
                Bundle bundle = new Bundle();
                bundle.putString("kiranaName",data.getKirana_name());
                bundle.putString("merchant_id",String.valueOf(data.getMerchant_id()));
                fragment1.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.mycontainer, fragment1,"RETRIEVE_ADDORDER_FRAGMENT").commit();
            }
        });

    }
}