package com.hong.assignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hong.assignment.domain.usecase.GetCentersUseCase
import com.hong.assignment.domain.usecase.RemoveAllCentersUseCase
import com.hong.assignment.domain.usecase.SaveCentersUseCase
import com.hong.assignment.model.query.CentersQueryMap
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCentersUseCase: GetCentersUseCase,
    private val saveCentersUseCase: SaveCentersUseCase,
    private val removeAllCentersUseCase: RemoveAllCentersUseCase
) : ViewModel() {

    companion object {
        const val MAX_COUNT = 10
    }

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    private val _startMapPageEvent: MutableLiveData<Unit> by lazy { MutableLiveData() }
    val startMapPageEvent: LiveData<Unit> get() = _startMapPageEvent

    private val _currentProgress: MutableLiveData<Int> by lazy { MutableLiveData() }
    val currentProgress: LiveData<Int> get() = _currentProgress

    // [s] Parameter
    private val queryMap: CentersQueryMap by lazy { CentersQueryMap() }
    // [e] Parameter

    private val _centersSuccessCountEvent: MutableLiveData<Int> by lazy { MutableLiveData(0) }
    val centersSuccessCountEvent: LiveData<Int> get() = _centersSuccessCountEvent

    fun start() {
        // 앱 재시작시 로컬 디비 다 날리고 시작
        removeAllCentersUseCase()
            .subscribe({
                Timber.d("RemoveCenter Success $it")
                startCenters()
            }, {
                Timber.d("RemoveCenter ERROR $it")
                startCenters()
            }).addTo(compositeDisposable)
    }

    private fun startCenters() {
        // API ERROR 발생시에도 카운팅
        getCentersUseCase(queryMap)
            .flatMap {
                queryMap.pageNo++
                saveCentersUseCase(it.list)
            }
            .repeat(10)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                // API Counting..
                val count = centersSuccessCountEvent.value ?: 0
                _centersSuccessCountEvent.value = count.plus(1)

                if (isApiSuccessEnd()) {
                    _startMapPageEvent.value = null
                }
            }, {
                val count = centersSuccessCountEvent.value ?: 0
                _centersSuccessCountEvent.value = count.plus(1)
            }).addTo(compositeDisposable)
    }

    fun setProgress(progress: Int) {
        _currentProgress.value = progress
    }

    fun setLimitProgress(progress: Int) {
        // 프로그래스바 종료 호출

        // API Success
        if (isApiSuccessEnd()) {
            _startMapPageEvent.value = null
        } else {
            // 초대값 80%
            setProgress(progress.coerceAtMost(80))
        }
    }

    /**
     * API 100개 까지 다 가져왔는지 체크하는 함수
     */
    private fun isApiSuccessEnd(): Boolean {
        val count = centersSuccessCountEvent.value ?: 0
        return MAX_COUNT <= count
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
