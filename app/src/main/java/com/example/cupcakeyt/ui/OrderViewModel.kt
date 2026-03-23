package com.example.cupcakeyt.ui

import androidx.lifecycle.ViewModel
import com.example.cupcakeyt.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/** Giá mặc định cho mỗi chiếc bánh cupcake */
private const val PRICE_PER_CUPCAKE = 2.00

/** Phụ phí nếu khách hàng muốn lấy bánh ngay trong ngày */
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {

    // Trạng thái giao diện (UI state) hiện tại của đơn hàng
    private val _uiState = MutableStateFlow(OrderUiState(pickupOptions = pickupOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    /** Cập nhật số lượng bánh và tính lại giá tiền */
    fun setQuantity(numberCupcakes: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                quantity = numberCupcakes,
                price = calculatePrice(quantity = numberCupcakes)
            )
        }
    }

    /** Cập nhật hương vị bánh */
    fun setFlavor(desiredFlavor: String) {
        _uiState.update { currentState ->
            currentState.copy(flavor = desiredFlavor)
        }
    }

    /** Cập nhật ngày lấy bánh và tính lại giá tiền (vì có thể có phụ phí) */
    fun setDate(pickupDate: String) {
        _uiState.update { currentState ->
            currentState.copy(
                date = pickupDate,
                price = calculatePrice(pickupDate = pickupDate)
            )
        }
    }

    /** Hủy đơn hàng, đưa mọi thứ về trạng thái ban đầu */
    fun resetOrder() {
        _uiState.value = OrderUiState(pickupOptions = pickupOptions())
    }

    /**
     * Hàm tính toán tổng tiền dựa trên số lượng và ngày lấy.
     * Trả về chuỗi tiền tệ đã được định dạng (ví dụ: $12.00)
     */
    private fun calculatePrice(
        quantity: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date
    ): String {
        var calculatedPrice = quantity * PRICE_PER_CUPCAKE
        // Nếu chọn lấy bánh ngay hôm nay (phần tử đầu tiên trong danh sách ngày), cộng thêm phụ phí
        if (pickupOptions()[0] == pickupDate) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        return NumberFormat.getCurrencyInstance().format(calculatedPrice)
    }

    /**
     * Hàm tạo danh sách 4 ngày gần nhất bắt đầu từ hôm nay
     * để hiển thị trong màn hình chọn ngày lấy bánh.
     */
    private fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()

        // Thêm ngày hiện tại và 3 ngày tiếp theo
        repeat(4) {
            dateOptions.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return dateOptions
    }
}