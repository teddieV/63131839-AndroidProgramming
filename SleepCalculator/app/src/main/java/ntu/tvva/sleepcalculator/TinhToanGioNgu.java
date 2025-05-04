package ntu.tvva.sleepcalculator;

import java.util.ArrayList;

/**
 * Lớp xử lý logic tính toán giờ ngủ và thức dậy
 */
public class TinhToanGioNgu {
    private static final int THOI_GIAN_NGU_TRUNG_BINH = 15; // phút
    private static final int THOI_GIAN_CHU_KY_NGU = 90; // phút
    private static final int SO_CHU_KY_TOI_THIEU = 3;
    private static final int SO_CHU_KY_TOI_DA = 8;

    /**
     * Tính toán các thời điểm đi ngủ để thức dậy vào giờ đã chọn
     * @param gioThucDay Giờ thức dậy
     * @param phutThucDay Phút thức dậy
     * @return Danh sách các thời điểm đi ngủ
     */
    public static ArrayList<String> tinhGioDiNgu(int gioThucDay, int phutThucDay) {
        ArrayList<String> ketQua = new ArrayList<>();
        
        // Đảm bảo giờ và phút hợp lệ
        gioThucDay = gioThucDay % 24;
        phutThucDay = phutThucDay % 60;
        
        ketQua.add(String.format("%02d:%02d", gioThucDay, phutThucDay));

        // Tính thời gian thức dậy theo phút
        int thoiGianThucDay = gioThucDay * 60 + phutThucDay;

        // Tính các thời điểm đi ngủ
        for (int chuKy = SO_CHU_KY_TOI_DA; chuKy >= SO_CHU_KY_TOI_THIEU; chuKy--) {
            int thoiGianNgu = chuKy * THOI_GIAN_CHU_KY_NGU + THOI_GIAN_NGU_TRUNG_BINH;
            int thoiGianDiNgu = thoiGianThucDay - thoiGianNgu;
            
            // Xử lý trường hợp thời gian âm (qua ngày)
            while (thoiGianDiNgu < 0) {
                thoiGianDiNgu += 24 * 60;
            }
            
            int gioDiNgu = (thoiGianDiNgu / 60) % 24;
            int phutDiNgu = thoiGianDiNgu % 60;
            
            ketQua.add(String.format("%02d:%02d (%d chu kỳ)", gioDiNgu, phutDiNgu, chuKy));
        }

        return ketQua;
    }

    /**
     * Tính toán các thời điểm thức dậy nếu đi ngủ vào giờ đã chọn
     * @param gioDiNgu Giờ đi ngủ
     * @param phutDiNgu Phút đi ngủ
     * @return Danh sách các thời điểm thức dậy
     */
    public static ArrayList<String> tinhGioThucDay(int gioDiNgu, int phutDiNgu) {
        ArrayList<String> ketQua = new ArrayList<>();
        
        // Đảm bảo giờ và phút hợp lệ
        gioDiNgu = gioDiNgu % 24;
        phutDiNgu = phutDiNgu % 60;
        
        ketQua.add(String.format("%02d:%02d", gioDiNgu, phutDiNgu));

        // Tính thời gian đi ngủ theo phút
        int thoiGianDiNgu = gioDiNgu * 60 + phutDiNgu;

        // Tính các thời điểm thức dậy
        for (int chuKy = SO_CHU_KY_TOI_THIEU; chuKy <= SO_CHU_KY_TOI_DA; chuKy++) {
            int thoiGianNgu = chuKy * THOI_GIAN_CHU_KY_NGU + THOI_GIAN_NGU_TRUNG_BINH;
            int thoiGianThucDay = thoiGianDiNgu + thoiGianNgu;
            
            // Xử lý trường hợp thời gian vượt quá 24h
            while (thoiGianThucDay >= 24 * 60) {
                thoiGianThucDay -= 24 * 60;
            }
            
            int gioThucDay = thoiGianThucDay / 60;
            int phutThucDay = thoiGianThucDay % 60;
            
            ketQua.add(String.format("%02d:%02d (%d chu kỳ)", gioThucDay, phutThucDay, chuKy));
        }

        return ketQua;
    }
} 