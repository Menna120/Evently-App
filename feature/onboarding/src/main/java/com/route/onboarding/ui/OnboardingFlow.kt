package com.route.onboarding.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.route.designsystem.composables.EventlyButton
import com.route.designsystem.composables.LanguageSwitch
import com.route.designsystem.composables.ThemeSwitch
import com.route.designsystem.navigation.Auth
import com.route.designsystem.navigation.Onboarding
import com.route.designsystem.theme.EventlyTheme
import com.route.designsystem.utils.LocalRootNavController
import com.route.onboarding.R
import com.route.onboarding.components.NavigationIconButton
import com.route.onboarding.components.OnboardingHeader
import com.route.onboarding.components.PageIndicator
import com.route.onboarding.components.SwitchPanelItem
import com.route.onboarding.model.OnboardingPage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = koinViewModel(),
) {
    val onboardingState by viewModel.onboardingState.collectAsStateWithLifecycle()
    val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle()
    val isArabic by viewModel.isArabic.collectAsStateWithLifecycle()

    OnboardingScreenContent(
        onboardingState = onboardingState,
        isDarkTheme = isDarkTheme,
        isArabic = isArabic,
        modifier = modifier,
        onThemeChanged = viewModel::onThemeChanged,
        onLanguageChanged = viewModel::onLanguageChanged,
        showPager = viewModel::showPager,
        onFinishOnboarding = viewModel::onFinishOnboarding
    )
}

@Composable
fun OnboardingScreenContent(
    onboardingState: OnboardingState,
    isDarkTheme: Boolean,
    isArabic: Boolean,
    modifier: Modifier = Modifier,
    onThemeChanged: (isDark: Boolean) -> Unit,
    onLanguageChanged: (isArabic: Boolean) -> Unit,
    showPager: () -> Unit,
    onFinishOnboarding: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {
        OnboardingHeader(modifier = Modifier.weight(.05f))

        AnimatedContent(
            targetState = onboardingState,
            modifier = Modifier.weight(.95f),
            transitionSpec = { fadeIn(tween(400)) togetherWith fadeOut(tween(400)) },
            label = "OnboardingFlowSwitch",
        ) { state ->
            when (state) {
                is OnboardingState.Welcome -> WelcomeStartScreen(
                    isArabic = isArabic,
                    isDarkTheme = isDarkTheme,
                    onLanguageChange = onLanguageChanged,
                    onThemeChange = onThemeChanged,
                    onStartClick = showPager,
                )

                is OnboardingState.Pager -> OnboardingPagerScreen(onFinishOnboarding = onFinishOnboarding)
            }
        }
    }
}

@Composable
private fun WelcomeStartScreen(
    isArabic: Boolean,
    isDarkTheme: Boolean,
    onLanguageChange: (Boolean) -> Unit,
    onThemeChange: (Boolean) -> Unit,
    onStartClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.weight(.9f)) {
            OnboardingContent(page = OnboardingPage.Welcome) {
                SwitchPanelItem(title = stringResource(R.string.language)) {
                    LanguageSwitch(checked = isArabic, onCheckedChange = onLanguageChange)
                }

                SwitchPanelItem(title = stringResource(R.string.theme)) {
                    ThemeSwitch(checked = isDarkTheme, onCheckedChange = onThemeChange)
                }
            }
        }

        Box(
            modifier = Modifier
                .weight(.1f)
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            EventlyButton(
                text = stringResource(R.string.let_s_start),
                modifier = Modifier.fillMaxWidth(),
                onClick = onStartClick,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnboardingPagerScreen(onFinishOnboarding: () -> Unit) {
    val navController = LocalRootNavController.current
    val scope = rememberCoroutineScope()
    val pages = OnboardingPage.entries.drop(1)
    val pagerState = rememberPagerState(
        pageCount = { pages.size },
        initialPage = 0,
    )
    val currentPage = pages[pagerState.currentPage]
    val isLastPage = currentPage == OnboardingPage.Connect

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(.9f),
            userScrollEnabled = true,
        ) { pageIndex ->
            OnboardingContent(page = pages[pageIndex])
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.1f)
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            PageNavigation(
                pagerState = pagerState,
                modifier = Modifier.fillMaxSize(),
                onPreviousClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                },
                onNextClick = {
                    if (isLastPage) {
                        onFinishOnboarding()
                        navController.navigate(Auth) {
                            popUpTo<Onboarding> { inclusive = true }
                        }
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
            )
        }
    }
}

@Composable
private fun OnboardingContent(
    page: OnboardingPage,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit) = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Image(
            painter = painterResource(id = page.illustrationResId),
            contentDescription = stringResource(id = page.titleResId),
            modifier = Modifier
                .fillMaxHeight(.5f)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit,
        )

        Text(
            text = stringResource(id = page.titleResId),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = stringResource(id = page.descriptionResId),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground,
        )

        content()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PageNavigation(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    val pageCount = pagerState.pageCount
    val currentPageIndex = pagerState.currentPage
    val isFirstPage = currentPageIndex == 0

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        // Previous Button
        AnimatedContent(!isFirstPage) { visible ->
            if (visible) {
                NavigationIconButton(
                    iconRes = R.drawable.ic_arrow_left,
                    contentDescription = stringResource(R.string.previous),
                    onClick = onPreviousClick,
                )
            } else {
                Spacer(Modifier.size(48.dp))
            }
        }

        PageIndicator(
            currentPage = currentPageIndex,
            pageCount = pageCount,
        )

        // Next Button
        NavigationIconButton(
            iconRes = R.drawable.ic_arrow_right,
            contentDescription = stringResource(R.string.next),
            onClick = onNextClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    EventlyTheme {
        CompositionLocalProvider(LocalRootNavController provides rememberNavController()) {
            OnboardingScreen()
        }
    }
}
