package tl209.noteapp_v1.ui.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import tl209.noteapp_v1.ui.screens.NoteDetailScreen
import tl209.noteapp_v1.ui.screens.NoteListScreen
import tl209.noteapp_v1.ui.viewmodel.NoteViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "note_list") {
        composable("note_list") {
            NoteListScreen { note ->
                navController.navigate("note_detail/${note?.id ?: -1}")
            }
        }
//        composable("note_detail/{noteId}", arguments = listOf(navArgument("noteId") { type = NavType.IntType })) { backStackEntry ->
//            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
//            val viewModel: NoteViewModel = hiltViewModel()
//            val note = viewModel.notes.value.firstOrNull { it.id == noteId }
//            NoteDetailScreen(note = note, onBack = { navController.popBackStack() })
//        }
        composable(
            route = "noteDetail/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId")
            NoteDetailScreen(
                noteId = noteId,
                navController = navController,
                onBack = { navController.popBackStack() }
            )
        }

    }
}