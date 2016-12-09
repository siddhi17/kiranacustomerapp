package com.kiranacustomerapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.kiranacustomerapp.R;
import com.kiranacustomerapp.helper.CommonUtils;
import com.kiranacustomerapp.helper.Excpetion2JSON;
import com.kiranacustomerapp.helper.ServerRequest;
import com.kiranacustomerapp.helper.SessionData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OtpConfirmation extends AppCompatActivity {

    String phone_no,email_id;
    EditText edt_otp;
    Button btn_submit_otp,btn_resend_otp;
    CoordinatorLayout coordinatorLayout;
    private ProgressDialog loadingDialog;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpconfirmation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        loadingDialog=new ProgressDialog(OtpConfirmation.this);
        edt_otp=(EditText)findViewById(R.id.edt_otp);
        btn_submit_otp=(Button) findViewById(R.id.btn_submit_otp);
        btn_resend_otp=(Button) findViewById(R.id.btn_resend_otp);

        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinatorLayout);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            phone_no=bundle.getString("phone_no");
            email_id=bundle.getString("email_id");
        }

        btn_resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send otp message to user
                sendOTP();

            }
        });

        //send otp message to user
        sendOTP();

        //validate otp enter by user
        doUserActivation();

    }

    public void  sendOTP(){

        View view1 = getCurrentFocus();
        if (view1 != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
        if (CommonUtils.isConnectedToInternet(getApplicationContext())) {
            //send otpsms asycTask
            SendOtpAsyncTask task = new SendOtpAsyncTask();
            task.execute(phone_no,email_id);
        }else {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Not able to connect. Please check your network connection & try again.", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    public void  doUserActivation(){
        btn_submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp=edt_otp.getText().toString();
                //validate otp enter by user async

                View view1 = getCurrentFocus();
                if (view1 != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
                }
                if (!otp.equals("")){
                    ActivateUserAsyncTask task = new ActivateUserAsyncTask();
                    task.execute(otp,email_id);
                }else {
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "Please enter the OTP", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });
    }

    public class SendOtpAsyncTask extends AsyncTask<String, Void, JSONObject> {
        String api;
        JSONObject jsonParams;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingDialog = ProgressDialog.show(OtpConfirmation.this, null, "please wait...");
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                api = getResources().getString(R.string.server_url) + "sendCustomerOTP";

                jsonParams = new JSONObject();
                jsonParams.put("phone_no", params[0]);
                jsonParams.put("email_id", params[1]);

                ServerRequest request = new ServerRequest(api, jsonParams);
                return request.sendRequest();

            } catch(JSONException je) {
                return Excpetion2JSON.getJSON(je);
            } catch (Exception ue) {
                return Excpetion2JSON.getJSON(ue);
            }
        }  //end of doInBackground

        @Override
        protected void onPostExecute(JSONObject response) {
            super.onPostExecute(response);
            if (loadingDialog.isShowing())
                loadingDialog.dismiss();
            try {
                JSONArray jsonArray = response.getJSONArray("array");
                Log.d("ServerResponseOTP", ""+jsonArray);
                if(jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.has("message")) {
                            String message = jsonObject.getString("message");
                            if (message.equals("inserted successfully")){
                                Snackbar snackbar = Snackbar.make(coordinatorLayout, getString(R.string.otp_sent), Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }else {

                            }
                        } else {

                        }
                    }

                }
            } catch(JSONException je) {
                je.printStackTrace();
               // Toast.makeText(getApplicationContext(), je.getMessage(), Toast.LENGTH_LONG).show();
            }
        } //end of onPostExecute
    }

    public class ActivateUserAsyncTask extends AsyncTask<String, Void, JSONObject> {
        String api;
        JSONObject jsonParams;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingDialog = ProgressDialog.show(OtpConfirmation.this, null, "please wait...");
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                api = getResources().getString(R.string.server_url) + "confirmCustomerOTP";

                jsonParams = new JSONObject();
                jsonParams.put("otp", params[0]);
                jsonParams.put("email_id", params[1]);

                ServerRequest request = new ServerRequest(api, jsonParams);
                return request.sendRequest();

            } catch(JSONException je) {
                return Excpetion2JSON.getJSON(je);
            } catch (Exception ue) {
                return Excpetion2JSON.getJSON(ue);
            }
        }  //end of doInBackground

        @Override
        protected void onPostExecute(JSONObject response) {
            super.onPostExecute(response);
            if (loadingDialog.isShowing())
                loadingDialog.dismiss();

            try {
                JSONArray jsonArray = response.getJSONArray("array");
                Log.d("ServerResponsejsonArray", ""+jsonArray);
                if(jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.has("message")) {
                            String message = jsonObject.getString("message");
                            if (message.equals("confirm successfully")){
                               // Toast.makeText(getApplicationContext(), "you are an active Merchant!", Toast.LENGTH_SHORT).show();

                                SessionData session = new SessionData(OtpConfirmation.this);
                                session.add("status",1);

                                startActivity(new Intent(OtpConfirmation.this,HomeActivity.class));
                                finish();


                            }else {
                                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Incorrect OTP !", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        } else {
                           // Toast.makeText(getApplicationContext(), "error in call of api", Toast.LENGTH_LONG).show();
                        }
                    }

                }
            } catch(JSONException je) {
                je.printStackTrace();
               // Toast.makeText(getApplicationContext(), je.getMessage(), Toast.LENGTH_LONG).show();
            }
        } //end of onPostExecute
    }
}
