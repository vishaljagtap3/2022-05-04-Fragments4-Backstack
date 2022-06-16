package com.bitcode.fragments4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bitcode.fragments4.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var dataFragments = ArrayList<DataFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddFragment.setOnClickListener {
            addDataFragment()
        }

        binding.btnRemoveFragment.setOnClickListener {
            removeDataFragment()
        }
    }

    private fun addDataFragment() {
        var dataFragment = DataFragment()

        dataFragments.add(dataFragment)

        var data = Data(
            "BitCode",
            Random(System.currentTimeMillis()).nextInt()
        )

        var input = Bundle()
        input.putSerializable("data", data)
        dataFragment.arguments = input

        supportFragmentManager.beginTransaction()
            .add(R.id.mainContainer, dataFragment, null)
            .addToBackStack(null)
            .commit()
    }

    private fun removeDataFragment() {
        //supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .remove(dataFragments[dataFragments.size-1])
            .addToBackStack(null)
            .commit()

        dataFragments.removeAt(dataFragments.size-1)
    }
}