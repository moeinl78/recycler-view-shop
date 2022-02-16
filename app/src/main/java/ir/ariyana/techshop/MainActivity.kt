package ir.ariyana.techshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.ariyana.techshop.databinding.ActivityMainBinding
import ir.ariyana.techshop.databinding.AddItemBinding
import ir.ariyana.techshop.databinding.RemoveItemBinding
import ir.ariyana.techshop.databinding.UpdateItemBinding

class MainActivity : AppCompatActivity(), Adapter.ItemEvents {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : Adapter

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

        adapter = Adapter(itemList.clone() as ArrayList<Item>, this)
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // add item to the recycler view
        binding.addItem.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val view = AddItemBinding.inflate(layoutInflater)
            dialog.setView(view.root)
            dialog.setCancelable(true)
            dialog.show()

            view.confirmAddItem.setOnClickListener {
                val itemName = view.itemNameInput.text.toString()
                val itemWeight = view.itemWeightInput.text.toString()
                val itemOs = view.itemOsInput.text.toString()
                val itemRelease = view.itemReleaseInput.text.toString()
                val randomImages = arrayListOf<String>(
                    "https://fdn2.gsmarena.com/vv/bigpic/xiaomi-12-pro.jpg",
                    "https://fdn2.gsmarena.com/vv/bigpic/xiaomi-black-shark-4s.jpg",
                    "https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-s22-plus-5g.jpg",
                    "https://fdn2.gsmarena.com/vv/bigpic/sony-xperia-pro-i.jpg",
                    "https://fdn2.gsmarena.com/vv/bigpic/sony-xperia-1-II-o.jpg",
                    "https://fdn2.gsmarena.com/vv/bigpic/asus-rog-phone-5s-pro.jpg",
                )
                val randomNum = (0..5).random()
                val item = Item(itemName, itemWeight, itemOs, itemRelease, randomImages[randomNum])
                adapter.addItem(item)
                binding.mainRecyclerView.scrollToPosition(adapter.itemCount - 1)
                dialog.dismiss()
            }
        }
    }

    override fun onItemClicked(item: Item, itemPosition: Int) {
        val dialog = AlertDialog.Builder(this).create()
        val view = UpdateItemBinding.inflate(layoutInflater)
        dialog.setView(view.root)
        dialog.setCancelable(true)
        dialog.show()

        view.updateNameInput.setText(item.itemName)
        view.updateWeightInput.setText(item.itemWeigh)
        view.updateOsInput.setText(item.itemSoftware)
        view.updateReleaseInput.setText(item.itemReleaseDate)

        if(view.updateNameInput.length() > 0 && view.updateWeightInput.length() > 0 && view.updateOsInput.length() > 0 && view.updateReleaseInput.length() > 0) {
            view.confirmUpdateItem.setOnClickListener {
                val itemName = view.updateNameInput.text.toString()
                val itemWeight = view.updateWeightInput.text.toString()
                val itemOs = view.updateOsInput.text.toString()
                val itemRelease = view.updateReleaseInput.text.toString()

                val updatedItem = Item(itemName, itemWeight, itemOs, itemRelease, item.itemImage)
                adapter.updateItem(updatedItem, itemPosition)
                dialog.dismiss()
            }
        }
        else {
            Toast.makeText(this, "invalid data!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onLongItemClicked(item: Item, itemPosition: Int) {
        val dialog = AlertDialog.Builder(this).create()
        val view = RemoveItemBinding.inflate(layoutInflater)
        dialog.setView(view.root)
        dialog.setCancelable(true)
        dialog.show()

        view.removeItemAccept.setOnClickListener {
            adapter.removeItem(item, itemPosition)
            dialog.dismiss()
        }

        view.removeItemCancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}