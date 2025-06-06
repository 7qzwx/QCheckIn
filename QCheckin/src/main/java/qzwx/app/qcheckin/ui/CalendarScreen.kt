package qzwx.app.qcheckin.ui

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import qzwx.app.qcheckin.components.ActivityCalendar
import qzwx.app.qcheckin.components.ActivityRecord
import qzwx.app.qcheckin.components.CalendarDataProvider
import qzwx.app.qcheckin.viewmodel.CheckInViewModel
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarScreen(modifier: Modifier = Modifier, viewModel: CheckInViewModel) {
    // Create an adapter from CheckInViewModel to CalendarDataProvider
    val checkInDataProvider = remember {
        object : CalendarDataProvider {
            override suspend fun getActivityData(
                startMonth: YearMonth,
                endMonth: YearMonth
            ): Map<LocalDate, Int> {
                return viewModel.getCheckInData(startMonth, endMonth)
            }

            override fun getActivityRecordsByDate(date: LocalDate): Flow<List<ActivityRecord>> {
                return viewModel.getCheckInHistoryByDate(date).map { checkInList ->
                    checkInList.map { checkIn ->
                        ActivityRecord(
                            title = checkIn.checkInName,
                            value = checkIn.experience,
                            date = date
                        )
                    }
                }
            }
        }
    }
    // Use the reusable ActivityCalendar component
    ActivityCalendar(
        modifier = modifier,
        dataProvider = checkInDataProvider,
        title = "签到一览",
        detailsTitle = "签到记录",
        emptyDetailsMessage = "请选择一个日期查看签到记录",
        noRecordsMessage = "今天似乎没有打卡!"
    )
}