package com.example.cupcakeyt.data

import com.example.cupcakeyt.R

object DataSource {
    // Danh sách các ID chuỗi văn bản đại diện cho các hương vị bánh
    val flavors = listOf(
        R.string.vanilla,
        R.string.chocolate,
        R.string.red_velvet,
        R.string.salted_caramel,
        R.string.coffee
    )

    // Danh sách các cặp giá trị: (ID chuỗi văn bản hiển thị trên nút, Số lượng thực tế)
    val quantityOptions = listOf(
        Pair(R.string.one_cupcake, 1),
        Pair(R.string.six_cupcakes, 6),
        Pair(R.string.twelve_cupcakes, 12)
    )
}