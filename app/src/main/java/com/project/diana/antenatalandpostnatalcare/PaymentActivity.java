package com.project.diana.antenatalandpostnatalcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidstudy.daraja.Daraja;
import com.androidstudy.daraja.DarajaListener;
import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.model.LNMExpress;
import com.androidstudy.daraja.model.LNMResult;
import com.androidstudy.daraja.util.TransactionType;

public class PaymentActivity extends AppCompatActivity {
    Daraja daraja;
    LNMExpress lnmExpress;
    private static final String TAG = "PaymentActivity";
    private ProgressDialog loadingBar;
    public static final String BUSINESS_SHORT_CODE = "174379";
    public static final String PASSKEY = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
    public static final String CONSUMER_KEY = "XUoqTzugqww0ew1wuLGGgpkwaN6BERW2";
    public static final String CONSUMER_SECRET = "fhnmOKzU2dYl6vk4";
    public static final String CALLBACK_URL = "https://2f50f430.ngrok.io/callback.php?key=kiduyuKLAUS1995";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        daraja = Daraja.with("XUoqTzugqww0ew1wuLGGgpkwaN6BERW2", "fhnmOKzU2dYl6vk4", new DarajaListener<AccessToken>() {
            @Override
            public void onResult(@NonNull AccessToken accessToken) {
                Log.i(this.getClass().getSimpleName(), accessToken.getAccess_token());
                Toast.makeText(PaymentActivity.this, "TOKEN : " + accessToken.getAccess_token(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Log.e(PaymentActivity.this.getClass().getSimpleName(), error);
            }
        });
        int overTotalPrice=200;
        String overT=String.valueOf(overTotalPrice);
        lnmExpress = new LNMExpress(
                "174379",
                "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",  //https://developer.safaricom.co.ke/test_credentials
                TransactionType.CustomerPayBillOnline,
                overT,
                "254719629173",
                "174379",
                "254719629173",
                "http://meal.shulemall.com/api",
                "Antenatal App",
                "Goods Payment"
        );

    }

    public void LoadMpesa(View view) {
        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Sending Mpesa Request");
        loadingBar.setMessage("Please wait, while we are Verifying the Transaction.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        Toast.makeText(this, "Mpesa Loading.. Input Pin", Toast.LENGTH_LONG).show();


        daraja.requestMPESAExpress(lnmExpress,
                new DarajaListener<LNMResult>() {
                    @Override
                    public void onResult(@NonNull LNMResult lnmResult) {
                        Log.i(PaymentActivity.this.getClass().getSimpleName(), lnmResult.ResponseDescription);
                        Log.d(TAG, "onResult: "+lnmResult.ResponseDescription);
                        loadingBar.dismiss();
                    }

                    @Override
                    public void onError(String error) {
                        Log.i(PaymentActivity.this.getClass().getSimpleName(), error);
                        Log.d(TAG, "onError: "+error);
                        loadingBar.dismiss();
                    }
                }
        );


    }
}