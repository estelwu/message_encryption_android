package estelwu.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import estelwu.sample.utils.CryptoHelper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvOriginalMessage)
    TextView tvOriginalMessage;

    @BindView(R.id.tvEncryptedMessage)
    TextView tvEncryptedMessage;

    @BindView(R.id.tvDecryptedMessage)
    TextView tvDecryptedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        crypto();
    }

    private void crypto() {
        CryptoHelper helper = new CryptoHelper(this);
        // Set original
        String original = "Hello World";
        tvOriginalMessage.setText(original);
        try {
            // Run encrypt: use AES to encrypt message.
            String enMsg = helper.msgEncrypt(original);
            tvEncryptedMessage.setText(enMsg);
            // Encrypted AES key: Use RSA to encrypt AES key.
            String enKey = helper.aesKeyEncrypt();

            // Decrypt message
            String deMsg = helper.msgDecrypt(enMsg, enKey);
            tvDecryptedMessage.setText(deMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}