/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kiranacustomerapp.helper;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{

    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
   @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
      ///  final Intent intent = new Intent("tokenReceiver");
        // You can also include some extra data.
      //  final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
      //  intent.putExtra("token",refreshedToken);
      //  broadcastManager.sendBroadcast(intent);
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.

        // To implement: Only if user is registered, i.e. UserId is available in preference, update token on server.

        if (!token.equals("")) {

            SharedPreferences.Editor editor  = getApplicationContext().getSharedPreferences("Token", getApplicationContext().MODE_PRIVATE).edit();

            editor.putString("token", token);

            editor.commit();

        }

    }

}

