package com.kiranacustomerapp.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kiranacustomerapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Siddhi on 11/26/2016.
 */
public class LoadMerchantsHolder  extends RecyclerView.ViewHolder{

    public ImageView imgFavEmpty,imgFavFill;
    public TextView tv_kiarana_name,tv_address;
    public LinearLayout lay_row;
    public RelativeLayout layoutFav;
    public Button btnPlaceOrder;
    public CircleImageView img_mer_avatar;


    public LoadMerchantsHolder(View v) {
        super(v);
        tv_kiarana_name = (TextView) v.findViewById(R.id.tv_kirana_name);
        tv_address = (TextView) v.findViewById(R.id.textViewAddress);
        lay_row = (LinearLayout) v.findViewById(R.id.lay_row);
        layoutFav = (RelativeLayout) v.findViewById(R.id.layoutFav);
        imgFavEmpty = (ImageView) v.findViewById(R.id.imageViewEmpty);
        imgFavFill = (ImageView) v.findViewById(R.id.imageViewFill);
        btnPlaceOrder = (Button) v.findViewById(R.id.btnPlacePrder);
        img_mer_avatar = (CircleImageView) v.findViewById(R.id.imageViewAvatar);


    }

}
