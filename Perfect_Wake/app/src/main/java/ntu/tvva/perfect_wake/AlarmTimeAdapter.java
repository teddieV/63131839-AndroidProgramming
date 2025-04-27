package ntu.tvva.perfect_wake;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import java.util.Calendar;
import java.util.List;
import android.os.Build;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.app.AlertDialog;

public class AlarmTimeAdapter extends RecyclerView.Adapter<AlarmTimeAdapter.AlarmTimeViewHolder> {

    private List<AlarmTime> alarmList;
    private OnAlarmToggleListener listener;
    private Context context;

    public interface OnAlarmToggleListener {
        void onAlarmToggle(AlarmTime alarmTime, boolean isEnabled);
    }

    public AlarmTimeAdapter(Context context, List<AlarmTime> alarmList, OnAlarmToggleListener listener) {
        this.context = context;
        this.alarmList = alarmList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlarmTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tạo view từ layout XML
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_alarm_time, parent, false);
        return new AlarmTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmTimeViewHolder holder, int position) {
        // Lấy thông tin báo thức tại vị trí position
        AlarmTime alarmTime = alarmList.get(position);

        // Hiển thị thông tin
        holder.timeText.setText(alarmTime.getTime());
        holder.detailText.setText(alarmTime.getDetail());

        // Cập nhật icon báo thức
        holder.alarmIcon.setImageResource(R.drawable.ic_alarm);

        // Cập nhật trạng thái công tắc (nếu có)
        if (holder.alarmSwitch != null) {
            holder.alarmSwitch.setChecked(alarmTime.isActive());
            holder.alarmSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                alarmTime.setActive(isChecked);
                if (listener != null) {
                    listener.onAlarmToggle(alarmTime, isChecked);
                }
                // Gọi setAlarm để đặt báo thức
                setAlarm(context, alarmTime, isChecked);
            });
        }
    }

    private void setAlarm(Context context, AlarmTime alarmTime, boolean isActive) {
        if (isActive) {
            try {
                // Tách giờ và phút từ chuỗi thời gian
                String[] timeParts = alarmTime.getTime().split(":");
                if (timeParts.length != 2) {
                    Toast.makeText(context, "Định dạng thời gian không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }

                int hour = Integer.parseInt(timeParts[0]);
                int minute = Integer.parseInt(timeParts[1]);

                // Tạo thời gian cho báo thức
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);

                // Nếu thời gian đã qua trong ngày, đặt cho ngày hôm sau
                if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                }

                // Tạo Intent cho AlarmReceiver
                Intent intent = new Intent(context, AlarmReceiver.class);
                intent.putExtra("alarm_time", alarmTime.getTime());
                intent.putExtra("alarm_detail", alarmTime.getDetail());

                // Tạo PendingIntent
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context,
                    alarmTime.getTime().hashCode(), // Sử dụng thời gian làm requestCode
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                );

                // Lấy AlarmManager
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                // Đặt báo thức
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setAlarmClock(
                        new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent),
                        pendingIntent
                    );
                } else {
                    alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        pendingIntent
                    );
                }

                Toast.makeText(context, 
                    "Đã đặt báo thức cho " + alarmTime.getTime() + "\n" + alarmTime.getDetail(), 
                    Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(context, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {
            try {
                // Hủy báo thức
                Intent intent = new Intent(context, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context,
                    alarmTime.getTime().hashCode(),
                    intent,
                    PendingIntent.FLAG_NO_CREATE | PendingIntent.FLAG_IMMUTABLE
                );

                if (pendingIntent != null) {
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                    pendingIntent.cancel();
                }

                Toast.makeText(context, "Đã tắt báo thức lúc " + alarmTime.getTime(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, "Lỗi khi tắt báo thức: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    private void datBaoThuc(AlarmTime alarmTime, boolean isEnabled) {
        try {
            // Tách giờ và phút từ thời gian
            String[] timeParts = alarmTime.getTime().split(":");
            int gio = Integer.parseInt(timeParts[0]);
            int phut = Integer.parseInt(timeParts[1]);

            // Tạo báo thức mới
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
            intent.putExtra(AlarmClock.EXTRA_HOUR, gio);
            intent.putExtra(AlarmClock.EXTRA_MINUTES, phut);
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Perfect Wake");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Mở app báo thức bằng context
            context.startActivity(intent);
            
            // Hiện thông báo thành công
            Toast.makeText(context, "Đã đặt báo thức " + alarmTime.getTime(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            // Nếu có lỗi thì hiện thông báo
            Toast.makeText(context, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public static class AlarmTimeViewHolder extends RecyclerView.ViewHolder {
        TextView timeText, detailText;     // Text hiển thị thời gian và chi tiết
        ImageView alarmIcon;               // Icon báo thức
        SwitchMaterial alarmSwitch;        // Công tắc bật/tắt báo thức

        public AlarmTimeViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ các view từ layout
            timeText = itemView.findViewById(R.id.timeText);
            detailText = itemView.findViewById(R.id.detailText);
            alarmIcon = itemView.findViewById(R.id.alarmIcon);
            alarmSwitch = itemView.findViewById(R.id.alarmSwitch);
        }
    }
}