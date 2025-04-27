package ntu.tvva.perfect_wake;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    private static final int NOTIFICATION_PERMISSION_CODE = 1001;
    private Switch switchDarkMode;
    private Switch switchNotifications;
    private Switch switchVibration;
    private SharedPreferences prefs;
    private NotificationManager notificationManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Khởi tạo SharedPreferences
        prefs = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        notificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        // Khởi tạo các switch
        switchDarkMode = view.findViewById(R.id.switchDarkMode);
        switchNotifications = view.findViewById(R.id.switchNotifications);
        switchVibration = view.findViewById(R.id.switchVibration);

        // Tải cài đặt đã lưu
        loadSettings();

        // Thiết lập sự kiện thay đổi cho các switch
        setupSwitchListeners();

        return view;
    }

    private void loadSettings() {
        // Đọc cài đặt từ SharedPreferences
        boolean isDarkMode = prefs.getBoolean("dark_mode", false);
        boolean notificationsEnabled = prefs.getBoolean("notifications", true);
        boolean vibrationEnabled = prefs.getBoolean("vibration", true);

        // Áp dụng cài đặt cho các switch
        switchDarkMode.setChecked(isDarkMode);
        switchNotifications.setChecked(notificationsEnabled);
        switchVibration.setChecked(vibrationEnabled);

        // Áp dụng chế độ tối/sáng
        applyDarkMode(isDarkMode);
    }

    private void setupSwitchListeners() {
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("dark_mode", isChecked).apply();
            applyDarkMode(isChecked);
        });

        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                requestNotificationPermission();
            } else {
                disableNotifications();
            }
            prefs.edit().putBoolean("notifications", isChecked).apply();
        });

        switchVibration.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("vibration", isChecked).apply();
            // Lưu cài đặt rung vào SharedPreferences
            // Việc áp dụng rung sẽ được xử lý khi tạo thông báo
        });
    }

    private void applyDarkMode(boolean isDarkMode) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        // Yêu cầu Activity khởi động lại để áp dụng theme mới
        requireActivity().recreate();
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(), 
                    android.Manifest.permission.POST_NOTIFICATIONS) 
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                    NOTIFICATION_PERMISSION_CODE
                );
            }
        }
    }

    private void disableNotifications() {
        if (notificationManager != null) {
            notificationManager.cancelAll();
            Toast.makeText(requireContext(), "Đã tắt thông báo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Đã bật thông báo", Toast.LENGTH_SHORT).show();
            } else {
                switchNotifications.setChecked(false);
                Toast.makeText(requireContext(), "Không thể bật thông báo do thiếu quyền", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Lưu tất cả cài đặt khi Fragment bị tạm dừng
        prefs.edit()
            .putBoolean("dark_mode", switchDarkMode.isChecked())
            .putBoolean("notifications", switchNotifications.isChecked())
            .putBoolean("vibration", switchVibration.isChecked())
            .apply();
    }
} 