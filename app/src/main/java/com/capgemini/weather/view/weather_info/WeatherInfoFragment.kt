package com.capgemini.weather.view.weather_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.capgemini.weather.R
import com.capgemini.weather.data.api.WeatherApiRequest
import com.capgemini.weather.databinding.FragmentDetailsBinding
import com.capgemini.weather.data.model.city_wheather.WeatherApiResponseModel
import com.capgemini.weather.data.model.WeatherRoomDataModel
import com.capgemini.weather.view.home.ApiRepository
import com.capgemini.weather.data.api.WeatherApiRetrofit
import com.capgemini.weather.databinding.WeatherInfoBinding
import com.capgemini.weather.utils.NotificationHelper
import com.capgemini.weather.view.base.BaseFragment
import com.squareup.picasso.Picasso
import javax.inject.Inject


class WeatherInfoFragment : BaseFragment() {

    @Inject
    lateinit var weatherApiRetrofit: WeatherApiRetrofit

    @Inject
    lateinit var notificationHelper: NotificationHelper



    private lateinit var detailsViewModel: WeatherInfoViewModel
    private lateinit var binding: WeatherInfoBinding
    private var roomDataModel: WeatherRoomDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiRequest = weatherApiRetrofit.getRetrofitInstance()
            .create(WeatherApiRequest::class.java)
        val apiRepository = ApiRepository(apiRequest)
        val factory = WeatherInfoViewModelFactory(apiRepository)
        detailsViewModel = ViewModelProvider(this, factory).get(WeatherInfoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeatherInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomDataModel = arguments?.let { detailsViewModel.getData(it) }
        initObserver()
        roomDataModel?.let { fetchWeatherDetailsFromApi(it) }
    }

    private fun initObserver() {
        detailsViewModel.getWeatherMutableLiveData()
            .observe(viewLifecycleOwner) { baseApiResponseModel ->
                hideProgressDialog()
                if (baseApiResponseModel != null && baseApiResponseModel.isSuccessful) {
                    val apiResponseData = baseApiResponseModel.apiResponseData
                    apiResponseData?.let { it ->
                        populateResult(it as WeatherApiResponseModel)
                    }

                } else {
                    //TODO handle api error here
                    val errorMsgString = resources.getString(R.string.error_msg)
                    notificationHelper.setSnackBar(binding.root, errorMsgString)
                }
            }
    }

    private fun fetchWeatherDetailsFromApi(roomDataModel: WeatherRoomDataModel) {
        showProgressDialog()
        detailsViewModel.fetchWeatherResponseLiveData(roomDataModel.areaName.toString())
    }

    private fun populateResult(weatherApiResponseModel: WeatherApiResponseModel) {
        val currentCondition = weatherApiResponseModel.data.current_condition[0]

        Picasso.get()
            .load(currentCondition.weatherIconUrl[0].value.replace("http","https"))
            .error(android.R.drawable.ic_menu_gallery)
            .into(binding.imgWeather)

        binding.txtCityName.text = weatherApiResponseModel.data.request[0].query.toString()
        binding.tvHumidity.text = currentCondition.humidity.toString()
        binding.txtWeather.text = currentCondition.weatherDesc[0].value
        binding.tvTemperature.text = detailsViewModel.getTemperatureText(currentCondition)
    }

    private fun showProgressDialog() {
        binding.progressBar.visibility = View.VISIBLE
    }


    private fun hideProgressDialog() {
        binding.progressBar.visibility = View.GONE
    }
}