package com.timilehinaregbesola.cryptoconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
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
            Text(modifier = Modifier.weight(1f), text = amount.toString(), fontSize = 24.sp)
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
