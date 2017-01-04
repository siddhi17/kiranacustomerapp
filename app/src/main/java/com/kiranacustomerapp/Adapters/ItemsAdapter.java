package com.kiranacustomerapp.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.kiranacustomerapp.Activities.HomeActivity;
import com.kiranacustomerapp.Activities.SearchActivity;
import com.kiranacustomerapp.AsyncTasks.AddQueryItemAsyncTask;
import com.kiranacustomerapp.Models.OrderItem;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.FilterString;
import com.kiranacustomerapp.viewHolders.LoadOrderItemsHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siddhi on 12/19/2016.
 */
public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable{
    static final int LOAD_ORDERITEMS = 0;
    private Context context;
    private List<String> list;
    private List<String> baselist = new ArrayList<>();

    public ItemsAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        this.baselist = list;
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
                View v_image_msg = inflater.inflate(R.layout.item_layout, parent, false);
                viewHolder = new LoadOrderItemsHolder(v_image_msg);
                break;
        }

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return  list == null ? 0 : list.size();
    }

    public void retrieveAllOrderItem(final LoadOrderItemsHolder holder, int position) {

        final String data = list.get(position);

        holder.txtItemName.setText(data);

      //  String unit = data.getQuantity() + " " + data.getUnit();
      //  holder.txtItemQty.setText(unit);

        holder.relativeRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((SearchActivity)context).linearLayoutRecycleView.setVisibility(View.GONE);
                ((SearchActivity)context).linearLayoutUnits.setVisibility(View.GONE);
                ((SearchActivity)context).textInput_Item_Unit.setVisibility(View.VISIBLE);
                ((SearchActivity)context).textInput_Item_quantity.setVisibility(View.VISIBLE);
             //   ((SearchActivity)context).edt_Item_Name.setHintTextColor(ContextCompat.getColor(context,R.color.accent));
                ((SearchActivity)context).focus2 = false;
                ((SearchActivity)context).focus1 = false;

                ((SearchActivity)context).textInput_Item_name.setBackgroundResource(0);
                ((SearchActivity)context).textInput_Item_Unit.setBackgroundResource(0);
             //   ((SearchActivity)context).edt_Item_Name.setText(data.getItemName());
             //   ((SearchActivity)context).edt_Item_quantity.setText(data.getQuantity());
              //  ((SearchActivity)context).edt_Item_Unit.setText(data.getUnit());

                boolean checkQuery = FilterString.findOut(holder.txtItemName.getText().toString(),((SearchActivity)context).mItemUnitsList,((SearchActivity)context).mItemNamesList);


                if(checkQuery)
                {
                    //    QueryOrderItem query = new QueryOrderItem(edtOrderItem.getText().toString());

                    //    mOrderItemQueryList.add(edtOrderItem.getText().toString());

                    ((SearchActivity)context).edt_Item_Name.setText(FilterString.mItemName);
                    ((SearchActivity)context).edt_Item_quantity.setText(FilterString.quantity);
                    ((SearchActivity)context).edt_Item_Unit.setText(FilterString.measuringUnit);

                    FilterString.measuringUnit = "";
                    FilterString.item = "";
                    FilterString.quantity = "";
                    FilterString.mItemName = "";

                }
                else {
                    // OrderItemQuery query = new OrderItemQuery(edtOrderItem.getText().toString());
                    FilterString.measuringUnit = "";
                    FilterString.item = "";
                    FilterString.quantity = "";
                    FilterString.mItemName = "";
                }
            }
        });

    }
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<String> results = new ArrayList<>();

                if (constraint != null) {
                    if (baselist != null && baselist.size() > 0) {
                        for (final String g : baselist ) {
                            if (g.toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                list = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}