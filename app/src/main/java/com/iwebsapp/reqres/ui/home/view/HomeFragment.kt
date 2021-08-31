package com.iwebsapp.reqres.ui.home.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.iwebsapp.reqres.R
import com.iwebsapp.reqres.databinding.FragmentHomeBinding
import com.iwebsapp.reqres.model.home.Datum
import com.iwebsapp.reqres.ui.home.adapter.HomeAdapter
import com.iwebsapp.reqres.ui.home.presenter.HomePresenter
import com.iwebsapp.reqres.ui.home.presenter.HomePresenterImpl

class HomeFragment : Fragment(), HomeView {
    private var presenter: HomePresenter? = null
    private var navController: NavController? = null

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUpVariable()

        val llm = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = llm

        presenter!!.init()
    }

    private fun setUpVariable() {
        if (presenter == null) {
            presenter = HomePresenterImpl(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setDataAdapter(data: ArrayList<Datum>) {
        val adapter = HomeAdapter(data, requireContext(), presenter!!)
        binding.recyclerView.adapter = adapter
    }

    override fun showImage(urlImg: String?, imageView: ImageView?) {
        Glide.with(requireActivity())
            .load(Uri.parse(urlImg))
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imageView!!)
    }

    override fun showDetail(idUser: String) {
        val bundle = Bundle()
        bundle.putString("idUser", idUser)
        navController!!.navigate(R.id.action_nav_home_to_nav_gallery, bundle)
    }

}