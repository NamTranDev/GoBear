package dev.tran.nam.gobear.view.splash.onboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import dev.tran.nam.gobear.databinding.ItemOnboardingBinding

class OnBoardingAdapter constructor(): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemOnboardingBinding.inflate(LayoutInflater.from(container.context), container, false)

        container.addView(binding.root)
        getItemPosition(binding.root)
        return binding.root
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return 3
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}