package ntu.tvva.perfect_wake;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private TextView tvTimeLabel;
    private TextView tvSelectedTime;
    private TextView tvResultLabel;
    private TextView[] tvTimes;
    private ImageButton[] btnAlarms;
    private RadioGroup rgFunction;
    private Calendar selectedTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Khởi tạo các thành phần
        tvTimeLabel = view.findViewById(R.id.tvTimeLabel);
        tvSelectedTime = view.findViewById(R.id.tvSelectedTime);
        tvResultLabel = view.findViewById(R.id.tvResultLabel);
        rgFunction = view.findViewById(R.id.rgFunction);

        // Khởi tạo mảng TextView cho các thời gian
        tvTimes = new TextView[7];
        tvTimes[0] = view.findViewById(R.id.tvTime1);
        tvTimes[1] = view.findViewById(R.id.tvTime2);
        tvTimes[2] = view.findViewById(R.id.tvTime3);
        tvTimes[3] = view.findViewById(R.id.tvTime4);
        tvTimes[4] = view.findViewById(R.id.tvTime5);
        tvTimes[5] = view.findViewById(R.id.tvTime6);
        tvTimes[6] = view.findViewById(R.id.tvTime7);

        // Khởi tạo mảng ImageButton cho các nút báo thức
        btnAlarms = new ImageButton[7];
        btnAlarms[0] = view.findViewById(R.id.btnAlarm1);
        btnAlarms[1] = view.findViewById(R.id.btnAlarm2);
        btnAlarms[2] = view.findViewById(R.id.btnAlarm3);
        btnAlarms[3] = view.findViewById(R.id.btnAlarm4);
        btnAlarms[4] = view.findViewById(R.id.btnAlarm5);
        btnAlarms[5] = view.findViewById(R.id.btnAlarm6);
        btnAlarms[6] = view.findViewById(R.id.btnAlarm7);

        // Thiết lập sự kiện click cho các nút báo thức
        for (int i = 0; i < btnAlarms.length; i++) {
            final int index = i;
            btnAlarms[i].setOnClickListener(v -> setAlarm(index));
        }

        // Khởi tạo thời gian mặc định
        selectedTime = Calendar.getInstance();
        selectedTime.set(Calendar.HOUR_OF_DAY, 23);
        selectedTime.set(Calendar.MINUTE, 0);
        updateTimeDisplay();

        // Thiết lập sự kiện click cho TextView thời gian
        tvSelectedTime.setOnClickListener(v -> showTimePicker());

        // Thiết lập sự kiện cho RadioGroup
        rgFunction.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbSleepMode) {
                // Chế độ Ngủ: "Khi nào đi ngủ để thức dậy lúc ..."
                tvTimeLabel.setText("Thời gian thức dậy");
                tvResultLabel.setText("Các thời điểm ngủ tốt nhất");
            } else {
                // Chế độ Thức dậy: "Khi nào thức dậy nếu tôi ngủ lúc ..."
                tvTimeLabel.setText("Thời gian ngủ");
                tvResultLabel.setText("Các thời điểm thức dậy tốt nhất");
            }
            calculateTimes();
        });

        return view;
    }

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),
                (view, hourOfDay, minute) -> {
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    selectedTime.set(Calendar.MINUTE, minute);
                    updateTimeDisplay();
                    calculateTimes();
                },
                selectedTime.get(Calendar.HOUR_OF_DAY),
                selectedTime.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    private void updateTimeDisplay() {
        String time = String.format(Locale.getDefault(), "%02d:%02d",
                selectedTime.get(Calendar.HOUR_OF_DAY),
                selectedTime.get(Calendar.MINUTE));
        tvSelectedTime.setText(time);
    }

    private void calculateTimes() {
        boolean isSleepMode = rgFunction.getCheckedRadioButtonId() == R.id.rbSleepMode;
        Calendar time = (Calendar) selectedTime.clone();
        
        for (int i = 0; i < 7; i++) {
            int cycle = i + 1;
            int minutes = cycle * 90; // 1.5 giờ mỗi chu kỳ
            
            if (isSleepMode) {
                // Chế độ Ngủ: tính thời gian ngủ (trừ đi thời gian)
                time.add(Calendar.MINUTE, -minutes);
            } else {
                // Chế độ Thức dậy: tính thời gian thức dậy (cộng thêm thời gian)
                time.add(Calendar.MINUTE, minutes);
            }
            
            updateTimeDisplay(tvTimes[i], time, cycle);
            
            // Reset về thời gian gốc cho lần tính tiếp theo
            time = (Calendar) selectedTime.clone();
        }
    }

    private void updateTimeDisplay(TextView textView, Calendar time, int cycle) {
        String timeStr = String.format(Locale.getDefault(), "%02d:%02d",
                time.get(Calendar.HOUR_OF_DAY),
                time.get(Calendar.MINUTE));
        String displayText = String.format(Locale.getDefault(), "%s (Chu kỳ %d - %d giờ %d phút)",
                timeStr,
                cycle,
                (cycle * 90) / 60,
                (cycle * 90) % 60);
        textView.setText(displayText);
    }

    private void setAlarm(int index) {
        try {
            // Lấy thời gian từ TextView tương ứng
            String timeText = tvTimes[index].getText().toString();
            String time = timeText.substring(0, 5); // Lấy phần "HH:mm"
            
            // Tách giờ và phút
            String[] timeParts = time.split(":");
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);

            // Tạo Calendar với thời gian báo thức
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);

            // Nếu thời gian đã qua trong ngày, đặt cho ngày hôm sau
            if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

            // Tạo intent để mở ứng dụng đặt báo thức
            Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
            alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, hour);
            alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, minute);
            alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, "Perfect Wake - Chu kỳ " + (index + 1));
            alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, false);

            // Kiểm tra xem có ứng dụng nào có thể xử lý intent này không
            PackageManager packageManager = requireActivity().getPackageManager();
            if (alarmIntent.resolveActivity(packageManager) != null) {
                startActivity(alarmIntent);
                Toast.makeText(requireContext(), "Mở ứng dụng đặt báo thức", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Không tìm thấy ứng dụng đặt báo thức", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Lỗi khi đặt báo thức: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
} 