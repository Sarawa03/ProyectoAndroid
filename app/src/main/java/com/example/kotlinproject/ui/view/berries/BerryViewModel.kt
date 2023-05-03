package com.example.kotlinproject.ui.view.berries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.domain.GetBerryByNameOrId
import com.example.kotlinproject.domain.model.BerryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BerryViewModel @Inject constructor(
    private val getBerryByNameOrId: GetBerryByNameOrId,
): ViewModel(){

    val berriesViewModel = MutableLiveData<List<BerryItem>>()
    companion object{
        var offset = 1
        const val MAX_BERRIES = 64
    }
//64
    fun getBerries(){
        viewModelScope.launch {
            val listBerriesShown: MutableList<BerryItem> = mutableListOf()
            for( i in offset..offset*10){
                if(i<=MAX_BERRIES) listBerriesShown.add(getBerryByNameOrId(i.toString()))
                else break
            }
            berriesViewModel.postValue(listBerriesShown)
        }
    }
}