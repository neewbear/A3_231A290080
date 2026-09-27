package vn.edu.vhu.ltdd.a3layout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "A3_231A290080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cho phép nội dung hiển thị edge-to-edge
        EdgeToEdge.enable(this);

        // Nạp giao diện
        setContentView(R.layout.activity_main);

        // Xử lý khoảng cách với thanh trạng thái / navigation
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets bars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            bars.left,
                            bars.top,
                            bars.right,
                            bars.bottom
                    );

                    return insets;
                }
        );

        // Kiểm tra đang dùng giao diện dọc hay ngang
        boolean landscape =
                getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE;

        Log.d(
                TAG,
                "Hệ thống đã nạp layout: "
                        + (landscape
                        ? "res/layout-land"
                        : "res/layout")
        );

        // Ánh xạ các View
        Button btnLogin = findViewById(R.id.btnLogin);

        Button btnConstraintDemo =
                findViewById(R.id.btnConstraintDemo);

        CheckBox cbRemember =
                findViewById(R.id.cbRemember);

        // Nút ĐĂNG NHẬP
        btnLogin.setOnClickListener(v -> {

            String message =
                    getString(R.string.login_success)
                            + (
                            cbRemember.isChecked()
                                    ? " (đã ghi nhớ)"
                                    : ""
                    );

            Snackbar.make(
                    v,
                    message,
                    Snackbar.LENGTH_SHORT
            ).show();
        });

        // Mở màn hình ConstraintLayout
        btnConstraintDemo.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ConstraintDemoActivity.class
            );

            startActivity(intent);
        });
    }
}