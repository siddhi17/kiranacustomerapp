package com.kiranacustomerapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kiranacustomerapp.Fragments.AddOrderFragment;
import com.kiranacustomerapp.Models.OrderItem;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.viewHolders.LoadMerchantsHolder;
import com.kiranacustomerapp.viewHolders.LoadOrderItemsHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Siddhi on 12/11/2016.
 */
public class OrderItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static final int LOAD_ORDERITEMS = 0;
    private Context context;
    private List<OrderItem> list;

    private AddOrderFragment addOrderFragment;

    public OrderItemsAdapter(Context context, List<OrderItem> list) {
        this.context = context;
        this.list = list;
        this.addOrderFragment = addOrderFragment;
    }

    @Override
    public int getItemViewType(int position) {

        Object obj = list.get(position);

        if (obj instanceof OrderItem) return LOAD_ORDERITEMS;

        return super.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        switch (viewType) {

            case LOAD_ORDERITEMS:
                LoadOrderItemsHolder loadOrderItemsHolder = (LoadOrderItemsHolder) holder;

                retrieveAllOrderItem(loadOrderItemsHolder,position);

                break;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (viewType) {
            case LOAD_ORDERITEMS:
                View v_image_msg = inflater.inflate(R.layout.order_item_layout, parent, false);
                viewHolder = new LoadOrderItemsHolder(v_image_msg);
                break;
        }

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void retrieveAllOrderItem(final LoadOrderItemsHolder holder, int position) {

        final OrderItem data = (OrderItem) list.get(position);

        holder.txtItemName.setText(data.getItemName());

        String unit = data.getQuantity() + " " + data.getUnit();
        holder.txtItemQty.setText(unit);



    }


}
