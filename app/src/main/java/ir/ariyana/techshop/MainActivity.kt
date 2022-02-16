package ir.ariyana.techshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.ariyana.techshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = arrayListOf<Item>(
            Item("Samsung Galaxy S22 Ultra", "228g, 8.9mm thickness", "Android 12, One UI 4.1", "Release 2022, February 25", "https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-s22-ultra-5g.jpg"),
            Item("Xiaomi Redmi Note 11 Pro+", "204g, 8.3mm thickness", "Android 11, MIUI 12.5", "Released 2021, November 01", "https://fdn2.gsmarena.com/vv/bigpic/xiaomi-redmi-note11-pro-plus.jpg"),
            Item("Apple iPhone 13 Pro Max", "240g, 7.7mm thickness", "iOS 15, up to iOS 15.3", "Released 2021, September 24", "https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-13-pro-max.jpg"),
            Item("Huawei P50 Pro", "195g, 8.5mm thickness", "HarmonyOS 2.0, EMUI 12", "Released 2021, August 12", "https://fdn2.gsmarena.com/vv/bigpic/huawei-p50-pro.jpg"),
        )

        val adapter = Adapter(itemList.clone() as ArrayList<Item>)
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}