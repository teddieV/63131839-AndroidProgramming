package ntu.tvva.sleepcalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/**
 * Adapter cho RecyclerView hiển thị kết quả tính toán
 */
public class KetQuaAdapter extends RecyclerView.Adapter<KetQuaAdapter.KetQuaViewHolder> {
    private ArrayList<String> danhSachKetQua;

    /**
     * ViewHolder cho mỗi item trong RecyclerView
     */
    public static class KetQuaViewHolder extends RecyclerView.ViewHolder {
        public TextView thoiGian;
        public TextView chuKy;

        public KetQuaViewHolder(View itemView) {
            super(itemView);
            thoiGian = itemView.findViewById(R.id.thoiGian);
            chuKy = itemView.findViewById(R.id.chuKy);
        }
    }

    /**
     * Khởi tạo adapter với danh sách kết quả
     * @param danhSachKetQua Danh sách kết quả cần hiển thị
     */
    public KetQuaAdapter(ArrayList<String> danhSachKetQua) {
        this.danhSachKetQua = danhSachKetQua;
    }

    @NonNull
    @Override
    public KetQuaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ket_qua, parent, false);
        return new KetQuaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KetQuaViewHolder holder, int position) {
        String ketQua = danhSachKetQua.get(position);
        if (position == 0) {
            // Item đầu tiên là thời gian gốc
            holder.thoiGian.setText(ketQua);
            holder.chuKy.setVisibility(View.GONE);
        } else {
            // Các item còn lại là thời gian và chu kỳ
            String[] parts = ketQua.split(" \\(");
            if (parts.length >= 1) {
                holder.thoiGian.setText(parts[0]);
                if (parts.length >= 2) {
                    String chuKyText = parts[1].replace(")", "");
                    holder.chuKy.setText(chuKyText);
                    holder.chuKy.setVisibility(View.VISIBLE);
                } else {
                    holder.chuKy.setVisibility(View.GONE);
                }
            } else {
                holder.thoiGian.setText(ketQua);
                holder.chuKy.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return danhSachKetQua.size();
    }
} 