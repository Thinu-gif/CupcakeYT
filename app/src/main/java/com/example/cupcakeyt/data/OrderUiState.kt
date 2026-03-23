package com.example.cupcakeyt.data

/**
 * Data class đại diện cho trạng thái giao diện (UI state) hiện tại của đơn hàng.
 */
data class OrderUiState(
    /** Số lượng bánh cupcake được chọn (ví dụ: 1, 6, 12) */
    val quantity: Int = 0,

    /** Hương vị bánh được chọn (ví dụ: "Chocolate", "Vanilla",...) */
    val flavor: String = "",

    /** Ngày lấy bánh được chọn (ví dụ: "Jan 1") */
    val date: String = "",

    /** Tổng giá tiền của đơn hàng hiện tại */
    val price: String = "",

    /** Danh sách các ngày có thể chọn để lấy bánh (được tạo tự động từ ViewModel) */
    val pickupOptions: List<String> = listOf()
)