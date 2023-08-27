package uz.turgunboyevjurabek.rizon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import uz.ilhomjon.rizonuz.R
import uz.ilhomjon.rizonuz.databinding.ActivityMainBinding
import uz.turgunboyevjurabek.rizon.utils.AppObject

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        AppObject.binding = binding
        AppObject.fragmentManager = supportFragmentManager
        val navController = findNavController(R.id.my_navigation_host)
        binding.btnNavigation.setupWithNavController(navController)

        //selectNavigation()

        binding.imgNotification.setOnClickListener {
            navController.navigate(R.id.notificationFragment)
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private fun selectNavigation() {
        if (binding.btnNavigation.selectedItemId==R.id.homeFragment){
            binding.btnNavigation.menu.findItem(R.id.homeFragment).setIcon(R.drawable.ic_home2)
        }
        if (binding.btnNavigation.selectedItemId==R.id.orderFragment){
            binding.btnNavigation.menu.findItem(R.id.orderFragment).setIcon(R.drawable.ic_kalaska2)
            binding.btnNavigation.menu.findItem(R.id.homeFragment).setIcon(R.drawable.ic_home2)

        }

        if(binding.btnNavigation.selectedItemId==R.id.productFragment){
            binding.btnNavigation.menu.findItem(R.id.productFragment).setIcon(R.drawable.ic_savat2)
            binding.btnNavigation.menu.findItem(R.id.orderFragment).setIcon(R.drawable.ic_karzinka)
            binding.btnNavigation.menu.findItem(R.id.homeFragment).setIcon(R.drawable.ic_home2)
        }
    }

//    override fun onResume() {
//        super.onResume()
//    //    selectNavigation()
//    }
    //vaqtinchalik funkiya token yozib olish uchun
//    fun addCatchToken(){
//        MySharedPreference.init(this)
//        MySharedPreference.token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjkxOTE4NDU2LCJpYXQiOjE2OTE3NDU2NTYsImp0aSI6IjQyMGM5ZGRlMGE0NzQ2MzI4NmQ2MmVlY2VhOWFmN2I0IiwidXNlcl9pZCI6ImQ0ZDJlZDBlLTljYTMtNGI1Ni1iYjc4LTAxNDk3MWU1YmFkNCJ9.Dv0qBe8nEvuwq1WwnpZJ32TkHMpBKGaDXI-QFD0rw-w"
//    }
}