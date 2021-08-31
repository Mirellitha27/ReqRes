package com.iwebsapp.reqres.ui.home.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
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
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), HomeView, androidx.appcompat.widget.SearchView.OnQueryTextListener  {
    private var presenter: HomePresenter? = null
    private var navController: NavController? = null
    private lateinit var searchItem: MenuItem
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeAdapter
    private var people: ArrayList<Datum> = arrayListOf()
    private var matchedPeople: ArrayList<Datum> = arrayListOf()
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
        setHasOptionsMenu(true)
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
        people = data
        adapter = HomeAdapter(data, requireContext(), presenter!!)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        searchItem = menu.findItem(R.id.m_search)
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        val id = menuItem.itemId
        if (id == R.id.m_search) {
            Log.d("TAG", "onOptionsItemSelected ")
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchItem.collapseActionView()
        search(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        search(newText)
        return false
    }

    private fun search(text: String?) {
        matchedPeople = arrayListOf()
        text?.let {
            people.forEach { person ->
                if (person.last_name!!.contains(text, true) ||
                    person.first_name!!.contains(text, true) ||
                    person.email!!.contains(text, true) ||
                    person.avatar!!.contains(text, true) ||
                    person.id.toString().contains(text, true)) {
                    matchedPeople.add(person)
                }
            }
            updateRecyclerView()
            if (matchedPeople.isEmpty()) {
                Toast.makeText(requireContext(), "No match found!", Toast.LENGTH_SHORT).show()
            }
            updateRecyclerView()
        }
    }

    private fun updateRecyclerView() {
        binding.recyclerView.apply {
            adapter = HomeAdapter(matchedPeople, requireContext(), presenter!!)
            binding.recyclerView.adapter = adapter
        }
    }
}