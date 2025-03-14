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
    val viewModel: NoteViewModel = hiltViewModel()
    NavHost(navController, startDestination = "note_list") {
        composable("note_list") {
            NoteListScreen(
                onNoteClick = { note ->
                    val noteId = note?.id ?: -1
                    navController.navigate("note_Detail/$noteId")
                },
                viewModel = viewModel
            )
        }
        composable(
            route = "note_Detail/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
            if (noteId <= 0) {
                navController.popBackStack()
                return@composable
            }

            NoteDetailScreen(
                noteId = noteId,
                navController = navController,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}