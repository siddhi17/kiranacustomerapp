package com.kiranacustomerapp.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.kiranacustomerapp.Activities.BillDetails;
import com.kiranacustomerapp.Fragments.OrderDetailFragment;
import com.kiranacustomerapp.Fragments.OrdersFragment;
import com.kiranacustomerapp.Models.Orders;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.CommonUtils;
import com.kiranacustomerapp.helper.TouchImageView;
import com.kiranacustomerapp.viewHolders.LoadBillOrderHolder;
import com.kiranacustomerapp.viewHolders.LoadOrderHeaderHolder;
import com.kiranacustomerapp.viewHolders.LoadOrdersHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Siddhi on 1/11/2017.
 */
public class BillOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    //static var

    static final int LOAD_ORDERS = 0;
    private Context context;
    private List<Orders> list;

    public BillOrdersAdapter(Context context, List<Orders> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {

        Object obj = list.get(position);

        if (obj instanceof Orders) return LOAD_ORDERS;

        return super.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        switch (viewType) {

            case LOAD_ORDERS:
                LoadBillOrderHolder loadBillOrderHolder = (LoadBillOrderHolder) holder;
                retriveAllOrders(loadBillOrderHolder, position);
                break;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (viewType) {
            case LOAD_ORDERS:
                View v_image_msg= inflater.inflate(R.layout.order_layout, parent, false);
                viewHolder = new LoadBillOrderHolder(v_image_msg);
                break;
        }

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void retriveAllOrders(final LoadBillOrderHolder holder, int position) {

        final Orders data = (Orders) list.get(position);

        String url = "http://104.131.162.126/testslim/v1/src/images/" + data.getReceipt();

        Picasso.with(context)
                .load(url);

        final String newDate = CommonUtils.formateDateFromstring("yyyy-MM-dd HH:mm:ss", "dd/M/yyyy", data.getCreated_at());

        holder.tv_Odate.setText(newDate);
        holder.tv_Oquantity.setText(String.valueOf(data.getItem_quantity()));
        holder.tv_Ototal.setText(String.valueOf(data.getTotalAmount()));


        holder.receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //create dialog to show and zoom an image of receipt

                if(!data.getReceipt().equals("") && !data.getReceipt().equals("null")) {

                    final Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                    dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    dialog.setContentView(R.layout.layout_full_image);
                    TouchImageView bmImage = (TouchImageView) dialog.findViewById(R.id.img_receipt);
                    // bmImage.setImageDrawable();
                    Button button = (Button) dialog.findViewById(R.id.btn_dissmiss);
                    dialog.setCancelable(true);
                    dialog.show();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    String url = "http://104.131.162.126/testslim/v1/src/images/" + data.getReceipt();

                    Picasso.with(context)
                            .load(url)
                            .placeholder(R.drawable.progress_animation)
                            .into(bmImage);
                }

                else {

                    ((BillDetails) context).showAlert("No receipt available.");

                }

            }
        });
    }

}
