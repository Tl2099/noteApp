package tl209.noteapp_v1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tl209.noteapp_v1.ui.nav.NavGraph
import tl209.noteapp_v1.ui.theme.NoteApp_V1Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteApp_V1Theme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
