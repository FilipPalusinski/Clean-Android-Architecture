package pl.palusinskifilip.usingokhttpandretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pl.palusinskifilip.usingokhttpandretrofit.ui.theme.UsingOkHttpAndRetrofitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsingOkHttpAndRetrofitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    App(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Users(
    navController: NavController,
    viewModel: MainViewModel = viewModel(factory = MainViewModelFactory())) {
    viewModel.uiStateLiveData.observeAsState().value?.let{
        UserList(uiState = it, navController)
    }
}

@Composable
fun App(navController: NavHostController) {
    NavHost(navController, startDestination = AppNavigation.Users.route) {
        composable(route = AppNavigation.Users.route){
            Users(navController)
        }
        composable(
            route = AppNavigation.User.route,
            arguments = listOf(navArgument(AppNavigation.User.argumentName){
                type = NavType.StringType
            })
        ) {
            User(it.arguments?.getString(AppNavigation.User.argumentName).orEmpty())
        }
    }
}

@Composable
fun User(text: String) {
    Column {
        Text(text = text)
    }
}


@Composable
fun UserList(uiState: UiState, navController: NavController) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item(uiState.count) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = uiState.count)
            }
        }
        items(uiState.userList) {
            Column(modifier = Modifier
                .padding(16.dp)
                .clickable {
                    navController.navigate(AppNavigation.User.routeForName(it.name))
                }) {
                Text(text = it.name)
                Text(text = it.username)
                Text(text = it.email)
            }
        }
    }
}