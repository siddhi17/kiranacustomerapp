package com.kiranacustomerapp.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kiranacustomerapp.R;

/**
 * Created by Siddhi on 1/10/2017.
 */
public class LoadBillsHolder extends RecyclerView.ViewHolder {

    public TextView tv_kirana_name,tv_Start_date,tv_end_date,tv_amount;
    public LinearLayout lay_row;

    public LoadBillsHolder(View itemView) {
        super(itemView);
        tv_kirana_name = (TextView) itemView.findViewById(R.id.tv_kirana_name);
        tv_Start_date = (TextView) itemView.findViewById(R.id.tv_start_date);
        tv_end_date = (TextView) itemView.findViewById(R.id.tv_end_date);
        tv_amount = (TextView) itemView.findViewById(R.id.tv_amount);
        lay_row = (LinearLayout) itemView.findViewById(R.id.lay_row);

    }

}
