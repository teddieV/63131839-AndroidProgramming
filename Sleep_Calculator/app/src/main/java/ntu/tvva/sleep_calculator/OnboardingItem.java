package ntu.tvva.sleep_calculator;

// Lớp chứa thông tin của một trang giới thiệu
public class OnboardingItem {
    // Các thuộc tính của trang giới thiệu
    private int hinhAnh;      // ID của hình ảnh
    private String tieuDe;    // Tiêu đề trang
    private String moTa;      // Mô tả trang

    // Hàm khởi tạo
    public OnboardingItem(int hinhAnh, String tieuDe, String moTa) {
        this.hinhAnh = hinhAnh;
        this.tieuDe = tieuDe;
        this.moTa = moTa;   
    }

    // Các hàm lấy thông tin
    public int getImage() {
        return hinhAnh;
    }

    public String getTitle() {
        return tieuDe;
    }

    public String getDescription() {
        return moTa;
    }
} 