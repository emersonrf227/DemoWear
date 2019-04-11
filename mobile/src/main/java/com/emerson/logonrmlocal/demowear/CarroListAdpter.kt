package com.emerson.logonrmlocal.demowear

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emerson.logonrmlocal.shared.Carro
import kotlinx.android.synthetic.main.adapter_carro.view.*

class CarroListAdapter(
        private val carros: MutableList<Carro>,
        private val callback: Callback?
) : RecyclerView.Adapter<CarroListAdapter.CarroViewHolder>() {

    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        val carro = carros[position]
        holder.itemView.tvModelo.text = carro.modelo
        holder.itemView.tvFabricante.text = carro.fabricante
        holder.itemView.setOnClickListener {
            callback?.carroClicked(carro)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_carro, parent, false)
        return CarroViewHolder(view)
    }

    override fun getItemCount() = carros.size

    inner class CarroViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface Callback {
        fun carroClicked(carro: Carro)
    }
}


