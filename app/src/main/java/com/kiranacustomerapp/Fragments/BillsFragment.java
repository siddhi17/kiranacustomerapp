package com.kiranacustomerapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kiranacustomerapp.Activities.HomeActivity;
import com.kiranacustomerapp.Adapters.BillsAdapter;
import com.kiranacustomerapp.Models.Bills;
import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.CommonUtils;
import com.kiranacustomerapp.helper.Excpetion2JSON;
import com.kiranacustomerapp.helper.ServerRequest;
import com.kiranacustomerapp.helper.SessionData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BillsFragment extends Fragment {




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bills, container, false);



        return view;
    }

}
