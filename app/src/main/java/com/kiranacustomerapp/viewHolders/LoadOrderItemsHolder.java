package com.kiranacustomerapp.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kiranacustomerapp.R;

/**
 * Created by Siddhi on 12/11/2016.
 */
public class LoadOrderItemsHolder extends   RecyclerView.ViewHolder{

    public TextView txtItemName,txtItemQty;
    public RelativeLayout relativeRow;

    public LoadOrderItemsHolder(View v) {
        super(v);
        txtItemName = (TextView) v.findViewById(R.id.textViewItemName);
        txtItemQty = (TextView) v.findViewById(R.id.textViewItemQty);
        relativeRow = (RelativeLayout) v.findViewById(R.id.relativeParent);

    }
}
