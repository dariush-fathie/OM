package pro.ahoora.zhin.om.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var name: MutableLiveData<String> = MutableLiveData()

    init {
        name.value = "default"
    }

}
