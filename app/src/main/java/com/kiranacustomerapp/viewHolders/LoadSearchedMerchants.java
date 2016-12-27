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
 * Created by Siddhi on 12/26/2016.
 */
public class LoadSearchedMerchants   extends RecyclerView.ViewHolder{
    public ImageView img_mer_avatar;
    public TextView tv_kiarana_name,tv_address;
    public LinearLayout lay_row;
    public Button btnAdd;


    public LoadSearchedMerchants(View v) {
        super(v);
        tv_kiarana_name = (TextView) v.findViewById(R.id.tv_kirana_name);
        tv_address = (TextView) v.findViewById(R.id.textViewAddress);
        lay_row = (LinearLayout) v.findViewById(R.id.lay_row);
        btnAdd = (Button) v.findViewById(R.id.btnAdd);
        //img_cust_avatar = (ImageView) v.findViewById(R.id.img_cust_avatar);


    }
}
