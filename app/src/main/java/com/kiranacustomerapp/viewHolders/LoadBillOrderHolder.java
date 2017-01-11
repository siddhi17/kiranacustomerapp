package com.kiranacustomerapp.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kiranacustomerapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Siddhi on 1/11/2017.
 */
public class LoadBillOrderHolder extends RecyclerView.ViewHolder  {

    public TextView tv_Ototal,tv_Oquantity,tv_Odate;
    public LinearLayout lay_row;
    public Button receipt;


    public LoadBillOrderHolder(View itemView) {
        super(itemView);

        tv_Oquantity = (TextView) itemView.findViewById(R.id.textView_items);
        tv_Odate = (TextView) itemView.findViewById(R.id.textView_s_date);
        tv_Ototal = (TextView) itemView.findViewById(R.id.textView_amount);
        receipt = (Button) itemView.findViewById(R.id.button_view_receipt);

    }



}
