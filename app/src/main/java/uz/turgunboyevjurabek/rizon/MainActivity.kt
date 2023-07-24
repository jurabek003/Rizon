package uz.turgunboyevjurabek.rizon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import uz.turgunboyevjurabek.rizon.databinding.ActivityMainBinding
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        AppObject.binding = binding
        val navController = findNavController(R.id.my_navigation_host)
        binding.btnNavigation.setupWithNavController(navController)

        addCatchToken()

        binding.imgNotification.setOnClickListener {
            navController.navigate(R.id.notificationFragment)
        }
    }

    //vaqtinchalik funkiya token yozib olish uchun
    fun addCatchToken(){
        MySharedPreference.init(this)
        MySharedPreference.token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjkwMjY0NDI2LCJpYXQiOjE2ODk4MzI0MjYsImp0aSI6IjdkZmNmZGE0ODZlNDRmZTBhZDcxYzliY2YxYmI3NGY2IiwidXNlcl9pZCI6MTU4fQ.LIvwD08gI0EIJkKGWI8SxiYxNYTTpyxaB2Oq9muBDCg"
    }
}