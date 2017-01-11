package com.kiranacustomerapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.kiranacustomerapp.Activities.MerchantProfileActivity;
import com.kiranacustomerapp.AsyncTasks.SendRequestAsyncTask;
import com.kiranacustomerapp.Models.Merchants;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.SessionData;
import com.kiranacustomerapp.viewHolders.LoadSearchedMerchants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siddhi on 12/26/2016.
 */
public class SearchedMerchantsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable{
    static final int LOAD_MERCHANTS = 0;
    private Context context;
    public ArrayList<Merchants> list;
    public ArrayList<Merchants> baselist = new ArrayList<>();

    public SearchedMerchantsAdapter(Context context, ArrayList<Merchants> list) {
        this.context = context;
        this.list = list;
        this.baselist = list;
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
                LoadSearchedMerchants loadSearchedMerchants = (LoadSearchedMerchants) holder;
                retriveAllMerchants(loadSearchedMerchants, position);
                break;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (viewType) {
            case LOAD_MERCHANTS:
                View v_image_msg = inflater.inflate(R.layout.search_merchant_layout, parent, false);
                viewHolder = new LoadSearchedMerchants(v_image_msg);
                break;
        }

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void retriveAllMerchants(final LoadSearchedMerchants holder, int position) {

        final Merchants data = (Merchants) list.get(position);

        holder.tv_kiarana_name.setText(data.getKirana_name());
        holder.tv_address.setText(data.getMerchant_address());


        String url = "http://104.131.162.126/testslim/v1/src/images/" + data.getAvatar();

        Picasso.with(context)
                .load(url)
                .resize(400,400)
                .placeholder(R.drawable.avatar1)
                .error(R.drawable.avatar1)
                .into(holder.img_mer_avatar);

        holder.lay_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, MerchantProfileActivity.class);

                i.putExtra("cont_name",data.getMerchant_name());
                i.putExtra("kirana_name",data.getKirana_name());
                i.putExtra("email_id",data.getEmail_id());
                i.putExtra("phone_no",data.getPhone());
                i.putExtra("address",data.getMerchant_address());
                i.putExtra("avatar",data.getAvatar());

                context.startActivity(i);

            }
        });

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SessionData sessionData;
                final String sessionUserId,access_token;

                sessionData=new SessionData(context);
                sessionUserId = sessionData.getString("user_id","-1");
                access_token = sessionData.getString("api_key", "-1");

                new SendRequestAsyncTask(context).execute(sessionUserId,access_token,data.getMerchant_id().toString());

            }
        });

    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Merchants> results = new ArrayList<>();

                if (constraint != null) {
                    if (baselist != null && baselist.size() > 0) {
                        for (final Merchants g : baselist ) {
                            if (g.getKirana_name()
                                    .contains(constraint.toString())||g.getMerchant_address()
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
                list = (ArrayList<Merchants>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
