package com.iwebsapp.reqres.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iwebsapp.reqres.R
import com.iwebsapp.reqres.model.home.Datum
import com.iwebsapp.reqres.ui.home.presenter.HomePresenter

class HomeAdapter(
    modelList:ArrayList<Datum>,
    context: Context,
    presenter: HomePresenter):
RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private val modelList: List<Datum>
    private val context: Context
    private val presenter: HomePresenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model: Datum = modelList[position]
        holder.txtIId.text = model.id.toString()
        holder.txtEmail.text = model.email
        holder.txtName.text = model.first_name.plus(" ").plus(model.last_name)
        presenter.showImage(model.avatar, holder.image)
        holder.itemView.setOnClickListener { presenter.showDetail(model.id.toString()) }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    class MyViewHolder(mView: View): RecyclerView.ViewHolder(mView) {
        var txtIId: TextView = itemView.findViewById(R.id.textViewId)
        var txtEmail: TextView = itemView.findViewById(R.id.textViewEmail)
        var txtName: TextView = itemView.findViewById(R.id.textViewName)
        var image: ImageView = itemView.findViewById(R.id.imgAvatar)
    }

    init {
        this.modelList = modelList
        this.context = context
        this.presenter = presenter
    }
}