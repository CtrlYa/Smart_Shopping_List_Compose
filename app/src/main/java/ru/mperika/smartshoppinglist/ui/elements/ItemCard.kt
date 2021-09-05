package ru.mperika.smartshoppinglist.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mperika.smartshoppinglist.data.Product
import smartshoppinglist.R


@Composable
fun itemListCard(product: Product) {
    Row(Modifier.padding(all = 8.dp).border(width = 3.dp, color = Color.Red, RectangleShape).fillMaxWidth()) {
        Image(painter = painterResource(id = R.drawable.default_pic), contentDescription = "Default pic", modifier = Modifier.size(90.dp).clip(CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier.height(95.dp)) {
            Text(text = product.productUserName, fontSize = 25.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = product.productBrand)
            Text(text = product.category.catName)
        }
    }
}

@Composable
fun itemFullCard() {

}
