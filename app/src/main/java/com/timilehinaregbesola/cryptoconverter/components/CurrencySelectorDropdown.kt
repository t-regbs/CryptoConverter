package com.timilehinaregbesola.cryptoconverter.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timilehinaregbesola.cryptoconverter.util.cryptoList
import com.timilehinaregbesola.cryptoconverter.util.fiatList

@Composable
fun CurrencySelector(initialDiff: Int = 0, isCrypto: Boolean, onValueChange: (Int) -> Unit) {
    val expanded = remember { mutableStateOf(false) }
    var init by remember { mutableStateOf(initialDiff) }
    val currentList = if (isCrypto) cryptoList else fiatList
    Box {
        Row(
            modifier = Modifier
                .clickable(onClick = { expanded.value = true })
        ) {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                imageVector = Icons.Filled.ExpandMore,
                contentDescription = "expand",
                tint = Color.White
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = currentList[init],
                fontSize = 21.sp,
                color = Color.White
            )
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            currentList.forEachIndexed { index, s ->
                DropdownMenuItem(
                    onClick = {
                        expanded.value = false
                        init = index
                        onValueChange(index)
                    }
                ) {
                    Text(text = s)
                }
            }
        }
    }
}
