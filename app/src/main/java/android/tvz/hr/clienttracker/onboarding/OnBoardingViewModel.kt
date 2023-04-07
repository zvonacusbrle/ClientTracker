package android.tvz.hr.clienttracker.onboarding

import android.tvz.hr.clienttracker.navigation.Screen
import android.tvz.hr.clienttracker.onboarding.util.OnboardingPrefs
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val onboardingPrefs: OnboardingPrefs
) : ViewModel() {

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Splash.route)
    val startDestionation: State<String> = _startDestination


    init {
        viewModelScope.launch {
            delay(3000L)
            onboardingPrefs.checkOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = Screen.ClientDetailsScreen.route
                } else {
                    _startDestination.value = Screen.Welcome.route
                }
            }
        }
    }
}