package com.yhr.jfj.tipappcomposer.util

// Function for calculating tip amount which show in the Tip section
fun calculateTotalTip(totalBill: Double, tipPercentage: Int): Double {
    return if (totalBill > 1 && totalBill.toString()
            .isNotEmpty()
    ) (totalBill * tipPercentage) / 100 else 0.0
}
//fun calculateTotalTip(totalBill: Double, tipPercentage: Double): Double {
//    return if (totalBill > 0) {
//        (totalBill * tipPercentage)
//    } else {
//        0.0
//    }
//}