package com.saoudi.basiccodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saoudi.basiccodelab.ui.theme.BasicCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCodelabTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false)}
    val extraPadding by animateDpAsState (
        targetValue = if (expanded) 48.dp else 0.dp,
        animationSpec = tween (durationMillis = 2000)
    )
    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(horizontal = 8.dp,vertical = 4.dp)){
        Row (Modifier.padding(24.dp)){
            Column(
                Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)){
                Text("Hello")
                Text(name)
            }
            ElevatedButton(onClick = { expanded = !expanded }) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: ()->Unit) {
    // TODO
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ){
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasicCodelabTheme {
        MyApp()
    }
}


@Composable
fun MyApp(){
    var shouldShowOnboarding by rememberSaveable {mutableStateOf(true)}
    if(shouldShowOnboarding){
        OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})

    }else{
        Greetings()
    }
}
@Composable
fun Greetings(names: List<String> = List(1000){"$it"}) {
    Surface(color = MaterialTheme.colorScheme.background, ){
        Column( modifier = Modifier.padding(vertical = 4.dp)){
            LazyColumn {
                items(names){ name ->
                    Greeting(name)

                }
            }
        }
    }
}