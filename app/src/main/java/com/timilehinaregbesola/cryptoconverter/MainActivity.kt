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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timilehinaregbesola.cryptoconverter.main.MainViewModel
import com.timilehinaregbesola.cryptoconverter.ui.theme.CryptoConverterTheme
import org.koin.androidx.compose.get

val fiatList = listOf(
    "AED", "CAD", "CNY", "EUR", "GBP", "HKD", "INR", "IQD", "JMD", "JPY", "KES", "KPW", "KRW", "KWD", "MKD",
    "MWK", "MXN", "NAD", "NGN", "NZD", "UGX", "USD", "UYU", "VND", "XAG", "XAU"
)
val cryptoList = listOf(
    "ADA", "BCD", "BCH", "BNB", "BTC", "BTCA", "DOGE", "DRGN", "EOS", "ERT", "ETC", "ETH", "LEO", "LINDA",
    "LINK", "LTC", "LUN", "MANA", "MIOTA", "TESLA", "THC", "THETA", "THS", "TRUMP", "TRX", "USDT", "WAVES",
    "WAX", "WTC", "XLM", "XTZ", "XMR", "XRP"
)
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
    val viewModel = get<MainViewModel>()
    val conversionState = viewModel.conversion.value
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
                .verticalScroll(rememberScrollState())
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
            Column(
                Modifier.padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.Center
            ) {
                val sendAmountText = rememberSaveable { mutableStateOf("0.686") }
                val currSelectedCurrency = rememberSaveable { mutableStateOf("NGN") }
                val currSelectedToken = rememberSaveable { mutableStateOf("ETH") }
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
                    TextField(
                        modifier = Modifier.width(150.dp),
                        value = sendAmountText.value,
                        onValueChange = { sendAmountText.value = it },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        textStyle = TextStyle(
                            color = Color.White,
                            fontSize = 21.sp,
//                            textAlign = TextAlign.Center
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color(0xFF050506),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        shape = MaterialTheme.shapes.medium.copy(CornerSize(24.dp))
                    )
                    CurrencySelector(initialDiff = 3, isCrypto = false) {
                        currSelectedCurrency.value = fiatList[it]
                    }
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
                    Button(
                        modifier = Modifier
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE4E4E5)),
                        onClick = {
                            viewModel.convert(
                                sendAmountText.value,
                                currSelectedCurrency.value,
                                currSelectedToken.value
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.SwapHoriz,
                            contentDescription = "Change icon",
                            tint = Color.Black
                        )
                    }
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
                        text = conversionState.coin,
                        fontSize = 21.sp,
                        color = Color.White
                    )
                    CurrencySelector(initialDiff = 11, isCrypto = true) {
                        currSelectedToken.value = cryptoList[it]
                    }
                }
            }
        }
    }
}

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
