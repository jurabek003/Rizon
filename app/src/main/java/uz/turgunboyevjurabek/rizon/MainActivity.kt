package uz.turgunboyevjurabek.rizon

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.turgunboyevjurabek.rizon.databinding.ActivityMainBinding
import uz.turgunboyevjurabek.rizon.fragments.HomeFragment
import uz.turgunboyevjurabek.rizon.fragments.orderFragment.OrderFragment
import uz.turgunboyevjurabek.rizon.fragments.productsFragment.ProductFragment
import uz.turgunboyevjurabek.rizon.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val homeFragment=HomeFragment()
        val productFragment= ProductFragment()
        val orderFragment= OrderFragment()
        val profileFragment=ProfileFragment()

        binding.btnNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.btn_home->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.my_navigation_host,homeFragment)
                        .commit()
                    binding.thtPanel.text="Asosiy Panel"
                    binding.btnNavigation.menu.findItem(R.id.btn_home).setIcon(R.drawable.ic_home2)
                    binding.btnNavigation.menu.findItem(R.id.btn_user).setIcon(R.drawable.ic_user)
                    binding.btnNavigation.menu.findItem(R.id.btn_savat).setIcon(R.drawable.ic_savat)
                    binding.btnNavigation.menu.findItem(R.id.btn_karzinku).setIcon(R.drawable.ic_karzinka)
                }

                R.id.btn_savat->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.my_navigation_host,productFragment)
                        .commit()
                    binding.thtPanel.text="Mahsulotlar"
                    binding.btnNavigation.menu.findItem(R.id.btn_home).setIcon(R.drawable.ic_home)
                    binding.btnNavigation.menu.findItem(R.id.btn_user).setIcon(R.drawable.ic_user)
                    binding.btnNavigation.menu.findItem(R.id.btn_savat).setIcon(R.drawable.ic_savat2)
                    binding.btnNavigation.menu.findItem(R.id.btn_karzinku).setIcon(R.drawable.ic_karzinka)
                }
                R.id.btn_karzinku->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.my_navigation_host,orderFragment)
                        .commit()
                    binding.thtPanel.text="Buyurtmalar"
                    binding.btnNavigation.menu.findItem(R.id.btn_home).setIcon(R.drawable.ic_home)
                    binding.btnNavigation.menu.findItem(R.id.btn_user).setIcon(R.drawable.ic_user)
                    binding.btnNavigation.menu.findItem(R.id.btn_savat).setIcon(R.drawable.ic_savat)
                    binding.btnNavigation.menu.findItem(R.id.btn_karzinku).setIcon(R.drawable.ic_kalaska22)
                }
                R.id.btn_user->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.my_navigation_host,profileFragment)
                        .commit()
                    binding.thtPanel.text="Profil"
                    binding.btnNavigation.menu.findItem(R.id.btn_home).setIcon(R.drawable.ic_home)
                    binding.btnNavigation.menu.findItem(R.id.btn_user).setIcon(R.drawable.ic_user2)
                    binding.btnNavigation.menu.findItem(R.id.btn_savat).setIcon(R.drawable.ic_savat)
                    binding.btnNavigation.menu.findItem(R.id.btn_karzinku).setIcon(R.drawable.ic_karzinka)
                }

            }

            true
        }
    }
}