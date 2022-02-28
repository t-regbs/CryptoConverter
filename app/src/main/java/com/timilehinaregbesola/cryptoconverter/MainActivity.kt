package com.timilehinaregbesola.cryptoconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timilehinaregbesola.cryptoconverter.ui.theme.CryptoConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Converter", color = Color.White) },
                backgroundColor = Color.Black
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF050506))
                .padding(horizontal = 32.dp, vertical = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FeaturedCoinCard(
                    backgroundColor = Color(0xFFA5CAE3),
                    name = "Bitcoin" to "BTC",
                    percentage = 6,
                    amount = 6.0286
                )
                FeaturedCoinCard(
                    backgroundColor = Color(0xFFCEB7F0),
                    name = "Dogecoin" to "DOGE",
                    percentage = 118,
                    amount = 16.800
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FeaturedCoinCard(
                    backgroundColor = Color(0xFFE4E4E5),
                    name = "Ethereum" to "ETH",
                    percentage = 18,
                    amount = 0.0086
                )
                FeaturedCoinCard(
                    backgroundColor = Color(0xFF383F48),
                    name = "Dollars" to "USDT",
                    percentage = 78,
                    amount = 7.860,
                    textColor = Color.White
                )
            }
            Spacer(Modifier.height(64.dp))
            Column(Modifier.padding(horizontal = 32.dp)) {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Send",
                    fontSize = 12.sp,
                    color = Color(0xFFA2A2A6)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "0.686",
                        fontSize = 21.sp,
                        color = Color.White
                    )
                    CurrencySelector(initialDiff = 0) {}
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier.width(80.dp),
                        color = Color(0xFFA2A2A6),
                    )
                    Icon(
                        modifier = Modifier
                            .background(
                                color = Color(0xFFE4E4E5),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(8.dp),
                        imageVector = Icons.Filled.SwapHoriz,
                        contentDescription = "Change icon",
                        tint = Color.Black
                    )
                    Divider(
                        modifier = Modifier.width(80.dp),
                        color = Color(0xFFA2A2A6),
                    )
                }
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Get",
                    fontSize = 12.sp,
                    color = Color(0xFFA2A2A6)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "39.393",
                        fontSize = 21.sp,
                        color = Color.White
                    )
                    CurrencySelector(initialDiff = 2) {}
                }
            }
        }
    }
}

@Composable
fun CurrencySelector(initialDiff: Int, onValueChange: (Int) -> Unit) {
    val expanded = remember { mutableStateOf(false) }
    val items = listOf("BTC", "ETH", "USDT")
    var init by remember { mutableStateOf(initialDiff) }
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
                text = items[init],
                fontSize = 21.sp,
                color = Color.White
            )
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            items.forEachIndexed { index, s ->
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

@Composable
fun FeaturedCoinCard(
    backgroundColor: Color,
    name: Pair<String, String>,
    percentage: Int,
    amount: Double,
    textColor: Color = Color.Black
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp),
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptoConverterTheme {
        HomeScreen()
    }
}
