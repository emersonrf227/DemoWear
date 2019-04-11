package com.emerson.logonrmlocal.demowear

import android.app.Notification
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.emerson.logonrmlocal.shared.Carro
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        CarroListAdapter.Callback, GoogleApiClient.ConnectionCallbacks {


    private lateinit var cliente: GoogleApiClient
    private  var connectedNode: List<Node>? = null






    override fun onConnected(p0: Bundle?) {

        Wearable.NodeApi.getConnectedNodes(cliente).setResultCallback {

            connectedNode = it.nodes


        }
    }

    override fun onConnectionSuspended(p0: Int) {

        connectedNode = null

    }






    override fun carroClicked(carro: Carro) {

        val gson = Gson()

        connectedNode?.forEach{
            Wearable.MessageApi.sendMessage(cliente,
                                            it.id,
                                            "/carro",
                                            gson.toJson(carro).toByteArray()
            )
        }


    }

    private var adapter: CarroListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val carros = CarroRepository.buscarTodos(this)
        adapter = CarroListAdapter(carros, this)
        lista.adapter = adapter
        lista.layoutManager = LinearLayoutManager(this)


        cliente = GoogleApiClient.Builder(this).addConnectionCallbacks(this).addApi(Wearable.API).build()

        cliente.connect()

    }
}

