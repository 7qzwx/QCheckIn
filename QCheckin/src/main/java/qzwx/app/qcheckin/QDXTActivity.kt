package qzwx.app.qcheckin

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import qzwx.app.qcheckin.data.CheckInDao
import qzwx.app.qcheckin.data.CheckInRepository
import qzwx.app.qcheckin.data.CheckInRepositoryImpl
import qzwx.app.qcheckin.data.QZXTDatabase
import qzwx.app.qcheckin.navigation.NavGraph
import qzwx.app.qcheckin.theme.QZWX_AppTheme
import qzwx.app.qcheckin.viewmodel.CheckInViewModel

class QDXTActivity : ComponentActivity() {
    private lateinit var checkInRepository: CheckInRepository

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database: QZXTDatabase = QZXTDatabase.getInstance(this)
        val checkInDao: CheckInDao = database.checkInDao()
        checkInRepository = CheckInRepositoryImpl(checkInDao)

        setContent {
            QZWX_AppTheme {
                // 使用 rememberNavController 和 viewModel 提供导航和 ViewModel
                val navController = rememberNavController()
                val checkInViewModel = CheckInViewModel(checkInRepository)

                NavGraph(checkInRepository = checkInRepository, checkInViewModel = checkInViewModel)
            }
        }
    }
}