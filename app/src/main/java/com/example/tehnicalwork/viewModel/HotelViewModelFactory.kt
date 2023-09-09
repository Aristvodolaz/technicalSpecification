import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tehnicalwork.network.ApiRepository
import com.example.tehnicalwork.viewModel.HotelViewModel

class HotelViewModelFactory(private val apiRepository: ApiRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HotelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HotelViewModel(apiRepository) as T
        }
        throw IllegalArgumentException("Неизвестный класс ViewModel")
    }
}
