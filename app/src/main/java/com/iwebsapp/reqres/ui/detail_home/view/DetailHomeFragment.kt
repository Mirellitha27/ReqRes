package com.iwebsapp.reqres.ui.detail_home.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.iwebsapp.reqres.R
import com.iwebsapp.reqres.databinding.FragmentDetailHomeBinding
import com.iwebsapp.reqres.ui.detail_home.presenter.DetailHomePresenter
import com.iwebsapp.reqres.ui.detail_home.presenter.DetailHomePresenterImpl


class DetailHomeFragment : Fragment(), DetailHomeView {
    private var presenter: DetailHomePresenter? = null
    private var _binding: FragmentDetailHomeBinding? = null
    private lateinit var idUser: String

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            arguments?.let {
                it.getString("idUser")?.let { tag ->
                    idUser = tag
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpVariable()
        presenter!!.init(idUser)
    }

    override fun setData(id: String, email: String?, name: String?, lastName: String?, image: String?) {
        binding.textViewId.text = id
        binding.textViewMail.text = email
        binding.textViewName.text = name
        binding.textViewLastName.text = lastName

        Glide.with(requireActivity())
            .load(Uri.parse(image))
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(binding.imageView)
    }

    private fun setUpVariable() {
        if (presenter == null) {
            presenter = DetailHomePresenterImpl(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}