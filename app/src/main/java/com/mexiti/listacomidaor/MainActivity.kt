package com.mexiti.listacomidaor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mexiti.listacomidaor.data.DataSource
import com.mexiti.listacomidaor.model.Platillo
import com.mexiti.listacomidaor.ui.theme.ListaComidaOrTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaComidaOrTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MenuApp()
                }
            }
        }
    }
}


@Composable
fun MenuApp(){
    MenuCardList(platilloList = DataSource().LoadPlatillos())
}



@Composable
fun MenuCardList( platilloList: List<Platillo>,modifier: Modifier= Modifier   ) {
    Scaffold(
        topBar = {
            MenuTopAppBar()
        }
    ) {
        it->


        LazyColumn(contentPadding = it) {
            items(platilloList) { platillo ->
                MenuCard(
                    platillo = platillo,
                    modifier = modifier.padding(10.dp)


                )
            }


        }


    }
}



@Composable
fun MenuCard(platillo:Platillo, modifier: Modifier = Modifier ){
    Card {
        Column {
            Image(
                painter = painterResource(id = platillo.drawableResourceId) ,
                contentDescription = stringResource(id = platillo.stringResourceId),
                modifier =
                modifier
                    .fillMaxWidth()
                    .height(190.dp),
                contentScale = ContentScale.Crop


            )
            Text(
                text = LocalContext.current.getString(platillo.stringResourceId),
                modifier = modifier.padding(22.dp),
                style = MaterialTheme.typography.displayMedium
            )

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(painter = painterResource(id = R.drawable.logo_comida),
                    contentDescription = null,
                    modifier = modifier
                        .padding(8.dp)
                        .size(64.dp)
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )

            }

        },
        modifier = modifier
    )
}




@Composable
@Preview(showBackground = true)
fun ShowMenuCard(){
    ListaComidaOrTheme(darkTheme = false) {
        MenuCardList(platilloList = DataSource().LoadPlatillos()  )
    }


}





