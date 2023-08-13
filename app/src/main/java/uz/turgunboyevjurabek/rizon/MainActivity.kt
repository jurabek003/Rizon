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
        MySharedPreference.token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjkxOTE4NDU2LCJpYXQiOjE2OTE3NDU2NTYsImp0aSI6IjQyMGM5ZGRlMGE0NzQ2MzI4NmQ2MmVlY2VhOWFmN2I0IiwidXNlcl9pZCI6ImQ0ZDJlZDBlLTljYTMtNGI1Ni1iYjc4LTAxNDk3MWU1YmFkNCJ9.Dv0qBe8nEvuwq1WwnpZJ32TkHMpBKGaDXI-QFD0rw-w"
    }
}