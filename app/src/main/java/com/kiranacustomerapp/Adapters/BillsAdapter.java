package com.kiranacustomerapp.Adapters;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiranacustomerapp.Fragments.OrderDetailFragment;
import com.kiranacustomerapp.Fragments.OrdersFragment;
import com.kiranacustomerapp.Models.Bills;
import com.kiranacustomerapp.Models.Orders;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.CommonUtils;
import com.kiranacustomerapp.viewHolders.LoadBillsHolder;
import com.kiranacustomerapp.viewHolders.LoadOrdersHolder;

import java.util.List;

/**
 * Created by Siddhi on 1/9/2017.
 */
public class BillsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    //static var

    static final int LOAD_BILLS = 0;
    private Context context;
    private List<Object> list;

    public BillsAdapter(Context context, List<Object> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {

        Object obj = list.get(position);

        if (obj instanceof Orders) return LOAD_BILLS;

        return super.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        switch (viewType) {

            case LOAD_BILLS:
                LoadBillsHolder loadBillsHolder = (LoadBillsHolder) holder;
                retriveAllBills(loadBillsHolder, position);
                break;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (viewType) {
            case LOAD_BILLS:
                View v_image_msg= inflater.inflate(R.layout.bills_layout, parent, false);
                viewHolder = new LoadBillsHolder(v_image_msg);
                break;
        }

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void retriveAllBills(final LoadBillsHolder holder, int position) {

        final Bills data = (Bills) list.get(position);

        final String startDate = CommonUtils.formateDateFromstring("yyyy-MM-dd", "dd MMM,yyyy", data.getStartDate());

        final String endDate = CommonUtils.formateDateFromstring("yyyy-MM-dd", "dd MMM,yyyy", data.getEndDate());

        holder.tv_kirana_name.setText(data.getKiranaName());
        holder.tv_Start_date.setText(startDate);
        holder.tv_end_date.setText(endDate);
        holder.tv_amount.setText(String.valueOf(data.getAmount()));

     /*  holder.lay_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("name", data.getMerchant_name());
                bundle.putString("date", newDate);
                bundle.putString("time", time);
                bundle.putLong("quantity", data.getItem_quantity());
                bundle.putInt("status", data.getStatus());
                bundle.putLong("orderID", data.getId());
                bundle.putLong("amount", data.getTotalAmount());
                bundle.putString("avatar", data.getAvatar());
                bundle.putString("receipt", data.getReceipt());
                //go to home screen
                FragmentManager fragmentManager = ordersFragment.getFragmentManager();
                OrderDetailFragment fragment = new OrderDetailFragment();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.mycontainer, fragment, "RETRIEVE_ORDERS_ITEMS_FRAGMENT").commit();

            }
        });*/

    }

}
