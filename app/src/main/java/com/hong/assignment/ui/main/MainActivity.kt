package com.hong.assignment.ui.main

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.databinding.DataBindingUtil
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.hong.assignment.BR
import com.hong.assignment.R
import com.hong.assignment.databinding.ActivityMainBinding
import com.hong.assignment.ui.map.MapActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private var animator : ValueAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performBinding()

        with(viewModel) {

            startMapPageEvent.observe(this@MainActivity) {
                moveToMap()
            }

            start()
        }

        startProgressAni()
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    /**
     * 프로그래스바 애니메이션 처리
     */
    private fun startProgressAni() {
        // 2초에 걸쳐 100%가 되도록 로딩바 구현
        animator = ValueAnimator.ofInt(0, 100).apply {
            interpolator = FastOutSlowInInterpolator()
            duration = 2000
            addUpdateListener {
                viewModel.setLimitProgress(it.animatedValue as Int)
            }
            start()
        }
    }

    /**
     * 지도 페이지 이동
     */
    private fun moveToMap() {
        // 기존 애니메이션 취소
        animator?.cancel()
        ValueAnimator.ofInt(binding.vProgress.progress, 100).apply {
            interpolator = FastOutSlowInInterpolator()
            duration = 400
            addUpdateListener {
                viewModel.setProgress(it.animatedValue as Int)
            }
            doOnEnd {
                Intent(this@MainActivity, MapActivity::class.java).apply {
                    startActivity(this)
                }
            }
            start()
        }

    }

    /**
     * Activity Binding
     */
    private fun performBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.setVariable(BR.vm, viewModel)
    }
}