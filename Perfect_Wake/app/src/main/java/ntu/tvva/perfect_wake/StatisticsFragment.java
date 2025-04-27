package ntu.tvva.perfect_wake;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Locale;

public class StatisticsFragment extends Fragment {
    private TextView tvTimeLabel;
    private TextView tvSelectedTime;
    private TextView tvResultLabel;
    private TextView[] tvTimes;
    private RadioGroup rgFunction;
    private Calendar selectedTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        // Khởi tạo các view
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

        // Khởi tạo thời gian mặc định
        selectedTime = Calendar.getInstance();
        selectedTime.set(Calendar.HOUR_OF_DAY, 23);
        selectedTime.set(Calendar.MINUTE, 0);
        updateTimeDisplay();

        // Thiết lập sự kiện click cho TextView thời gian
        tvSelectedTime.setOnClickListener(v -> showTimePicker());

        // Thiết lập sự kiện cho RadioGroup
        rgFunction.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbWakeToSleep) {
                tvTimeLabel.setText("Thời gian thức dậy");
                tvResultLabel.setText("Các thời điểm ngủ tốt nhất");
            } else {
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
        boolean isWakeToSleep = rgFunction.getCheckedRadioButtonId() == R.id.rbWakeToSleep;
        Calendar time = (Calendar) selectedTime.clone();
        
        for (int i = 0; i < 7; i++) {
            int cycle = i + 1;
            int minutes = cycle * 90; // 1.5 giờ mỗi chu kỳ
            
            if (isWakeToSleep) {
                // Tính thời gian ngủ (trừ đi thời gian)
                time.add(Calendar.MINUTE, -minutes);
            } else {
                // Tính thời gian thức dậy (cộng thêm thời gian)
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
} 