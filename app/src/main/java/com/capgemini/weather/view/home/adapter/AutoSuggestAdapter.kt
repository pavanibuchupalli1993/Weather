package com.capgemini.weather.view.home.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.capgemini.weather.R
import com.capgemini.weather.data.model.search.Result
import com.capgemini.weather.utils.DataConverter
import com.capgemini.weather.utils.NotificationHelper

class AutoSuggestAdapter(context: Context, @LayoutRes private val layoutResource: Int) :
    ArrayAdapter<com.capgemini.weather.data.model.search.Result>(context, layoutResource),
    Filterable {

    private var citiesData: MutableList<com.capgemini.weather.data.model.search.Result>? = ArrayList()
    private val dataConverter = DataConverter()

    fun setData(list: List<com.capgemini.weather.data.model.search.Result>?) {
        citiesData!!.clear()
        citiesData!!.addAll(list!!)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context)
            .inflate(layoutResource, parent, false) as TextView
        view.text = citiesData?.get(position)?.let { prepareDropdownText(it) }
        return view
    }

    override fun getCount(): Int {
        return if (citiesData != null && citiesData!!.size > 0) {
            citiesData!!.size
        } else 0
    }

    override fun getItem(position: Int): com.capgemini.weather.data.model.search.Result {
        return citiesData!![position]
    }

    /**
     * Used to Return the full object directly from adapter.
     *
     * @param position
     * @return
     */
    fun getObject(position: Int): Result {
        return citiesData!![position]
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint != null) {
                    filterResults.values = citiesData
                    filterResults.count = citiesData!!.size
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged()
                } else if (constraint != null && constraint.length > 3) {
                    showNoDataSnackBarMessage()
                    notifyDataSetInvalidated()
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }
    }

    private fun prepareDropdownText(result: com.capgemini.weather.data.model.search.Result): String {
        return dataConverter.prepareDisplayIdFromResult(result)
    }

    private fun showNoDataSnackBarMessage() {
        try {
            val rootView =
                (context as Activity?)!!.window.decorView.findViewById<View>(R.id.content)
            NotificationHelper().setSnackBar(
                rootView,
                context.getString(R.string.search_no_result_found)
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}