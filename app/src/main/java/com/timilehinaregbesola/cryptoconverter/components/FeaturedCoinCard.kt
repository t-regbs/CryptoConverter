package com.timilehinaregbesola.cryptoconverter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeaturedCoinCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    name: Pair<String, String>,
    percentage: Int,
    amount: Double,
    textColor: Color = Color.Black
) {
    Card(
        modifier = modifier,
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = name.second, color = textColor)
                Text(text = "+$percentage%", color = Color(0xFFA2A2A6))
            }
            Text(modifier = Modifier.weight(1.5f), text = amount.toString(), fontSize = 24.sp)
            Column(modifier = Modifier.weight(3f), verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = name.first,
                    color = textColor
                )
            }
        }
    }
}
